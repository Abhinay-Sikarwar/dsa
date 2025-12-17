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

    // 40: COMBINATION SUM II
// BACKTRACK TO FIND ALL UNIQUE COMBINATIONS (NO REUSE) THAT SUM TO TARGET.

    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(candidates); // Step 1: sort
            backtrack(candidates, target, 0, new ArrayList<>(), result);
            return result;
        }
    
        private void backtrack(int[] candidates, int remaining, int start,
                List<Integer> path, List<List<Integer>> result) {
    
            if (remaining == 0) {
                result.add(new ArrayList<>(path)); // valid combination
                return;
            }
    
            for (int i = start; i < candidates.length; i++) {
    
                // Skip duplicates
                if (i > start && candidates[i] == candidates[i - 1])
                    continue;
    
                // Prune search
                if (candidates[i] > remaining)
                    break;
    
                path.add(candidates[i]);
                backtrack(candidates, remaining - candidates[i], i + 1, path, result);
                path.remove(path.size() - 1); // backtrack
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(2^T)       // T is target value.
    // 🧠 SPACE COMPLEXITY: O(T)        // recursion depth + path.

    // 46: PERMUTATIONS
// BACKTRACK TO GENERATE ALL POSSIBLE PERMUTATIONS.

    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
            return result;
        }
    
        private void backtrack(int[] nums, boolean[] used,
                List<Integer> path, List<List<Integer>> result) {
    
            // Base case: permutation complete
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
    
            // Try each unused number
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue;
    
                used[i] = true;   // Choose
                path.add(nums[i]);
    
                backtrack(nums, used, path, result);
                
                // Backtrack
                path.remove(path.size() - 1);
                used[i] = false;  // Make available again
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N * N!)    // N! permutations, each of length N.
    // 🧠 SPACE COMPLEXITY: O(N)        // recursion depth + path.

    // 47: PERMUTATIONS II
// BACKTRACK TO GENERATE ALL UNIQUE PERMUTATIONS (HANDLE DUPLICATES).

    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums);    // To handle duplicate condition
            backtrack(nums, new boolean [nums.length], new ArrayList<>(), result);
            return result;
        }
    
        private void backtrack(int[] nums, boolean[] used,
                List<Integer> current, List<List<Integer>> result) {
    
            // Base case: permutation complete
            if (current.size() == nums.length) {
                result.add(new ArrayList<>(current));
                return;
            }
    
            for (int i = 0; i < nums.length; i++) {
    
                // If already used in this permutation
                if (used[i]) continue;
    
                // Skip duplicates
                // Only allow nums[i] if its previous duplicate is already used
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                    continue;
    
                // Choose
                used[i] = true;
                current.add(nums[i]);
    
                // Explore
                backtrack(nums, used, current, result);
    
                // Unchoose (backtrack)
                used[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N * N!)    // N! permutations, each of length N.
    // 🧠 SPACE COMPLEXITY: O(N)        // recursion depth + current.

    // 78: SUBSETS
// BACKTRACK TO GENERATE ALL POSSIBLE SUBSETS.

    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(0, nums, new ArrayList<>(), result);
            return result;
        }
    
        private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
            // Add current subset to result (make a copy)
            result.add(new ArrayList<>(current));
    
            // Try including each remaining element
            for (int i = start; i < nums.length; i++) {
                // Choose element nums[i]
                current.add(nums[i]);
    
                // Recurse to build further subsets
                backtrack(i + 1, nums, current, result);
    
                // Backtrack: remove last chosen element
                current.remove(current.size() - 1);
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N * 2^N)   // 2^N subsets, each of length up to N.
    // 🧠 SPACE COMPLEXITY: O(N)        // recursion depth + current.