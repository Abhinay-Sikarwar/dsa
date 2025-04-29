//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 3. TWO POINTERS & SLIDING WINDOW
// EASY

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

// MEDIUM
 
    // 16: 3SUM CLOSEST
// TWO POINTERS, SORTING
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums); // Sort the array to enable two-pointer traversal
            int closestSum = nums[0] + nums[1] + nums[2]; // Initialize with the first three elements

            // Iterate through each number as the first element in the triplet
            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1;                       // Second pointer
                int k = nums.length - 1;             // Third pointer
                
                // Optional: skip duplicates for the first element
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                // Two-pointer approach
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];

                    // If exact match found, return immediately
                    if (sum == target) return sum;

                    // Update closestSum if the current one is closer to the target
                    if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                        closestSum = sum;
                    }

                    // Move pointers based on comparison
                    if (sum < target) {
                        j++;         // Increase sum
                    } else {
                        k--;         // Decrease sum
                    }
                }
            }

            return closestSum;       // Return the closest found sum
        }
    }
    
    // ⏱️ Time Complexity:  O(n^2)
    // 🧠 Space Complexity:  O(1)

    // 18: 4SUM
// TWO POINTERS, SORTING, SKIPPING DUPLICATES
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> solution = new ArrayList<>();
            Arrays.sort(nums); // Sort the array to simplify duplicate handling and enable two-pointer strategy
            int length = nums.length;
    
            // Not enough numbers to form a quadruplet
            if (length < 4) return solution;
    
            // First number (i)
            for (int i = 0; i < length - 3; i++) {
                // Skip duplicate values for the first number
                if (i > 0 && nums[i] == nums[i - 1]) continue;
    
                // Early stopping: smallest possible sum is already greater than target
                if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
    
                // Skip this i if the largest possible sum with nums[i] is still too small
                if ((long) nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3] < target) continue;
    
                // Second number (j)
                for (int j = i + 1; j < length - 2; j++) {
                    // Skip duplicate values for the second number
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
    
                    // Use two pointers to find the remaining two numbers (k and l)
                    int k = j + 1;
                    int l = length - 1;
    
                    while (k < l) {
                        long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];
    
                        if (sum == target) {
                            // Found a valid quadruplet
                            solution.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                            k++;
                            l--;
    
                            // Skip duplicate values for the third number
                            while (k < l && nums[k] == nums[k - 1]) k++;
    
                            // Skip duplicate values for the fourth number
                            while (k < l && nums[l] == nums[l + 1]) l--;
                        } else if (sum < target) {
                            // Need a larger sum, move k forward
                            k++;
                        } else {
                            // Need a smaller sum, move l backward
                            l--;
                        }
                    }
                }
            }
    
            return solution;
        }
    }

    // ⏱️ Time Complexity:  O(n^3)
    // 🧠 Space Complexity:  O(1)    EXCLUDING OUTPUT O(k)   Where K = NO VALID QUADRUPLETS

    // 438: FIND ALL ANAGRAMS IN A STRING
// SLIDING WINDOW, TWO POINTERS, FREQUENCY ARRAY
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> solution = new ArrayList<>();
    
            int sLen = s.length(), pLen = p.length();
            // Edge case: if pattern is longer than the string or either is empty
            if (sLen == 0 || pLen == 0 || sLen < pLen) {
                return solution;
            }
    
            int[] charCount = new int[26]; // Count of each character in pattern 'p'
            for (char c : p.toCharArray()) {
                charCount[c - 'a']++;
            }
    
            int start = 0, end = 0, required = pLen; // 'required' tracks how many characters still need to match
    
            // Build the initial window of size 'pLen'
            for (end = 0; end < pLen; end++) {
                int idx = s.charAt(end) - 'a';
                charCount[idx]--;
                if (charCount[idx] >= 0) required--; // Decrease required only if char was actually needed
            }
    
            // If the first window is an anagram, add index 0
            if (required == 0) {
                solution.add(0);
            }
    
            // Slide the window one character at a time
            while (end < sLen) {
                // Remove the character going out of the window
                int outIdx = s.charAt(start++) - 'a';
                if (charCount[outIdx] >= 0) required++; // If it was part of p, we now need it again
                charCount[outIdx]++;
    
                // Add the character coming into the window
                int inIdx = s.charAt(end++) - 'a';
                charCount[inIdx]--;
                if (charCount[inIdx] >= 0) required--; // If it was needed, we fulfilled one requirement
    
                // If all requirements met, it's an anagram
                if (required == 0) {
                    solution.add(start); // 'start' is the index where the current window begins
                }
            }
    
            return solution;
        }
    }

    // ⏱️ Time Complexity:  O(n)     Where n = s.lenght()
    // 🧠 Space Complexity:  O(1)

    // 567: PERMUTATION IN STRING
// TWO POINTERS, SLIDING WINDOW, FREQUENCY ARRAY
    class Solution {
        public boolean checkInclusion(String s1, String s2) {
             // Edge case checks
            if (s1 == null || s2 == null) return false;
        
            int s1Len = s1.length(), s2Len = s2.length();
            if (s1Len == 0 || s2Len == 0 || s2Len < s1Len) {
                return false;
            }

            int[] charCount = new int[26];

            // Count frequency of each character in s1
            for (char c : s1.toCharArray()) {
                charCount[c - 'a']++;
            }

            int start = 0, end = 0, required = s1Len;
        
            // Process the first window
            for (end = 0; end < s1Len; end++) {
                int idx = s2.charAt(end) - 'a';
                charCount[idx]--;
                if (charCount[idx] >= 0) required--;
            }

            // If all required characters matched in the first window 
            if (required == 0) {
                return true;
            }

            // Slide the window across s2
            while (end < s2Len) {
                // Character going out of the window
                int outIdx = s2.charAt(start++) - 'a';
                // First check if it becomes > 0, which means we now need one more of that character
                if (charCount[outIdx] >= 0) required++;
                // Then, increment the count
                charCount[outIdx]++;

                // Character coming into the window
                int inIdx = s2.charAt(end++) - 'a';
                charCount[inIdx]--;
                if (charCount[inIdx] >= 0) required--;

                // If all characters matched
                if (required == 0) {
                    return true;
                }
            }

            return false; 
        }
    }
    
    // ⏱️ Time Complexity:  O(n)          where n = s2.length()
    // 🧠 Space Complexity:  O(1)

    // 424: LONGEST REPEATING CHARACTER REPLACEMENT
// TWO POINTERS, SLIDING WINDOW, FREQUENCY ARRAY
    class Solution {
        public int characterReplacement(String s, int k) {
            if (s.length() == 1) return 1;
            
            int[] count = new int[26]; // Frequency of characters A-Z
            int maxFreq = 0; // Max frequency in the current window
            int left = 0; // Left pointer of the window
            int maxLength = 0; // Result
    
            for (int right = 0; right < s.length(); right++) {
                // Update the frequency of the right character
                count[s.charAt(right) - 'A']++;
    
                // Update max frequency character count in the current window
                maxFreq = Math.max(maxFreq, count[s.charAt(right) - 'A']);
    
                // Check if we need to shrink the window
                int windowSize = right - left + 1;
                if (windowSize - maxFreq > k) {
                    // Shrink from the left
                    count[s.charAt(left) - 'A']--;
                    left++;
                }
    
                // Update max length if current window is valid
                maxLength = Math.max(maxLength, right - left + 1);
            }
    
            return maxLength;
        } 
    } 
    
    // ⏱️ Time Complexity:  O(n)            Where n = s.length()
    // 🧠 Space Complexity:  O(1)

    // 209: MINIMUM SIZE SUBARRAY SUM
// TWO POINTERS, SLIDING WINDOW, TERNARY OPERATOR
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            // Initialize resultlength with the maximum possible value.
            // This will help us track the minimum length found.
            int resultlength = Integer.MAX_VALUE, length = nums.length;
    
            // Left pointer of the sliding window
            int left = 0;
    
            // Current sum of the elements in the window
            int currentSum = 0;
    
            // Move the right pointer to expand the window
            for (int right = 0; right < length; right++) {
                // Add the current number to the window's sum
                currentSum += nums[right];
    
                // Shrink the window from the left as long as the sum is >= target
                while (currentSum >= target) {
                    // Update resultlength if this window is smaller than previously recorded ones
                    if (right - left + 1 < resultlength) {
                        resultlength = right - left + 1;
                    }
    
                    // Subtract the leftmost element from the window and move the left pointer forward
                    currentSum -= nums[left++];
                }
            }
    
            // If no valid subarray was found, return 0. Otherwise, return the minimum length found.
            return resultlength != Integer.MAX_VALUE ? resultlength : 0;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 1004: MAX CONSECUTIVE ONES III