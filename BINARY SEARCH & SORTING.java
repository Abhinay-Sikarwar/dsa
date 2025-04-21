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
