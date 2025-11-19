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

    // 10: REGULAR EXPRESSION MATCHING
// DP WHERE `dp[i][j]` = WHETHER `s[0..i-1]` MATCHES `p[0..j-1]`.

    class Solution {
        public boolean isMatch(String s, String p) {
            int n = s.length(), m = p.length();
            boolean[][] dp = new boolean[n + 1][m + 1];
    
            dp[0][0] = true;                         // EMPTY STRING MATCHES EMPTY PATTERN
    
            for (int j = 2; j <= m; j++)             // INITIALIZE DP FOR PATTERNS LIKE a*, a*b*
                if (p.charAt(j - 1) == '*')
                    dp[0][j] = dp[0][j - 2];         // '*' MATCHES ZERO OF PREV CHAR
    
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
    
                    char pc = p.charAt(j - 1);
    
                    if (pc != '*') {                 // NORMAL CHAR OR '.'
                        if (pc == '.' || pc == s.charAt(i - 1))
                            dp[i][j] = dp[i - 1][j - 1]; // MATCH DEPENDS ON DIAGONAL
                    } 
                    else {                            // '*' CASE
                        dp[i][j] = dp[i][j - 2];      // ZERO OCCURRENCES OF PREV CHAR
    
                        char prev = p.charAt(j - 2);  // PRECEDING CHAR FOR '*'
                        if (prev == '.' || prev == s.charAt(i - 1))
                            dp[i][j] |= dp[i - 1][j]; // ONE OR MORE OCCURRENCES
                    }
                }
            }
            return dp[n][m];  // FULL STRING/PATTERN MATCH?
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*M) — two nested loops over string and pattern lengths.
    // 🧠 SPACE COMPLEXITY: O(N*M) — DP table to store possible matches.

    // 44: WILDCARD MATCHING
// DP WHERE `dp[i][j]` = WHETHER `s[0..i-1]` MATCHES `p[0..j-1]`.

    class Solution {
        public boolean isMatch(String s, String p) {
            int n = s.length(), m = p.length();
            // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
            boolean[][] dp = new boolean[n + 1][m + 1];
    
            // Base case: empty string & empty pattern
            dp[0][0] = true;
    
            // Handle patterns like "*", "**", "***"
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 1];
                }
            }
    
            // Fill DP table
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
    
                    char pc = p.charAt(j - 1);  // pattern char
                    char sc = s.charAt(i - 1);  // string char
    
                    // Exact match or '?' → take diagonal (shrink both)
                    if (pc == sc || pc == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    // '*' → zero chars (dp[i][j-1]) OR one/more chars (dp[i-1][j])
                    else if (pc == '*') {
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }
    
            return dp[n][m];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*M) — two nested loops over string and pattern lengths.
    // 🧠 SPACE COMPLEXITY: O(N*M) — DP table to store possible matches.

    // 312: BURST BALLOONS