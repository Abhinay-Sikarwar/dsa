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