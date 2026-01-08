//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  11. BACKTRACKING
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 691:  STICKERS TO SPELL WORD
// BACKTRACKING OVER STICKER CHOICES WITH DP MEMIZATION ON REMAINING TARGETS.

    class Solution {
        public int minStickers(String[] stickers, String target) {
            int n = target.length();
            // all target characters covered
            int FULL = (1 << n) - 1;
    
            // Convert stickers to frequency arrays
            int[][] freq = new int[stickers.length][26];
            for (int i = 0; i < stickers.length; i++)
                for (char c : stickers[i].toCharArray())
                    freq[i][c - 'a']++;
    
            // dp[mask] = minimum stickers needed to cover characters in mask
            int[] dp = new int[1 << n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0; // no characters covered → 0 stickers
    
            // Try all coverage states
            for (int mask = 0; mask <= FULL; mask++) {
    
                if (dp[mask] == Integer.MAX_VALUE) continue;
    
                // Try using each sticker
                for (int[] sticker : freq) {
    
                    int[] cnt = sticker.clone();
                    int next = mask;
    
                    // Try to cover uncovered target positions
                    for (int i = 0; i < n; i++) {
                        if (((mask >> i) & 1) == 1) continue;
    
                        char c = target.charAt(i);
                        if (cnt[c - 'a'] > 0) {
                            cnt[c - 'a']--;
                            next |= (1 << i);
                        }
                    }
    
                    // Transition to new coverage state
                    dp[next] = Math.min(dp[next], dp[mask] + 1);
                }
            }
    
            return dp[FULL] == Integer.MAX_VALUE ? -1 : dp[FULL];
        }
    }

    // ⏱️ TIME COMPLEXITY: O(m * 2^n)   // m = number of stickers, n = target length.
    // 🧠 SPACE COMPLEXITY: O(2^n)      // DP states for all target bitmask combinations.