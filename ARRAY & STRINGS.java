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
    
    // 53: MAXIMUM SUBARRAY
//KADANE'S ALGORITHM
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