//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 3. TWO POINTERS & SLIDING WINDOW
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 239: SLIDING WINDOW MAXIMUM
// MONOTONIC DEQUE
    public class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            // early exit in case of 
            if (nums.length == 1) return nums;
            int n = nums.length;
    
            // Result array to store the maximum of each window
            int[] result = new int[n - k + 1];
    
            // Deque to store indices of useful elements in the current window
            // It helps us keep track of potential maximums in decreasing order
            Deque<Integer> deque = new ArrayDeque<>();
    
            for (int i = 0; i < n; i++) {
    
                // Step 1: Remove indices that are outside the current window (i - k + 1 is the window's left bound)
                if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
    
                // Step 2: Remove indices from the back of the deque if their values are less than nums[i]
                // They can't be maximum if nums[i] is greater
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }
    
                // Step 3: Add the current index to the deque
                deque.offerLast(i);
    
                // Step 4: If our window has hit size k, record the max (at the front of the deque)
                // The first element of deque is always the largest in the current window
                if (i >= k - 1) {
                    result[i - k + 1] = nums[deque.peekFirst()];
                }
            }
    
            return result;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)              Where n = nums.length
    // 🧠 Space Complexity:  O(k)             Where k = size of window

    // 992: SUBARRAYS WITH K DIFFERENT INTEGERS
// SLIDING WINDOW, FREQUENCY MAP
    class Solution {
    
        // Returns the number of subarrays with exactly k distinct integers
        public int subarraysWithKDistinct(int[] nums, int k) {
            // Use the sliding window trick:
            // Exactly k distinct = At most k distinct - At most (k - 1) distinct
            return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
        }
    
        // Helper method: counts subarrays with at most k distinct integers
        private int atMostKDistinct(int[] nums, int k) {
            Map<Integer, Integer> freqMap = new HashMap<>(); // Tracks frequency of elements in window
            int left = 0; // Start of the window
            int totalSubarrays = 0; // Running total of valid subarrays
    
            // Expand the window to the right
            for (int right = 0; right < nums.length; right++) {
                int rightVal = nums[right];
                freqMap.put(rightVal, freqMap.getOrDefault(rightVal, 0) + 1);
    
                // If this is a new distinct value, reduce the allowed distinct count
                if (freqMap.get(rightVal) == 1) {
                    k--;
                }
    
                // If window has more than k distinct elements, shrink from the left
                while (k < 0) {
                    int leftVal = nums[left];
                    freqMap.put(leftVal, freqMap.get(leftVal) - 1);
    
                    // If an element's count drops to 0, we removed a distinct value
                    if (freqMap.get(leftVal) == 0) {
                        k++;
                    }
    
                    left++; // Shrink the window
                }
    
                // All subarrays ending at 'right' and starting from 'left' are valid
                totalSubarrays += right - left + 1;
            }
    
            return totalSubarrays;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n)