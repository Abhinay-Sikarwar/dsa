//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 3. TWO POINTERS & SLIDING WINDOW
//------------------------------------------------------------- EASY ----------------------------------------------------------------

    // 26: REMOVE DUPLICATES FROM SORTED ARRAY
// TWO POINTERS
    class Solution {
        public int removeDuplicates(int[] nums) {
            // If the array is empty, there are no elements to process
            if (nums.length == 0) return 0;

            // Pointer j will track the position of the last unique element
            int j = 0;

            // Start from the second element and iterate through the array
            for (int i = 1; i < nums.length; i++) {
                // If the current element is different from the last unique element
                if (nums[i] != nums[j]) {
                    j++; // Move j to the next position
                    nums[j] = nums[i]; // Update the position with the new unique element
                }
            }

            // The number of unique elements is j + 1
            return j + 1;
        }
    }
 
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 283: MOVE ZEROES
// TWO POINTERS
    class Solution {
        public void moveZeroes(int[] nums) {
            // Pointer 'left' marks the position where the next non-zero element should go
            int left = 0;

            // Traverse the array with 'right' pointer
            for (int right = 0; right < nums.length; right++) {
                // When a non-zero element is found
                if (nums[right] != 0) {
                    // Only swap if 'left' and 'right' are different to avoid unnecessary operations
                    if (left != right) {
                        // Swap the elements at 'left' and 'right' positions
                        int temp = nums[right];
                        nums[right] = nums[left];
                        nums[left] = temp;
                    }

                    // Move 'left' forward to the next position for non-zero
                    left++;
                }
            }
        }
    }

    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 977: SQUARES OF A SORTED ARRAY
// TWO POINTERS
    class Solution {
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int[] result = new int[n]; // Result array to store sorted squares

            int left = 0, right = n - 1; // Pointers at the beginning and end of the array
            int position = n - 1; // Position to fill in the result array, starting from the end

            // Loop until the two pointers meet
            while (left <= right) {
                // Square of the left and right elements
                int LftSquare = nums[left] * nums[left];
                int RgtSquare = nums[right] * nums[right];

                // Place the larger square at the current position in result
                if (RgtSquare > LftSquare) {
                    result[position--] = RgtSquare;
                    right--; // Move right pointer inward
                } else {
                    result[position--] = LftSquare;
                    left++; // Move left pointer inward
                }
            }

            return result;
        }
    }

    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n)