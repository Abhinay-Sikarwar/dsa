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

    //LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
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