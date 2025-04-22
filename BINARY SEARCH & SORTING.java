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
