//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  11. BACKTRACKING
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 17: LETTER COMBINATIONS OF A PHONE NUMBER
// BACKTRACK TO BUILD ALL LETTER COMBINATIONS.

    class Solution {
        private final String[] KEYPAD = {
                "", // 0
                "", // 1 (no letters)
                "abc", // 2
                "def", // 3
                "ghi", // 4
                "jkl", // 5
                "mno", // 6
                "pqrs", // 7
                "tuv", // 8
                "wxyz" // 9
        };
    
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            backtrack(digits, 0, new StringBuilder(), result);
            return result;
        }
    
        // DFS to build combinations
        private void backtrack(String digits, int index, StringBuilder path, List<String> result) {
            // Base: one full combination formed
            if (index == digits.length()) {
                result.add(path.toString());
                return;
            }
    
            // Get possible letters for this digit
            String letters = KEYPAD[digits.charAt(index) - '0'];
    
            // Try each letter and recurse
            for (char c : letters.toCharArray()) {
                path.append(c); // choose
                backtrack(digits, index + 1, path, result); // explore
                path.deleteCharAt(path.length() - 1); // un-choose
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(3^N * 4^M)   // N digits with 3 letters, M digits with 4 letters.
    // 🧠 SPACE COMPLEXITY: O(N)          // recursion depth + path storage.

    // 39: COMBINATION SUM