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
// TWO POINTERS
    class Solution {
        public int[] sortedSquares(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            int left = 0, right = n - 1;
            int position = n - 1;

            while (left <= right) {
                int RgtSquare = nums[left] * nums[left];
                int LftSquare = nums[right] * nums[right];

                if (LftSquare > RgtSquare) {
                    result[position--] = LftSquare;
                    left++;
                } else {
                    result[position--] = RgtSquare;
                    right--;
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
            Arrays.sort(nums);
            int closestSum = nums[0] + nums[1] + nums[2];

            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];

                    if (sum == target) return sum;

                    if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                        closestSum = sum;
                    }

                    if (sum < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }

            return closestSum;
        }
    }
    
    // ⏱️ Time Complexity:  O(n^2)
    // 🧠 Space Complexity:  O(1)

    // 18: 4SUM
// TWO POINTERS, SORTING, SKIPPING DUPLICATES
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> solution = new ArrayList<>();
            Arrays.sort(nums);
            int length = nums.length;

            if (length < 4)
                return solution;

            for (int i = 0; i < length - 3; i++) {
                if (i > 0 && nums[i - 1] == nums[i])
                    continue;

                if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target)
                    break;
                if ((long) nums[i] + nums[length - 1] + nums[length - 2] + nums[length - 3] < target)
                    continue;

                for (int j = i + 1; j < length - 2; j++) {
                    if (j > i + 1 && nums[j - 1] == nums[j])
                        continue;

                    int k = j + 1, l = length - 1;

                    while (k < l) {
                        long sum = nums[i] + nums[j] + nums[k] + nums[l];

                        if (sum == target) {
                            solution.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                            k++;
                            l--;

                            while (k < l && nums[k - 1] == nums[k])
                                k++;
                            while (k < l && nums[l + 1] == nums[l])
                                l--;
                        } else if (sum < target)
                            k++;
                        else
                            l--;

                    }
                }
            }

            return solution;
        }
    }
    
    // ⏱️ Time Complexity:  O(n^3)
    // 🧠 Space Complexity:  O(1)    EXCLUDING OUTPUT O(k)   WHERE K = NO VALID QUADRUPLETS

    // 438: FIND ALL ANAGRAMS IN A STRING
// SLIDING WINDOW, TWO POINTERS, FREQUENCY ARRAY
    class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> solution = new ArrayList<>();

            int sLen = s.length(), pLen = p.length();
            if (sLen == 0 || pLen == 0 || sLen < pLen) {
                return solution;
            }

            int[] charCount = new int[26];
            for (char c : p.toCharArray()) {
                charCount[c - 'a']++;
            }

            int start = 0, end = 0, required = pLen;

            for (end = 0; end < pLen; end++) {
                int idx = s.charAt(end) - 'a';
                charCount[idx]--;
                if (charCount[idx] >= 0) required--;
            }

            if (required == 0) {
                solution.add(0);
            }

            while (end < sLen) {
                int outIdx = s.charAt(start++) - 'a';
                if (charCount[outIdx] >= 0) required++;
                charCount[outIdx]++;

                int inIdx = s.charAt(end++) - 'a';
                charCount[inIdx]--;
                if (charCount[inIdx] >= 0) required--;

                if (required == 0) {
                    solution.add(start);
                }
            }

            return solution;
        }
    }

    // ⏱️ Time Complexity:  O(n)     WHERE n = s.lenght()
    // 🧠 Space Complexity:  O(1)

