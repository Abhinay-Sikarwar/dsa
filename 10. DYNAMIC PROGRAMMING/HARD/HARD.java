//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  10. DYNAMIC PROGRAMMING
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 72: EDIT DISTANCE
// DP WHERE `dp[i][j]` = MIN EDITS BETWEEN PREFIXES.

    class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
    
            // dp[i][j] = min operations to convert word1[0..i-1] → word2[0..j-1]
            int[][] dp = new int[m + 1][n + 1];
    
            // Base cases: converting empty prefix
            for (int i = 0; i <= m; i++) dp[i][0] = i; // delete i chars
            for (int j = 0; j <= n; j++) dp[0][j] = j; // insert j chars
    
            // Fill DP table bottom-up
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
    
                    // If last characters match → no new operation needed
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    // Otherwise choose the best among insert, delete, replace
                    else {
                        dp[i][j] = 1 + Math.min(
                                dp[i - 1][j], // delete from word1
                                Math.min(
                                        dp[i][j - 1], // insert into word1
                                        dp[i - 1][j - 1] // replace character
                                ));
                    }
                }
            }
    
            return dp[m][n];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(M*N) — two nested loops over lengths of the words.
    // 🧠 SPACE COMPLEXITY: O(M*N) — DP table to store prefix differences.