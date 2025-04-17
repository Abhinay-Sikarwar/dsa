//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE

// 1. ARRAYS & STRING
// EASY
     
    // 1: TWO SUM
// BRUTE FORCE 
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            int n = nums.length;
            for(int i = 0; i < n - 1; i++){
                for(int j = i + 1; j < n; j++){
                    if(nums[i] + nums[j] == target){
                        return new int[]{i,j};
                    }
                }
            }
            return new int[]{};//case where target not found
        }
    }
 
    //⏱️ time complexity: O(n^2)
    //🧠 space complexity: O(1)

    // ONE-PASS HASHTABLE
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> numMap = new HashMap<>();
            int n = nums.length;
    
            for (int i = 0; i < n; i++) {
                int complement = target - nums[i];
                if (numMap.containsKey(complement)) {
                    return new int[] { numMap.get(complement), i };
                }
                numMap.put(nums[i], i);
            }
    
            return new int[] {};//when target is not found
        }
    }

    //⏱️ time complexity: O(n)
    //🧠 space complexity: O(n)

    // 121: BEST TIME TO BUY AND SELL STOCKS
// SINGLE-PASS GREEDY APPROACH
    class Solution {
        public int maxProfit(int[] prices) {
            int buy = prices[0];
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] < buy) {
                    buy = prices[i];
                } else if (prices[i] - buy > profit){
                    profit = prices[i] - buy;
                }
            }
            return profit;
        }
    }

    //⏱️ Time Complexity: O(n)
    //🧠 Space Complexity: O(1)

    // 217: CONTAINS DUPLICATE
 // created a hashset
     class Solution {
        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> occur = new HashSet<>();
            for (int num : nums) {
                if (occur.contains(num))
                      return true;
                occur.add(num);    
           }
           return false;  
        }
    }

    //⏱️ Time Complexity: O(n)
    //🧠 Space Complexity: O(n)

    // 242: VALID ANAGRAM
 // sort the string
     class Solution {
        public boolean isAnagram(String s, String t) {
            char[] schars = s.toCharArray();
            char[] tchars = t.toCharArray();

            Arrays.sort(schars);
            Arrays.sort(tchars);

            return Arrays.equals(schars,tchars);
        }
    }
    
    // ⏱️ Time Complexity: O(n log n)
    // 🧠 Space Complexity: O(n)
    
 // hashmap
     class Solution {
        public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> occurence = new HashMap<>();
        
            for (char x : s.toCharArray()) {
            occurence.put(x, occurence.getOrDefault(x, 0) + 1);
            }
        
            for (char x : t.toCharArray()) {
            occurence.put(x, occurence.getOrDefault(x, 0) - 1);
            }
        
            for (int val : occurence.values()) {
               if (val != 0) {
                return false;
               }
            }
        
            return true;
        }
    }

    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(n)

    //VALID PALINDROME
// TWO POINTERS    
    class Solution {
        public boolean isPalindrome(String s) {
           if (s.isEmpty()) {
               return true;
           }
         
           int first = 0;
           int last = s.length() - 1;
           while (first <= last) {
               char currfirst = s.charAt(first);
               char currlast = s.charAt(last);
               if (!Character.isLetterOrDigit(currfirst)) {
                   first++;
               } else if (!Character.isLetterOrDigit(currlast)) {
                   last--;
               } else {
                   if (Character.toLowerCase(currfirst) != Character.toLowerCase(currlast)) {
                       return false;
                   }
                   first++;
                   last--;
               }
           }
        return true;
        }
    }
    
    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(1

    // 344: REVERSE STRING
// TWO POINTERS
    class Solution {
        public char[] reverseString(char[] s) {

           int left = 0;
           int right = s.length - 1;
           while (left < right) {
               char temp = s[left];
               s[left] = s[right];
               s[right] = temp;
               left++;
               right--;
           }
           
           return  s;
        }
    }

    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(1)

    // 14: LONGEST COMMON PREFIX
 // SORTING AND PREFIX
    class Solution {
        public String longestCommonPrefix(String[] strs) {
           StringBuilder prefix = new StringBuilder();
           Arrays.sort(strs);
           String first = strs[0];
           String last = strs[strs.length - 1];
           for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
             if (first.charAt(i) != last.charAt(i)){
                  return prefix.toString();
             }
             prefix.append(first.charAt(i));
           }
           return prefix.toString();
        }
    }

    // ⏱️ Time Complexity: O(n log n + m)       //Let m be the length of the shortest string among first and last.
    // 🧠 Space Complexity: O(m)               //prefix is a StringBuilder, which at most will store m characters.
    
    // 53: MAXIMUM SUBARRAY
// KADANE'S ALGORITHM
    class Solution {
        public int maxSubArray(int[] nums) {
           int MaxSum = Integer.MIN_VALUE;
           int CurrentSum = 0;

             for (int i = 0; i < nums.length; i++) {
              CurrentSum += nums[i];

              if (CurrentSum > MaxSum) {
                  MaxSum = CurrentSum;
              }

              if (CurrentSum < 0) {
                  CurrentSum = 0;
              }
           }
           return MaxSum;
        }
    }

    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(1)

// MEDIUM
    
    // 8: STRING TO INTEGER(ATOI)
// ATOI FORMAT(LIKE C,C++) (WITH LONG DATATYPE)
    class Solution {
        public int myAtoi(String s) {
            String str = s.trim();
            if (str.isEmpty())
                return 0;
            int sign = 1, i = 0;
            long result = 0;

            if (str.charAt(0) == '-') {
                sign = -1;
                i++;
            } else if (str.charAt(0) == '+') {
                i++;
            }

            while (i < str.length()) {
                char w = str.charAt(i);
                if (w < '0' || w > '9')
                    break;

                result = result * 10 + (w - '0');

                if (sign * result > Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                if (sign * result < Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;

                i++;
            }
            return (int) (sign * result);
        }
    }

    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(1)

 // ONE THAT DON'T USE LONG DATATYPE
    class Solution {
        public int myAtoi(String s) {
            if (s == null || s.isEmpty()) return 0;

            s = s.trim(); 
            if (s.isEmpty()) return 0;

            int i = 0, sign = 1, result = 0;
            int n = s.length();

            if (s.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (s.charAt(i) == '+') {
                i++;
            }

            while (i < n) {
                char c = s.charAt(i);
                if (c < '0' || c > '9') break;

                int digit = c - '0';

                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                result = result * 10 + digit;
                i++;
            }

            return result * sign;
        }
    }
    
    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(1)

    // 3:LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
 // SLIDING WINDOW & HASHSET
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length();
            int maxlength = 0;
            Set<Character> WindowSet = new HashSet<>();
            int left = 0;

            for (int right = 0; right < n; right++) {
                if (!WindowSet.contains(s.charAt(right))) {
                    WindowSet.add(s.charAt(right));
                    maxlength = Math.max(maxlength, right - left + 1);
                } else {
                    while (WindowSet.contains(s.charAt(right))) {
                        WindowSet.remove(s.charAt(left));
                        left++;
                    }
                    WindowSet.add(s.charAt(right));
                }
            }
            return maxlength;
        }
    }

    // ⏱️ Time Complexity: O(n)                  n = length of the string
    // 🧠 Space Complexity: O(min(n, m))         m = size of character set

    // 49: GROUP ANAGRAMS
//HASMAP & SORTING
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> AnaMap = new HashMap<>();

            for (String word : strs) {
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String SortedKey = new String(chars);

                if (!AnaMap.containsKey(SortedKey)) {
                    AnaMap.put(SortedKey, new ArrayList<>());
                }

                AnaMap.get(SortedKey).add(word);
            }

            return new ArrayList<>(AnaMap.values());
        }
    }
    
    // ⏱️ Time Complexity: O(n * k log k)              n = number of strings in input
    // 🧠 Space Complexity: O(n * k)                   k = average length of a string

    // 11: CONTAINER WITH MOST WATER
// TWO POINTERS
    class Solution {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int maxarea = 0;

            while (left < right) {
            int currentarea = Math.min(height[left] , height[right]) * (right - left);
                maxarea = Math.max(maxarea , currentarea);

                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return maxarea;
        }
    }

    // ⏱️ Time Complexity: O(n)
    // 🧠 Space Complexity: O(1)

    // 15: 3SUM
// TWO POINTER, SORTING, SKIP DUPLICATES
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> solution = new ArrayList<>();
            Arrays.sort(nums);

            for (int i =0; i < nums.length - 2; i++) {
                if (i>0 && nums[i] == nums[i - 1]) {
                    continue;
                }

                int j = i + 1;
                int k = nums.length - 1;

                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        solution.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while (j < k && nums[j] == nums[j + 1]) {
                            j++;
                        }

                        while (j < k && nums[k] == nums[k - 1]) {
                            k--;
                        }

                        j++;
                        k--;
                    } else if (sum < 0) {
                    
                        j++;
                    } else {
                    
                        k--;
                    }
                }        
            }

            return solution;
        }
    } 
    
    // ⏱️ Time Complexity: O(n^2)
    // 🧠 Space Complexity(auxiliary): O(1)
    // 🧠 Space Complexity(with output): O(k), where k = number of triplets

    // 33: SEARCH IN ROTATED SORTED ARRAY
// UPDATED BINARY SEARCH
    class Solution {
        public int search(int[] nums, int target) {
            int close = 0, far = nums.length - 1;

            while (close <= far) {
                int mid = (close + far) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[close] <= nums[mid]) {
                    if (nums[close] <= target && target < nums[mid]) {
                        far = mid - 1;
                    } else {
                        close = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[far]) {
                        close = mid + 1;
                    } else {
                            far = mid - 1;
                    }
                }
            }

            return -1;
        }
    }
    
    // ⏱️ Time Complexity:  O(log n)
    // 🧠 Space Complexity:  O(1)

    // 153: FIND MINIMUM IN ROTATED SORTED ARRAY
// UPDATED BINARY SEARCH
    class Solution {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;

            if (nums[left] < nums[right]) {
                return nums[left];
            }

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid; 
                }
            }
            return nums[left];
        }
    } 
    
    // ⏱️ Time Complexity:  O(log n)
    // 🧠 Space Complexity:  O(1)

    // 238: PRODUCT OF ARRAY EXCEPT SELF
// PREFIX,SUFFIX,TWO PASS
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] solution = new int[n];

            solution[0] = 1;

            for (int i = 1; i < n; i++) {
                solution[i] = solution[i - 1] * nums[i - 1];
            }

            int prevSuffix = 1;

            for (int i = n - 2; i >= 0; i--) {
                prevSuffix *= nums[i + 1];
                solution[i] *= prevSuffix;
            }

            return solution;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 739: DAILY TEMPRETURES
// USING STACK  
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] results = new int[temperatures.length];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < temperatures.length; i++) {
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    results[stack.peek()] = i - stack.pop();
                }

                stack.push(i);
            }

            return results;
        }
    }   

     // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n)
