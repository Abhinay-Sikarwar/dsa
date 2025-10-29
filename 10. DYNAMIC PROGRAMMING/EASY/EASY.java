//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  10. DYNAMIC PROGRAMMING
//------------------------------------------------------------- EASY ----------------------------------------------------------------

    // 70: CLIMBING STAIRS
// TOP-DOWN DP WITH MEMOIZATION

    class Solution {
        private int[] DP;
    
        public int climbStairs(int n) {
            DP = new int[n + 1];
            return countWays(n);
        }
    
        private int countWays(int n) {
            if (n <= 2) return n;         // Base cases
            if (DP[n] != 0) return DP[n]; // already computed
    
            // Recurrence: ways(n) = ways(n-1) + ways(n-2)
            DP[n] = countWays(n - 1) + countWays(n - 2);
            return DP[n];
        }
    }

    //⏱️ TIME COMPLEXITY: O(N) — each state is computed and stored once.
    //🧠 SPACE COMPLEXITY: O(N) — Dp array and recursion stack use linear space.