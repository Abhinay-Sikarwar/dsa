//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  8. HEAPS & PRIORITY QUEUES
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

    // 378: KTH SMALLEST ELEMENT IN A SORTED MATRIX
// MIN-HEAP TO TRACK NEXT SMALLEST ELEMENT

    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
    
            // Min-heap: each element is [value, row, col]
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    
            // Add first element of each row to the heap
            for (int i = 0; i < n; i++) {
                pq.offer(new int[] { matrix[i][0], i, 0 });
            }
    
            // Extract-min k times
            int val = 0;
            for (int count = 0; count < k; count++) {
                int[] cur = pq.poll();
                val = cur[0];
                int row = cur[1], col = cur[2];
    
                // Push next element in the same row
                if (col + 1 < n) {
                    pq.offer(new int[] { matrix[row][col + 1], row, col + 1 });
                }
            }
            return val;
        }
    }

    //⏱️ time complexity: O(k log n) where n is matrix.length.
    //🧠 space complexity: O(n) for the heap.

    // 973: K-CLOSEST POINTS TO ORIGIN
// MAX-HEAP TO TRACK K CLOSEST POINTS

    class Solution {
        public int[][] kClosest(int[][] points, int k) {
            // Max-heap storing points by descending distance from origin
            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
            );
    
            // Add each point to the heap
            for (int[] p : points) {
                pq.offer(p);
                // Keep only k closest points in the heap
                if (pq.size() > k) pq.poll();
            }
    
            // Extract k closest points from the heap
            int[][] res = new int[k][2];
            for (int i = 0; i < k; i++) {
                res[i] = pq.poll();
            }
    
            return res;
        }
    }

    //⏱️ time complexity: O(N log k) where N is points.length.
    //🧠 space complexity: O(k) for the heap.