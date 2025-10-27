//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  2. BINARY SEARCH & SORTING
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 354: RUSSIAN DOLL ENVELOPES
// BINARY SEARCH, LONGEST INCREASING SUBSEQUENCE AND SORTING
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {

            Arrays.sort(envelopes, (a, b) ->
                a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
            );

            int[] LIS = new int[envelopes.length];
            int length = 0;

            for (int[] env : envelopes) {
                int height = env[1];
                int index = lowerBound(LIS, 0, length, height);

                LIS[index] = height;
                if (index == length) length++;
            }

            return length;
        }

        private int lowerBound(int[] LIS, int start, int end, int target) {
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (LIS[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }
    } 
    
    // ⏱️ Time Complexity:  O(nlogn)
    // 🧠 Space Complexity:  O(n)