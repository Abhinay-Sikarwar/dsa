//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 2. BINARY SEARCH & SORTING
// EASY

    // 278: FIRST BAD VERSION
// BINARY SEARCH
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int l = 1;
            return findBad(l,n);
        }
    
        private int findBad(int left, int right) {
            if (left == right) {
                return left;
            }
    
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                return findBad(left, mid);
            } else {
                return findBad(mid + 1, right);
            }
        }
    }
    
    // ⏱️ Time Complexity:  O(logn)
    // 🧠 Space Complexity:  O(logn)
    
    // 35: SEARCH INSERT POSITION
// BINARY SEARCH
    class Solution {
        public int searchInsert(int[] nums, int target) {
            int left = 0, right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;   
                }
            }
        
            return left;
        }
    }
    
    // ⏱️ Time Complexity:  O(logn)
    // 🧠 Space Complexity:  O(1)

// MEDIUM

    // 34: FIND FIRST AND LAST POSITION OF ELEMENT IN SORTED ARRAY
// BINARY SEARCH WITH SOME TWEAKS
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
                return new int[] {-1, -1};
            }
            int left = binary_search(nums, target, true);
            if (left == -1) return new int[] {-1, -1};
            int right = binary_search(nums, target, false);
            return new int[] {left, right};
        }

        private int binary_search(int[] nums, int target, boolean isSearchingLeft) {
            int left = 0, right = nums.length - 1, index = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    index = mid;
                    if (isSearchingLeft) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }

            return index;
        }
    }
    
    // ⏱️ Time Complexity:  O(logn)
    // 🧠 Space Complexity:  O(1)

    // 162: FIND PEAK ELEMENT
// BINARY SEARCH WITHOUT SORTING
    class Solution {
        public int findPeakElement(int[] nums) {
            if (nums.length == 1) return 0;
            int left = 0, right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                boolean isLeftSmaller = (mid == 0 || nums[mid - 1] <= nums[mid]);
                boolean isRightSmaller = (mid == nums.length - 1 || nums[mid + 1] <= nums[mid]);
                if (isLeftSmaller && isRightSmaller) {
                    return mid;
                } else if (mid < nums.length && nums[mid + 1] > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }
    } 
    
    // ⏱️ Time Complexity:  O(logn)
    // 🧠 Space Complexity:  O(1)

// HARD

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

//BONUS QUESTION

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