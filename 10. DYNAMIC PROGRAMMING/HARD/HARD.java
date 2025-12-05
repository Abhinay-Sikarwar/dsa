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
// DP WHERE `dp[left][right]` = MAX COINS FROM BURSTING BALLOONS IN (left, right).

    class Solution {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[] arr = new int[n + 2];
    
            // Add virtual balloons with value 1 at both ends
            arr[0] = 1;
            arr[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                arr[i] = nums[i - 1];
            }
    
            // dp[left][right] = max coins from bursting balloons between (left, right)
            int[][] dp = new int[n + 2][n + 2];
    
            // Iterate over interval lengths
            for (int len = 2; len <= n + 1; len++) {
                for (int left = 0; left + len <= n + 1; left++) {
                    int right = left + len;
    
                    // Try each balloon i as the last to burst in (left, right)
                    for (int i = left + 1; i < right; i++) {
                        dp[left][right] = Math.max(
                            dp[left][right],
                            dp[left][i] +                 // coins from left interval
                            dp[i][right] +                // coins from right interval
                            arr[left] * arr[i] * arr[right] // coins from bursting i last
                        );
                    }
                }
            }
    
            // Full interval (0, n+1)
            return dp[0][n + 1];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^3) — three nested loops for interval lengths and positions.
    // 🧠 SPACE COMPLEXITY: O(N^2) — DP table to store max coins in the interval.

    // 85: MAXIMAL RECTANGLE
// TRANSFORM EACH ROW TO HISTOGRAM AND USE STACK-BASED LARGEST RECTANGLE ALGO.

    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int columns = matrix[0].length;
            int rows = matrix.length;
    
            int[] heights = new int[columns + 1];  // Histogram heights for each column (+ sentinel 0)
            heights[columns] = 0;                  // Ensures final stack cleanup
            int max = 0;
    
            for (int row = 0; row < rows; row++) {
                Stack<Integer> stack = new Stack<Integer>();  // Monotonic increasing stack of indices
    
                for (int i = 0; i < columns + 1; i++) {
    
                    // Update histogram height based on current row
                    if (i < columns)
                        if (matrix[row][i] == '1')
                            heights[i] += 1;       // Extend upward if '1'
                        else
                            heights[i] = 0;        // Reset height if '0'
    
                    // Push while non-decreasing; otherwise compute areas
                    if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                        stack.push(i);
                    } else {
                        // Pop all taller bars and compute maximal rectangles for them
                        while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                            int top = stack.pop();
                            int area = heights[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                            if (area > max)
                                max = area;
                        }

                        stack.push(i);
                    }
                }
            }
    
            return max;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(R*C) — Build heights for each row,leverage stack for largest area.
    // 🧠 SPACE COMPLEXITY: O(C) — heights array and a monotonic stack for indices.

    // 115: DISTINCT SUBSEQUENCES
// DP WHERE `dp[j]` = NUMBER OF WAYS TO FORM `t[0..j-1]` USING PROCESSED PREFIX OF `s`.

    class Solution {
        public int numDistinct(String s, String t) {
            int n = s.length(), m = t.length();
            
            // dp[j] = number of ways to form t[0..j-1] using processed prefix of s
            int[] dp = new int[m + 1];
    
            dp[0] = 1; // Empty t can always be formed
    
            for (int i = 1; i <= n; i++) {
                // Traverse backwards to avoid overwriting dp[j-1]
                for (int j = m; j >= 1; j--) {
    
                    // If characters match, add ways:
                    // - skip s[i-1]  (already in dp[j])
                    // - use s[i-1]   (dp[j-1])
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[j] += dp[j - 1];
                    }
                }
            }
    
            return dp[m];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N*M) — nested loops over lengths of s and t.
    // 🧠 SPACE COMPLEXITY: O(M) — DP array to store counts.

    // 87: SCRAMBLE STRING
// RECURSIVE WITH MEMOIZATION TO CHECK ALL SPLITS AND SWAPS.

    class Solution {
        private Map<String, Boolean> memo = new HashMap<>();

        public boolean isScramble(String s1, String s2) {
            // If strings are equal → trivial scramble
            if (s1.equals(s2)) return true;
            
            // If lengths differ or char frequencies differ → impossible
            if (s1.length() != s2.length() || !sameChars(s1, s2)) return false;
    
            // Memoization check
            String key = s1 + "#" + s2;
            if (memo.containsKey(key)) return memo.get(key);
    
            int n = s1.length();
    
            // Try all split positions
            for (int i = 1; i < n; i++) {
                // No swap case
                if ( isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                     isScramble(s1.substring(i), s2.substring(i)) ) {
                    memo.put(key, true);
                    return true;
                }
    
                // Swap case
                if ( isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                     isScramble(s1.substring(i), s2.substring(0, n - i)) ) {
                    memo.put(key, true);
                    return true;
                }
            }
    
            memo.put(key, false);
            return false;
        }
    
        // Quick frequency check to prune impossible cases
        private boolean sameChars(String a, String b) {
            int[] freq = new int[26];
            for (char c : a.toCharArray()) freq[c - 'a']++;
            for (char c : b.toCharArray()) freq[c - 'a']--;
            for (int f : freq) if (f != 0) return false;
            return true;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^4) — exponential splits with memoization.
    // 🧠 SPACE COMPLEXITY: O(N^3) — memoization storage.

    // 730: COUNT DIFFERENT PALINDROMIC SUBSEQUENCES
// DP WHERE `dp[i][j]` = COUNT OF UNIQUE PALINDROMIC SUBSEQUENCES IN `s[i..j]`.

    class Solution {
        static final int MOD = 1_000_000_007;

        public int countPalindromicSubsequences(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];
            char[] arr = s.toCharArray();
    
            // Base case: single characters are palindromes
            for (int i = 0; i < n; i++) dp[i][i] = 1;
    
            // Fill dp for intervals of increasing length
            for (int len = 2; len <= n; len++) {
                for (int i = 0; i + len - 1 < n; i++) {
                    int j = i + len - 1;
    
                    if (arr[i] != arr[j]) { 
                        dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                    } else {
                        int left = i + 1;
                        int right = j - 1;
    
                        // find first match from left
                        while (left <= right && arr[left] != arr[i]) left++;
                        // find first match from right
                        while (left <= right && arr[right] != arr[i]) right--;
    
                        if (left > right) {
                            // no match inside
                            dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                        } else if (left == right) {
                            // one match inside
                            dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                        } else {
                            // more than one match inside
                            dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                        }
                    }
    
                    dp[i][j] %= MOD;
                    if (dp[i][j] < 0) dp[i][j] += MOD;
                }
            }
    
            return dp[0][n - 1];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^2) — nested loops for all substrings.
    // 🧠 SPACE COMPLEXITY: O(N^2) — DP table to count unique palindrome.

    // 1000: MINIMUM COST TO MERGE STONES
// DP WHERE `dp[i][j]` = MIN COST TO MERGE STONES FROM INDEX `i` TO `j`.

    class Solution {
        private static int INF = 1_000_000_000;
        
        public int mergeStones(int[] stones, int K) {
            int n = stones.length;
    
            // If merging cannot end in exactly 1 pile -> impossible
            if ((n - 1) % (K - 1) != 0) return -1;
    
            // Build prefix sum for fast range sum
            int[] prefix = new int[n + 1];
            for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + stones[i];
    
            int[][] dp = new int[n][n];
    
            // Process intervals of increasing length
            for (int len = K; len <= n; len++) {
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    dp[i][j] = INF;
    
                    // Try forming K piles first
                    // Valid splits are spaced by (K-1)
                    for (int mid = i; mid < j; mid += K - 1) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][mid] + dp[mid + 1][j]);
                    }
    
                    // If interval can be reduced to 1 pile, pay merging cost
                    if ((len - 1) % (K - 1) == 0) {
                        dp[i][j] += prefix[j + 1] - prefix[i];
                    }
                }
            }
    
            // Minimum cost to merge entire array into 1 pile
            return dp[0][n - 1];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N^3/K) — three nested loops with step K-1 for splits.
    // 🧠 SPACE COMPLEXITY: O(N^2) — DP table to store min cost to merge.