//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 3. TWO POINTERS & SLIDING WINDOW
// EASY

    // 26: REMOVE DUPLICATES FROM SORTED ARRAY
// TWO POINTERS
    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0;

            int j = 0; 
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[j]) {
                    j++; 
                    nums[j] = nums[i]; 
                }
            }

            return j + 1;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 283: MOVE ZEROES
// TWO POINTERS
    class Solution {
        public void moveZeroes(int[] nums) {
            int left = 0;

            for (int right = 0; right < nums.length; right++) {
                if (nums[right] != 0) {
                    if (left != right) {
                        int temp = nums[right];
                        nums[right] = nums[left];
                        nums[left] = temp;
                    }

                    left++;
                }
            }
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 977: SQUARES OF A SORTED ARRAY     
