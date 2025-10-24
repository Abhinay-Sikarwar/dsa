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