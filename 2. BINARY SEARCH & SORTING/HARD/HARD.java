//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  2. BINARY SEARCH & SORTING
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 4: MEDIAN OF TWO SORTED ARRAYS
// OPTIMAL BINARY SEARCH
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int n = nums1.length;
            int m = nums2.length;
            int low = 0, high = n;

            while (low <= high) {
                int partitionN = (low + high) / 2;
                int partitionM = (n + m + 1) / 2 - partitionN;

                int maxLeftN = (partitionN == 0) ? Integer.MIN_VALUE : nums1[partitionN - 1];
                int minRightN = (partitionN == n) ? Integer.MAX_VALUE : nums1[partitionN];

                int maxLeftM = (partitionM == 0) ? Integer.MIN_VALUE : nums2[partitionM - 1];
                int minRightM = (partitionM == m) ? Integer.MAX_VALUE : nums2[partitionM];

                if (maxLeftN <= minRightM && maxLeftM <= minRightN) {
                    if ((n + m) % 2 == 0) {
                        return ((double)Math.max(maxLeftN, maxLeftM) + Math.min(minRightN, minRightM)) / 2;
                    } else {
                        return (double)Math.max(maxLeftN, maxLeftM);
                    }
                } else if (maxLeftN > minRightM) {
                    high = partitionN - 1;
                } else {
                    low = partitionN + 1;
                }
            }

            throw new IllegalArgumentException("Input arrays are not sorted."); 
        }
    }    

    // ⏱️ Time Complexity:  O(log(min(n, m)))
    // 🧠 Space Complexity:  O(1)