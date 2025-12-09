//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  10. DYNAMIC PROGRAMMING
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 403: FROG JUMP
// DP TRACKS VALID JUMPS PER STONE AND PROPAGATES FORWARD TO REACH THE LAST STONE.

    class Solution {
        public boolean canCross(int[] stones) {
            // If first jump is not possible, early exit
            if (stones[1] != 1) return false;
    
            // Map: stone position -> set of jumps that can land here
            Map<Integer, Set<Integer>> map = new HashMap<>();
            for (int stone : stones) {
                map.put(stone, new HashSet<>());
            }
    
            // Start at position 0 with "last jump" = 0 (no jump yet)
            map.get(0).add(0);
    
            // For every stone position
            for (int pos : stones) {
                // For every jump that landed us here
                for (int jump : map.get(pos)) {
    
                    // Try next steps: k-1, k, k+1
                    for (int nextJump = jump - 1; nextJump <= jump + 1; nextJump++) {
                        if (nextJump <= 0) continue; // cannot jump <= 0
    
                        int nextPos = pos + nextJump;
    
                        // Reached the last stone
                        if (nextPos == stones[stones.length - 1]) {
                            return true;
                        }
    
                        // If a stone exists at landing position, record this jump
                        if (map.containsKey(nextPos)) {
                            map.get(nextPos).add(nextJump);
                        }
                    }
                }
            }
    
            return false;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N²) IN WORST CASE EACH STONE MAY STORE MULTIPLE JUMP SIZES.
    // 🧠 SPACE COMPLEXITY: O(N²) MAP HOLDS SETS OF POSSIBLE JUMPS FOR EACH STONE.