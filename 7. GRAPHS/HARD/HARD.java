//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 7. GRAPHS
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