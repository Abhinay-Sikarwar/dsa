//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  10. DYNAMIC PROGRAMMING
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 91: DECODE WAYS
// USE BOTTOM-UP DP TO COUNT VALID DECODINGS

    class Solution {
        public int numDecodings(String s) {
            int n = s.length();
            if (n == 0) return 0;
    
            int[] dp = new int[n + 1];
            dp[n] = 1; // base case: empty string
            dp[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0; // last char is valid if not '0'
    
            // fill dp backwards
            for (int i = n - 2; i >= 0; i--) {
                // '0' can't start a valid code
                if (s.charAt(i) == '0') {
                    dp[i] = 0;
                    continue;
                }
    
                dp[i] = dp[i + 1]; // take one digit
    
                int num = Integer.parseInt(s.substring(i, i + 2));
                if (num >= 10 && num <= 26)
                    dp[i] += dp[i + 2]; // take two digits if valid
            }
    
            return dp[0];
        }
    }

    //⏱️ TIME COMPLEXITY: O(N) — each character is processed once.
    //🧠 SPACE COMPLEXITY: O(N) — dp array stores results for all indices.

    // 62: UNIQUE PATHS
// USE 1D DP ARRAY TO COUNT UNIQUE PATHS IN GRID

    class Solution {
        public int uniquePaths(int m, int n) {
            int[] dp = new int[n];
            // First row: only one way to move (right)
            Arrays.fill(dp, 1);
    
            // Build paths row by row
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    // Paths = from top (dp[j]) + from left (dp[j-1])
                    dp[j] += dp[j - 1];
                }
            }
    
            // Bottom-right cell
            return dp[n - 1];
        }
    }

    //⏱️ TIME COMPLEXITY: O(M*N) — we fill an MxN grid.
    //🧠 SPACE COMPLEXITY: O(N) — we use a single array.

    // 55: JUMP GAME
// CHECK IF END OF ARRAY IS REACHABLE

    class Solution {
        public boolean canJump(int[] nums) {
            // farthest index we can reach so far
            int maxReach = 0;
            for (int i = 0; i < nums.length; i++) {
                // can't reach this index
                if (i > maxReach) return false;
                // update farthest reachable index
                maxReach = Math.max(maxReach, i + nums[i]);
            }
            return true;
        }
    }

    //⏱️ TIME COMPLEXITY: O(N) — single pass through the array.
    //🧠 SPACE COMPLEXITY: O(1) — only a few variables.

    // 152: MAXIMUM PRODUCT SUBARRAY
// TRACK MAX AND MIN PRODUCTS DUE TO NEGATIVES(FLIP SIGNS)

    class Solution {
        public int maxProduct(int[] nums) {
            // Base case: at least one element exists
            int maxSoFar = nums[0];  // max product ending here
            int minSoFar = nums[0];  // min product ending here
            int result = nums[0];    // global max product
    
            // Traverse the array from the second element
            for (int i = 1; i < nums.length; i++) {
                int cur = nums[i];
    
                // If current number is negative, swap max and min
                // because multiplying by a negative flips signs
                if (cur < 0) {
                    int temp = maxSoFar;
                    maxSoFar = minSoFar;
                    minSoFar = temp;
                }
    
                // Update max and min products ending at current index
                maxSoFar = Math.max(cur, cur * maxSoFar);
                minSoFar = Math.min(cur, cur * minSoFar);
    
                // Update the global maximum
                result = Math.max(result, maxSoFar);
            }
    
            return result;
        }
    }

    //⏱️ TIME COMPLEXITY: O(N) — single pass through the array.
    //🧠 SPACE COMPLEXITY: O(1) — only a few variables.

    // 198: HOUSE ROBBER
// OPTIMIZED DP WITH CONSTANT SPACE

    class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
    
            int[] dp = new int[3]; // dp[0] = prev2, dp[1] = prev, dp[2] = curr
    
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
    
            for (int i = 2; i < n; i++) {
                // either skip current (prev) or rob current (prev2 + nums[i])
                dp[2] = Math.max(dp[1], dp[0] + nums[i]);
                dp[0] = dp[1]; // shift prev2
                dp[1] = dp[2]; // shift prev
            }
    
            return dp[1]; // dp[1] always holds the latest result
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N) — single pass through the array.
    // 🧠 SPACE COMPLEXITY: O(1) — only constant extra space (3 variables used).