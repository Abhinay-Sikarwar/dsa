//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  7. GRAPHS
//------------------------------------------------------------- HARD ----------------------------------------------------------------- 

    // 127: WORD LADDER
// BIDIRECTIONAL BFS TO FIND SHORTEST WORD LADDER

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            // Convert list to set for O(1) lookups
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return 0; // No solution if endWord not in dictionary
    
            // Sets for bidirectional BFS
            Set<String> begin = new HashSet<>(), end = new HashSet<>();
            begin.add(beginWord);
            end.add(endWord);
    
            int level = 1, len = beginWord.length(); // Current transformation length and word length
    
            while (!begin.isEmpty() && !end.isEmpty()) {
                // Expand smaller frontier for efficiency
                if (begin.size() > end.size()) {
                    Set<String> temp = begin;
                    begin = end;
                    end = temp;
                }
    
                Set<String> next = new HashSet<>(); // Next level words
                for (String word : begin) {
                    char[] chars = word.toCharArray();
    
                    for (int i = 0; i < len; i++) {
                        char orig = chars[i];
                        // Try all 26 letters at position i
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == orig) continue; // Skip same letter
                            chars[i] = c;
                            String newWord = new String(chars);
    
                            // If newWord is in opposite frontier, path found
                            if (end.contains(newWord)) return level + 1;
    
                            // If newWord in dictionary, add to next level and remove to mark visited
                            if (wordSet.remove(newWord)) next.add(newWord);
                        }
                        chars[i] = orig; // Restore original char for next iteration
                    }
                }
    
                begin = next; // Move to next level
                level++;      // Increment transformation length
            }
    
            return 0; // No transformation sequence found
        }
    }

    // ⏱️ Time Complexity:  O(N * L) where L is the length of each word and N is the number of words in the wordList.
    // 🧠 Space Complexity: O(N) for word sets used in BFS.

    // 269: ALIEN DICTIONARY
// TOPOLOGICAL SORT USING BFS TO DETERMINE CHARACTER ORDER

    class Solution {
        public String foreignDictionary(String[] words) {
            Map<Character, Set<Character>> graph = new HashMap<>(); // Graph: char -> set of chars that come after it
            Map<Character, Integer> indegree = new HashMap<>();   // In-degree of each char
    
            // Initialize graph nodes and indegrees
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    graph.putIfAbsent(c, new HashSet<>());
                    indegree.putIfAbsent(c, 0);
                }
            }
    
            // Build graph by comparing adjacent words
            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i];
                String w2 = words[i + 1];
                int minLen = Math.min(w1.length(), w2.length());
    
                // Check invalid case: prefix longer than next word
                if (w1.length() > w2.length() &&
                    w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
                    return "";
                }
    
                // Find first differing character and create edge
                for (int j = 0; j < minLen; j++) {
                    if (w1.charAt(j) != w2.charAt(j)) {
                        if (!graph.get(w1.charAt(j)).contains(w2.charAt(j))) {
                            graph.get(w1.charAt(j)).add(w2.charAt(j));
                            indegree.put(w2.charAt(j), indegree.get(w2.charAt(j)) + 1);
                        }
                        break; // Only the first difference matters
                    }
                }
            }
    
            // Topological sort using BFS
            Queue<Character> q = new LinkedList<>();
            for (char c : indegree.keySet()) {
                if (indegree.get(c) == 0) q.offer(c);
            }
    
            StringBuilder res = new StringBuilder();
            while (!q.isEmpty()) {
                char char_ = q.poll();
                res.append(char_);
                for (char neighbor : graph.get(char_)) {
                    indegree.put(neighbor, indegree.get(neighbor) - 1);
                    if (indegree.get(neighbor) == 0) q.offer(neighbor);
                }
            }
    
            // Check for cycle
            if (res.length() != indegree.size()) return "";
    
            return res.toString();
        }
    }

    // ⏱️ Time Complexity:  O(C) where C is the total number of characters in all words.
    // 🧠 Space Complexity: O(1) since the character set is fixed 

    // 212: WORD SEARCH II
// TRIE + DFS TO FIND ALL WORDS IN BOARD

    class Solution {
        // Trie node representing each prefix in dictionary
        private class TrieNode {
            TrieNode[] children = new TrieNode[26];
            String word; // non-null when a complete word ends here
        }
    
        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = buildTrie(words);  // build trie of all words
            Set<String> res = new HashSet<>();
            int m = board.length, n = board[0].length;
    
            // Start DFS from every cell on the board
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    dfs(board, i, j, root, res);
    
            return new ArrayList<>(res);
        }
    
        private void dfs(char[][] b, int i, int j, TrieNode node, Set<String> res) {
            // Stop if out of bounds or already visited
            if (i < 0 || j < 0 || i >= b.length || j >= b[0].length) return;
            char c = b[i][j];
            if (c == '#' || node.children[c - 'a'] == null) return; // invalid path
    
            node = node.children[c - 'a'];
            if (node.word != null) {           // found a complete word
                res.add(node.word);
                node.word = null;              // avoid duplicates
            }
    
            b[i][j] = '#';                     // mark current cell as visited
    
            // Explore all 4 directions (up, down, left, right)
            dfs(b, i + 1, j, node, res);
            dfs(b, i - 1, j, node, res);
            dfs(b, i, j + 1, node, res);
            dfs(b, i, j - 1, node, res);
    
            b[i][j] = c;                       // backtrack: restore cell
        }
    
        // Builds a Trie for fast prefix and word lookup
        private TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for (String w : words) {
                TrieNode node = root;
                for (char c : w.toCharArray()) {
                    int idx = c - 'a';
                    if (node.children[idx] == null)
                        node.children[idx] = new TrieNode();
                    node = node.children[idx];
                }
                node.word = w;                 // mark end of the word
            }
            return root;
        }
    }

    // ⏱️ Time Complexity: O(M * 4 * 3^(L - 1))  where M = total cells in the board, L = max word length.
    //     Each DFS explores at most 4 * 3^(L-1) paths, but Trie pruning helps cut down unnecessary searches.
    // 🧠 Space Complexity: O(N + L)  N = total characters in all words (for Trie), L = recursion depth (path length on board).

    // 1192: CRITICAL CONNECTIONS IN A NETWORK
// TARJAN'S ALGORITHM TO FIND ALL BRIDGES IN AN UNDIRECTED GRAPH

    class Solution {
        private int time = 0;
        private List<List<Integer>> adj;
        private List<List<Integer>> bridges;
    
        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            adj = new ArrayList<>();
            bridges = new ArrayList<>();
    
            // Build adjacency list
            for (int i = 0; i < n; i++)
                adj.add(new ArrayList<>());
            for (List<Integer> edge : connections) {
                int u = edge.get(0), v = edge.get(1);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
    
            int[] disc = new int[n];
            int[] low = new int[n];
            Arrays.fill(disc, -1); // mark all nodes unvisited
    
            // Run DFS from node 0 (graph is connected as per problem)
            dfs(0, -1, disc, low);
    
            return bridges;
        }
    
        private void dfs(int u, int parent, int[] disc, int[] low) {
            disc[u] = low[u] = time++;
    
            for (int v : adj.get(u)) {
                if (v == parent)
                    continue; // skip parent edge
    
                if (disc[v] == -1) { // unvisited
                    dfs(v, u, disc, low);
                    low[u] = Math.min(low[u], low[v]);
    
                    if (low[v] > disc[u]) // bridge condition
                        bridges.add(Arrays.asList(u, v));
                } else {
                    // back edge
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }
    }

    // ⏱️ Time Complexity: O(V + E) where V is the number of vertices and E is the number of edges.
    // 🧠 Space Complexity: O(V + E) for the adjacency list and recursion.

    // 721: ACCOUNTS MERGE
// UNION-FIND TO MERGE ACCOUNTS BASED ON COMMON EMAILS

    class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            Map<String, String> emailToName = new HashMap<>();  // Map email -> owner name
            Map<String, String> parent = new HashMap<>();       // Union-Find parent map
    
            // Step 1: Initialize parent for union-find and map emails to names
            for (List<String> account : accounts) {
                String name = account.get(0);
                for (int i = 1; i < account.size(); i++) {
                    String email = account.get(i);
                    parent.putIfAbsent(email, email);
                    emailToName.put(email, name);
                }
            }
    
            // Step 2: Union emails in the same account
            for (List<String> account : accounts) {
                String firstEmail = account.get(1);
                for (int i = 2; i < account.size(); i++) {
                    union(parent, firstEmail, account.get(i));
                }
            }
    
            // Step 3: Group emails by root parent
            Map<String, TreeSet<String>> groups = new HashMap<>();
            for (String email : parent.keySet()) {
                String root = find(parent, email);
                groups.computeIfAbsent(root, x -> new TreeSet<>()).add(email); // TreeSet sorts emails
            }
    
            // Step 4: Build result
            List<List<String>> result = new ArrayList<>();
            for (String root : groups.keySet()) {
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(emailToName.get(root));  // Add account owner
                mergedAccount.addAll(groups.get(root));    // Add sorted emails
                result.add(mergedAccount);
            }
    
            return result;
        }
    
        private String find(Map<String, String> parent, String email) {
            if (!parent.get(email).equals(email)) {
                parent.put(email, find(parent, parent.get(email))); // Path compression
            }
            return parent.get(email);
        }
    
        private void union(Map<String, String> parent, String email1, String email2) {
            String root1 = find(parent, email1);
            String root2 = find(parent, email2);
            if (!root1.equals(root2)) {
                parent.put(root1, root2);
            }
        }
    }

    // ⏱️ Time Complexity: O(A * log A) where A is the total number of emails across all accounts.
    // 🧠 Space Complexity: O(A) for the union-find structure and email mappings.