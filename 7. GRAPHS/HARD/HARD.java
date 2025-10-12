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