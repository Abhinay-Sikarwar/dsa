//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  9. GREEDY
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 871: MINIMUM NUMBER OF REFUELING STOPS
// GREEDY APPROACH: SELECT FUEL STATIONS USING A MAX-HEAP TO REACH TARGET WITH MINIMUM STOPS.

    import java.util.*;
    class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            // Max-heap to store fuel capacities of stations we've passed
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
            int fuel = startFuel;  // current reachable distance (in miles)
            int stops = 0;         // count of refuels
            int idx = 0;           // index for stations
    
            // While we haven't reached the target
            while (fuel < target) {
                // Add all stations we can currently reach into the heap
                while (idx < stations.length && stations[idx][0] <= fuel) {
                    maxHeap.offer(stations[idx][1]);
                    idx++;
                }
    
                // If no reachable stations to refuel from → impossible
                if (maxHeap.isEmpty()) return -1;
    
                // Refuel with the largest available fuel station
                fuel += maxHeap.poll();
                stops++;
            }
    
            return stops;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(n log n) — each station added/removed from heap once.
    // 🧠 SPACE COMPLEXITY: O(n) — heap stores up to all stations.