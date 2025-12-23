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

    // 90: SUBSETS II
// BACKTRACK TO GENERATE ALL UNIQUE SUBSETS (HANDLE DUPLICATES).

    class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            // Sort to bring duplicates together
            Arrays.sort(nums);
    
            backtrack(0, nums, new ArrayList<>(), result);
            return result;
        }
    
        private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
            // Add current subset to result (make a copy)
            result.add(new ArrayList<>(current));
    
            // Try including each element starting from 'start'
            for (int i = start; i < nums.length; i++) {
                // Skip duplicates at the same recursion level
                if (i > start && nums[i] == nums[i - 1]) continue;
    
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

    // 77: COMBINATIONS
// BACKTRACK TO GENERATE ALL COMBINATIONS OF K NUMBERS FROM 1 TO N.

    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(1, n, k, new ArrayList<>(), result);
            return result;
        }
    
        private void backtrack(int start, int n, int k,
                List<Integer> current, List<List<Integer>> result) {
    
            // If k numbers are chosen, add to result
            if (current.size() == k) {
                result.add(new ArrayList<>(current));
                return;
            }
    
            // Try all possible next numbers
            for (int i = start; i <= n; i++) {
                current.add(i);                          // choose
                backtrack(i + 1, n, k, current, result); // explore
                current.remove(current.size() - 1);      // un-choose
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(C(N, K) * K) // C(N, K) combinations, each of length K.
    // 🧠 SPACE COMPLEXITY: O(K)          // recursion depth + current.

    // 131: PALINDROME PARTITIONING
// BACKTRACK TO PARTITION STRING INTO ALL POSSIBLE PALINDROMIC SUBSTRINGS, USING DP TO PRECOMPUTE PALINDROMES.

    class Solution {
        public List<List<String>> partition(String s) {
            int n = s.length();
            // isPal[i][j] = true if substring s[i..j] is a palindrome
            boolean[][] isPal = new boolean[n][n];
            List<List<String>> res = new ArrayList<>();
    
            // Precompute palindrome substrings
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {
                    isPal[i][j] = s.charAt(i) == s.charAt(j) &&
                            (j - i < 2 || isPal[i + 1][j - 1]);
                }
            }
    
            backtrack(0, s, isPal, new ArrayList<>(), res);
            return res;
        }
    
        private void backtrack(int start, String s, boolean[][] isPal,
                List<String> path, List<List<String>> res) {
    
            // Reached end → valid partition
            if (start == s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
    
            // Try all possible palindromic substrings
            for (int end = start; end < s.length(); end++) {
                if (isPal[start][end]) {
                    path.add(s.substring(start, end + 1));
                    backtrack(end + 1, s, isPal, path, res);
                    path.remove(path.size() - 1); // backtrack
                }
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N * 2^N)   // 2^N partitions, each up to length N.
    // 🧠 SPACE COMPLEXITY: O(N)        // DP table + recursion depth + current path.

    // 93: RESTORE IP ADDRESSES
// BACKTRACK TO SPLIT STRING INTO VALID IP ADDRESSES, WITH PRUNING BASED ON REMAINING CHARACTERS.

    class Solution {
        private List<String> result = new ArrayList<>();
    
        public List<String> restoreIpAddresses(String s) {
            backtrack(s, 0, 0, new StringBuilder());
            return result;
        }
    
        private void backtrack(String s, int index, int segments, StringBuilder path) {
            // Valid IP
            if (segments == 4 && index == s.length()) {
                result.add(path.toString());
                return;
            }
    
            // Invalid state
            if (segments == 4 || index == s.length()) {
                return;
            }
    
            // Pruning: remaining chars check
            int remainingChars = s.length() - index;
            int remainingSegments = 4 - segments;
            if (remainingChars < remainingSegments || remainingChars > remainingSegments * 3) {
                return;
            }
    
            int originalLength = path.length();
    
            // Try segment lengths 1 to 3
            for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
    
                String part = s.substring(index, index + len);
    
                // Leading zero check
                if (part.length() > 1 && part.charAt(0) == '0')
                    break;
    
                int value = Integer.parseInt(part);
                if (value > 255)
                    break;
    
                // Add dot if needed
                if (segments > 0)
                    path.append('.');
                path.append(part);
    
                backtrack(s, index + len, segments + 1, path);
    
                // Backtrack
                path.setLength(originalLength);
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(1)   // Fixed 4 segments, each with at most 3 choices (3⁴ = 81).
    // 🧠 SPACE COMPLEXITY: O(1)  // Max recursion depth = 4; path length is constant.

    // 526: BEAUTIFUL ARRANGEMENT
// BACKTRACK TO COUNT BEAUTIFUL ARRANGEMENTS USING BITMASKING.

    class Solution {
        private int count = 0;
    
        public int countArrangement(int n) {
            backtrack(n, 1, 0);
            return count;
        }
    
        private void backtrack(int n, int pos, int usedMask) {
            // All positions filled
            if (pos > n) {
                count++;
                return;
            }
    
            // Try placing each number at current position
            for (int num = 1; num <= n; num++) {
                // Skip if number already used
                if ((usedMask & (1 << num)) == 0) {
                    // Check beautiful arrangement condition
                    if (num % pos == 0 || pos % num == 0) {
                        // Mark num as used and move to next position
                        backtrack(n, pos + 1, usedMask | (1 << num));
                    }
                }
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N!)   // Permutation backtracking with strong pruning.
    // 🧠 SPACE COMPLEXITY: O(N)   // Recursion depth + bitmask.