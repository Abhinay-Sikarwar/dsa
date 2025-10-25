//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 9. GREEDY
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    //  134. GAS STATION
// IF TOTAL GAS ≥ TOTAL COST, START AFTER LAST NEGATIVE TANK POINT; ELSE RETURN -1.

    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int total = 0; // total net gain
            int tank = 0;  // current tank balance
            int start = 0; // candidate starting station
    
            for (int i = 0; i < gas.length; i++) {
                int gain = gas[i] - cost[i]; // Net gain at each step
                total += gain;
                tank += gain;
    
                // Can't reach next station from current start
                if (tank < 0) {
                    start = i + 1; // next station might be a better start
                    tank = 0;      // reset fuel
                }
            }
    
            // Check overall feasibility
            return total >= 0 ? start : -1;
        }
    }

    //⏱️ TIME COMPLEXITY: O(N) single pass through all stations.
    //🧠 SPACE COMPLEXITY: O(1) constant extra variables.

    // 45: JUMP GAME II
// GREEDY APPROACH: TRACK FARTHEST REACHABLE INDEX AND INCREMENT JUMPS WHEN CURRENT END IS REACHED.

    class Solution {
        public int jump(int[] nums) {
            int jumps = 0;
            int currentEnd = 0;   // end of current range
            int farthest = 0;     // farthest we can reach in current range
    
            // We don't need to process the last index (no jump needed from there)
            for (int i = 0; i < nums.length - 1; i++) {
                farthest = Math.max(farthest, i + nums[i]);
    
                // If we’ve reached the end of our current jump range
                if (i == currentEnd) {
                    jumps++;               // must make another jump
                    currentEnd = farthest; // extend range
    
                    // Early stop if we can already reach the end
                    if (currentEnd >= nums.length - 1) break;
                }
            }
    
            return jumps;
        }
    }

    //⏱️ TIME COMPLEXITY: O(N) single pass through the array.
    //🧠 SPACE COMPLEXITY: O(1) constant extra variables.

    // 406. QUEUE RECONSTRUCTION BY HEIGHT
// SORT BY HEIGHT DESC, THEN INSERT EACH PERSON AT INDEX = K.

    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            // Sort by height desc, and if equal height, by k asc
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
            for(int[] p : people) queue.offer(p);
    
            // Insert each person into the list at index = k
            List<int[]> reOrdList = new LinkedList<>();
            while(!queue.isEmpty()){
                int[] person = queue.poll();
                reOrdList.add(person[1], person);  // insert at index k
            }
    
            // Convert list to array
            return reOrdList.toArray(new int[people.length][2]);
        }
    }

    //⏱️ TIME COMPLEXITY: O(N log N) for sorting and O(N^2) for insertions in the worst case.
    //🧠 SPACE COMPLEXITY: O(N) for the priority queue and list.

    // 452. MINIMUM NUMBER OF ARROWS TO BURST BALLOONS
// SORT BY END COORDINATE, GREEDILY SHOOT ARROWS AT THE END OF BALLOONS.

    class Solution {
        public int findMinArrowShots(int[][] points) {
            // Sort balloons by their ending x-coordinate
            Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
    
            int arrows = 1;          // At least one arrow is needed
            int currentEnd = points[0][1]; // Shoot arrow at the end of first balloon
    
            for (int i = 1; i < points.length; i++) {
                // If the current balloon starts after the last arrow's end, we need a new arrow
                if (points[i][0] > currentEnd) {
                    arrows++;
                    currentEnd = points[i][1]; // Shoot at the end of this balloon
                }
                // Otherwise, the current balloon is already burst by the last arrow
            }
    
            return arrows;
        }
    }

    //⏱️ TIME COMPLEXITY: O(N log N) for sorting the balloons.
    //🧠 SPACE COMPLEXITY: O(1) constant extra space.