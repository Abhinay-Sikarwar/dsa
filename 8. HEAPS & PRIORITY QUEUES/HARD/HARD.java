//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 8. HEAPS & PRIORITY QUEUES
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 295: FIND MEDIAN FROM DATA STREAM
// TWO HEAPS: MAX-HEAP LOWER HALF, MIN-HEAP UPPER HALF, BALANCE FOR MEDIAN.

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    
    import java.util.*;

    class MedianFinder {
        // Max-heap for the smaller half of numbers
        private PriorityQueue<Integer> lower;
        // Min-heap for the larger half of numbers
        private PriorityQueue<Integer> upper;
    
        public MedianFinder() {
            // Max-heap: largest element on top
            lower = new PriorityQueue<>((a, b) -> (b - a));
            // Min-heap: smallest element on top
            upper = new PriorityQueue<>();
        }
    
        public void addNum(int num) {
            // Step 1: Add to appropriate heap
            if (lower.isEmpty() || num <= lower.peek()) {
                lower.offer(num); // Goes to smaller half
            } else {
                upper.offer(num); // Goes to larger half
            }
    
            // Step 2: Balance heaps (size difference ≤ 1)
            if (lower.size() > upper.size() + 1) {
                upper.offer(lower.poll());
            } else if (upper.size() > lower.size()) {
                lower.offer(upper.poll());
            }
        }
    
        public double findMedian() {
            // If odd total count, median is top of larger heap
            if (lower.size() > upper.size()) {
                return lower.peek();
            }
            // If even, average of tops from both heaps
            return (lower.peek() + upper.peek()) / 2.0;
        }
    }

    //⏱️ time complexity: O(log N) for addNum, O(1) for findMedian
    //🧠 space complexity: O(N) for heaps.