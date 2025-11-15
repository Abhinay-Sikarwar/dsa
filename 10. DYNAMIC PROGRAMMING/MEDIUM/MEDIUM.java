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
// DP TO FIND LENGTH OF LONGEST PALINDROMIC SUBSEQUENCE USING 1D ARRAY

    class Solution {
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[] dp = new int[n];
    
            for (int i = n - 1; i >= 0; i--) {
                dp[i] = 1;     // single char palindrome
                int prev = 0;  // stores dp[j-1] from previous row
                for (int j = i + 1; j < n; j++) {
                    int temp = dp[j];
                    if (s.charAt(i) == s.charAt(j))
                        dp[j] = prev + 2; // match ends
                    else
                        dp[j] = Math.max(dp[j], dp[j - 1]); // skip one end
                    prev = temp;
                }
            }
            return dp[n - 1];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^2) — nested loops over the string.
    // 🧠 SPACE COMPLEXITY: O(N) — dp array to store substring lengths.

    // 64: MINIMUM PATH SUM
// DP TO FIND MINIMUM PATH SUM IN GRID, UPDATING GRID IN PLACE

    class Solution {
        public int minPathSum(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
    
            // Update grid in place to store the minimum path sums
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (i == 0 && j == 0) continue; // Start cell
                    else if (i == 0)
                        grid[i][j] += grid[i][j - 1]; // Only from left
                    else if (j == 0)
                        grid[i][j] += grid[i - 1][j]; // Only from top
                    else
                        grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]); // Min of top/left
                }
            }
    
            return grid[row - 1][col - 1]; // Bottom-right cell
        }
    }

    // ⏱️ TIME COMPLEXITY: O(M*N) — we fill an MxN grid.
    // 🧠 SPACE COMPLEXITY: O(1) — we modify the input.

    // 740: DELETE AND EARN
// TRANSFORM TO HOUSE ROBBER PROBLEM USING DP

    class Solution {
        public int deleteAndEarn(int[] nums) {
            // Find the maximum number to size our DP arrays
            int max = 0;
            for (int num : nums) {
                max = Math.max(max, num);
            }
    
            // earn[i] = total points we can earn from all occurrences of number i
            int[] earn = new int[max + 1];
            for (int num : nums) {
                earn[num] += num;
            }
    
            // dp[i] = max points we can earn considering numbers up to i
            int[] dp = new int[max + 1];
            dp[0] = 0;
            dp[1] = earn[1];
    
            // Standard "house robber" transition
            for (int i = 2; i <= max; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + earn[i]);
            }
    
            return dp[max];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N + M) — N is length of nums, M is max number.
    // 🧠 SPACE COMPLEXITY: O(M) — for dp and earn arrays.

    // 718: MAXIMUM LENGTH OF REPEATED SUBARRAY
// DP TO FIND LENGTH OF LONGEST COMMON SUBARRAY

    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            int[][] dp = new int[m + 1][n + 1];
            int maxLen = 0;
    
            // dp[i][j] = length of longest common subarray ending at nums1[i-1], nums2[j-1]
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1; // extend previous match
                        maxLen = Math.max(maxLen, dp[i][j]);
                    }
                    // else dp[i][j] = 0 (default)
                }
            }
    
            return maxLen;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(M*N) — nested loops over both arrays.
    // 🧠 SPACE COMPLEXITY: O(M*N) — dp array to store lengths.

    // 309: BEST TIME TO BUY AND SELL STOCK WITH COOLDOWN
// DP WITH 3 STATES: HOLD, SOLD, REST

    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length == 1) return 0;
    
            int[] dp = new int[3]; // 0: hold, 1: sold, 2: rest
            dp[0] = -prices[0];    // holding a stock
            dp[1] = 0;             // just sold (in cooldown)
            dp[2] = 0;             // not holding, can buy
    
            for (int i = 1; i < prices.length; i++) {
                int prevSold = dp[1];
                
                // If sold today -> must have been holding yesterday
                dp[1] = dp[0] + prices[i];
                // If hold today -> either continue holding or buy today
                dp[0] = Math.max(dp[0], dp[2] - prices[i]);
                // If rest today -> either stay resting or come from cooldown
                dp[2] = Math.max(prevSold, dp[2]);
            }
    
            // Max profit when not holding any stock
            return Math.max(dp[1], dp[2]);
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N) — single pass through prices.
    // 🧠 SPACE COMPLEXITY: O(1) — only a few variables.

    // 494: TARGET SUM
// TRANSFORM TO SUBSET SUM PROBLEM USING DP, COUNT SUBSETS WITH SUM = (target + totalSum) / 2

    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int totalSum = 0;
            for (int num : nums) totalSum += num;
            
            // If (target + totalSum) is odd or target is out of range → no solution
            if ((target + totalSum) % 2 != 0 || totalSum < Math.abs(target)) return 0;
            
            int newTarget = (target + totalSum) / 2;
            int[] dp = new int[newTarget + 1];
            dp[0] = 1; // one way to form sum 0 (choose nothing)
            
            // Count subsets with sum = newTarget
            for (int num : nums) {
                for (int j = newTarget; j >= num; j--) {
                    dp[j] += dp[j - num];
                }
            }
            
            return dp[newTarget];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*sum) — N is length of nums, sum is newTarget.
    // 🧠 SPACE COMPLEXITY: O(sum) — dp array to store subsets.

    // 416: PARTITION EQUAL SUBSET SUM
// DP TO CHECK IF ARRAY CAN BE PARTITIONED INTO TWO SUBSETS WITH EQUAL SUM

    class Solution {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int x : nums) sum += x;
    
            // If total sum is odd, can't divide equally
            if (sum % 2 != 0) return false;
            int target = sum / 2;
    
            boolean[] dp = new boolean[target + 1];
            dp[0] = true; // base: sum 0 is always achievable
    
            // For each number, update achievable sums
            for (int num : nums) {
                for (int j = target; j >= num; j--) {
                    dp[j] |= dp[j - num];
                }
                // 🚀 Early stop: if target sum already achievable
                if (dp[target]) return true;
            }
    
            return dp[target];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*sum) — N is length of nums, sum is target.
    // 🧠 SPACE COMPLEXITY: O(sum) — dp array to store achievable.

    // 279: PERFECT SQUARES
// DP TO FIND MINIMUM NUMBER OF PERFECT SQUARES SUMMING TO N

    class Solution {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);  // initialize with large value
            dp[0] = 0;                           // 0 can be formed with 0 squares
            
            // compute dp[i] for all i from 1 to n
            for (int i = 1; i <= n; i++) {
                // try every perfect square <= i
                for (int j = 1; j * j <= i; j++) {
                    int square = j * j;
                    // choose the minimum amoung all possible squares
                    dp[i] = Math.min(dp[i], dp[i - square] + 1);
                }
            }
            return dp[n];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*sqrt(N)) — nested loops over n and perfect squares.
    // 🧠 SPACE COMPLEXITY: O(N) — dp array to store minimum.

    // 1143: LONGEST COMMON SUBSEQUENCE
// DP TO FIND LENGTH OF LONGEST COMMON SUBSEQUENCE BETWEEN TWO STRINGS

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int n = text1.length(), m = text2.length();
    
            int[][] dp = new int[n + 1][m + 1]; // dp table with extra row & column
    
            // fill dp bottom-up
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
    
                    // if characters match, extend LCS
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } 
                    // else take best from left or top
                    else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
    
            return dp[n][m];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*M) — nested loops over both strings.
    // 🧠 SPACE COMPLEXITY: O(N*M) — dp table to store LCS length.

    // 1048: LONGEST STRING CHAIN
// DP TO FIND LONGEST STRING CHAIN BY REMOVING ONE CHAR AT A TIME

    class Solution {
        public int longestStrChain(String[] words) {
            // Sort words by length (shortest to longest)
            Arrays.sort(words, (a, b) -> a.length() - b.length());
    
            // dp[word] = longest chain ending at this word
            Map<String, Integer> dp = new HashMap<>();
    
            int longest = 1;
    
            for (String word : words) {
                int best = 1; // at least the word itself
    
                // Try removing each character to form predecessor
                for (int i = 0; i < word.length(); i++) {
                    String pred = word.substring(0, i) + word.substring(i + 1);
    
                    if (dp.containsKey(pred)) {
                        best = Math.max(best, dp.get(pred) + 1);
                    }
                }
    
                dp.put(word, best);
                longest = Math.max(longest, best);
            }
    
            return longest;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*L^2) — N is number of words, L is max word length.
    // 🧠 SPACE COMPLEXITY: O(N) — dp map to store chain.