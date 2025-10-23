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