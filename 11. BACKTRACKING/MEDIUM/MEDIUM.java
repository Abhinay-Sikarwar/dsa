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
// BACKTRACK TO FIND ALL UNIQUE COMBINATIONS THAT SUM TO TARGET.

    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(0, candidates, target, new ArrayList<>(), result);
            return result;
        }
    
        // index: current position in candidates
        // target: remaining sum to fulfill
        private void backtrack(int index, int[] candidates, int target,
                List<Integer> path, List<List<Integer>> result) {
    
            // Found valid combination
            if (target == 0) {
                result.add(new ArrayList<>(path));
                return;
            }
    
            // Out of range or sum exceeded
            if (index == candidates.length || target < 0) return;
    
            // OPTION 1: pick current number (unlimited reuse)
            path.add(candidates[index]);
            backtrack(index, candidates, target - candidates[index], path, result);
    
            path.remove(path.size() - 1); // backtrack
    
            // OPTION 2: skip current number
            backtrack(index + 1, candidates, target, path, result);
        }
    }

    // ⏱️ TIME COMPLEXITY: O(2^T)       // T is target value.
    // 🧠 SPACE COMPLEXITY: O(T)        // recursion depth + path.