//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  9. GREEDY
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 135: CANDY
// TWO PASSES TO ENSURE RATING ORDER, THEN SUM.

    import java.util.*;
    class Solution {
        public int candy(int[] ratings) {
            int n = ratings.length;
            if (n == 0)
                return 0;
    
            int[] candies = new int[n];
            Arrays.fill(candies, 1);
    
            // Left to right
            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i - 1])
                    candies[i] = candies[i - 1] + 1;
            }
    
            // Right to left
            for (int i = n - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1])
                    candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
    
            // Sum total
            int total = 0;
            for (int candy : candies)
                total += candy;
    
            return total;
        }
    }

    //⏱️ TIME COMPLEXITY: O(n) — each array is traversed twice (left→right and right→left).
    //🧠 SPACE COMPLEXITY: O(n) — extra array used to store candies for each child.