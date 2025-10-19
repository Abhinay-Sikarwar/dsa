//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 8. HEAPS & PRIORITY QUEUES
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 347: TOP K FREQUENT ELEMENTS
// HASHMAP FOR FREQUENCIES, MIN-HEAP FOR TOP K ELEMENTS

    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // Count frequencies
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }
    
            // Use a min-heap based on frequencies
            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
    
            // Keep top k elements in the heap
            for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
                minHeap.add(entry);
                if (minHeap.size() > k) {
                    minHeap.poll(); // remove least frequent
                }
            }
    
            // Extract elements from heap
            int[] result = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                result[i] = minHeap.poll().getKey();
            }
    
            return result;
        }
    } 
    
    //⏱️ time complexity: O(N log k) where N is nums.length, K elements in heap.
    //🧠 space complexity: O(N) for freqMap.