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

    // 121: best time to buy and sell stock
//SINGLE-PASS GREEDY APPROACH
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

    // 217: contains duplcate
 //created a hashset
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