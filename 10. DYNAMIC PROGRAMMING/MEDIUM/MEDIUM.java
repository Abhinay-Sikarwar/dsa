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

    // 213: HOUSE ROBBER II
// CIRCULAR ARRAY HANDLED BY TWO PASSES, EXCLUDING FIRST OR LAST HOUSE

    class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0]; // only one house
    
            // Run twice: excluding first or excluding last
            return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
        }
    
        // Rob linearly within nums[l..r] using rolling dp[3]
        private int robRange(int[] nums, int l, int r) {
            int[] dp = new int[3]; // dp[0]=prev2, dp[1]=prev, dp[2]=curr
    
            dp[0] = 0;
            dp[1] = nums[l];
    
            for (int i = l + 1; i <= r; i++) {
                dp[2] = Math.max(dp[1], dp[0] + nums[i]); // skip or take
                dp[0] = dp[1]; // shift
                dp[1] = dp[2];
            }
    
            return dp[1]; // final result
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N) — two passes through the array.
    // 🧠 SPACE COMPLEXITY: O(1) — only constant extra space.

    // 300: LONGEST INCREASING SUBSEQUENCE
// DP TO TRACK LIS ENDING AT EACH INDEX

    class Solution {
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);             // Each element is an LIS of length 1
            
            int maxLen = 1;
            
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i])  // Can extend the subsequence
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLen = Math.max(maxLen, dp[i]);  // Track global maximum
            }
            
            return maxLen;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^2) — nested loops over the array.
    // 🧠 SPACE COMPLEXITY: O(N) — dp array to store LIS.

    // 139: WORD BREAK
// DP TO CHECK IF STRING CAN BE SEGMENTED INTO DICTIONARY WORDS

    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict); // fast lookups
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true; // base case
    
            // dp[i] = can s[0..i-1] be segmented?
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break; // no need to check further splits
                    }
                }
            }
    
            return dp[s.length()];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^2) — nested loops over the string.
    // 🧠 SPACE COMPLEXITY: O(N) — dp array to store segmentation.

    // 377: COMBINATION SUM IV
// DP TO COUNT ORDERED COMBINATIONS TO FORM TARGET SUM

    class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1; // base case: one way to make 0 (choose nothing)
    
            // build up solutions for all sums from 1 to target
            for (int t = 1; t <= target; t++) {
                for (int num : nums) {
                    if (num <= t) {
                        dp[t] += dp[t - num]; // extend combinations that make (t - num)
                    }
                }
            }
    
            return dp[target]; // total combinations for target
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*target) — nested loops over nums and target.
    // 🧠 SPACE COMPLEXITY: O(target) — dp array to store combinations.

    // 322: COIN CHANGE
// DP TO FIND MINIMUM COINS TO MAKE AMOUNT, USING BOTTOM-UP APPROACH

    class Solution {
        public int coinChange(int[] coins, int amount) {
            // dp[i] = min coins to make amount i
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1); // initialize with a large number
            dp[0] = 0; // base case
    
            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
    
            // if dp[amount] wasn't updated, return -1
            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*amount) — nested loops over coins and amount.
    // 🧠 SPACE COMPLEXITY: O(amount) — dp array to store minimum.

    // 647: PALINDROMIC SUBSTRINGS
// DP TO COUNT PALINDROMIC SUBSTRINGS USING 1D ARRAY

    class Solution {
        public int countSubstrings(String s) {
            int n = s.length();
            boolean[] dp = new boolean[n];
            int count = 0;
    
            // Traverse from bottom to top (i decreasing)
            for (int i = n - 1; i >= 0; i--) {
                // Traverse rightwards for j >= i
                for (int j = n - 1; j >= i; j--) {
                    if (s.charAt(i) == s.charAt(j)) {
                        // If length < 3 or inner substring is palindrome
                        if (j - i < 3 || dp[j - 1]) {
                            dp[j] = true;
                            count++;
                        } else {
                            dp[j] = false;
                        }
                    } else {
                        dp[j] = false;
                    }
                }
            }
    
            return count;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^2) — nested loops over the string.
    // 🧠 SPACE COMPLEXITY: O(N) — dp array to store palindrome.

    // 516: LONGEST PALINDROMIC SUBSEQUENCE