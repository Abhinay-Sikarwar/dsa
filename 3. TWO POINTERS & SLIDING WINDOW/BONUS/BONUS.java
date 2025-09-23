//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 3. TWO POINTERS & SLIDING WINDOW
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 30: SUBSTRING WITH CONCATENATION OF ALL WORDS
// HASHMAP, SLIDING WINDOW
    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> result = new ArrayList<>();
    
            // Length of each word (all words have the same length)
            int wordLen = words[0].length();
            // Total number of words
            int numWords = words.length;
            // Total length of all words concatenated
            int totalLen = wordLen * numWords;
    
            // If s is shorter than the total length of all words, no solution is possible
            if (s.length() < totalLen)
                return result;
    
            // Map to store the expected frequency of each word
            Map<String, Integer> wordCount = new HashMap<>();
            for (String word : words)
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
    
            // We try every possible starting index within the first wordLen characters
            // This helps cover all possible word alignments
            for (int i = 0; i < wordLen; i++) {
                int left = i; // Start of the sliding window
                int right = i; // End of the sliding window
                Map<String, Integer> seen = new HashMap<>(); // Words seen in current window
    
                // Move the right pointer in steps of wordLen
                while (right + wordLen <= s.length()) {
                    // Extract the current word from the string
                    String word = s.substring(right, right + wordLen);
                    right += wordLen;
    
                    // If the word is one we're looking for
                    if (wordCount.containsKey(word)) {
                        // Count how many times we've seen this word in the current window
                        seen.put(word, seen.getOrDefault(word, 0) + 1);
    
                        // If we've seen this word too many times, shrink the window from the left
                        while (seen.get(word) > wordCount.get(word)) {
                            String leftWord = s.substring(left, left + wordLen);
                            seen.put(leftWord, seen.get(leftWord) - 1);
                            left += wordLen;
                        }
    
                        // If the window contains exactly all the words, record the start index
                        if (right - left == totalLen)
                            result.add(left);
                    } else {
                        // If the word is not in the list, reset everything and move on
                        seen.clear();
                        left = right;
                    }
                }
            }
    
            return result;
        }
    }
    
    // ⏱️ Time Complexity:  O(n * l)                Where n = length of the main string, l = length of each word(all wors have same length)
    // 🧠 Space Complexity:  O(w)                   Where w = number of words i.e words.length 