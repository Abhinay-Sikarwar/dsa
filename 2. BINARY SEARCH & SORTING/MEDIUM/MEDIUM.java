//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 2. BINARY SEARCH & SORTING
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

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