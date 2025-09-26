//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 7. GRAPHS
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 200: NUMBER OF ISLANDS
// DFS FLOOD-FILL TO SINK CONNECTED LAND CELLS AND COUNT ISLANDS

    class Solution {
        public int numIslands(char[][] grid) {
            int rows = grid.length;
            int columns = grid[0].length;
            int islands = 0;
    
            // Traverse the entire grid
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // Found a land cell
                    if (grid[i][j] == '1') {
                        islands++;
                        sink(grid, i, j); // sink the island
                    }
                }
            }
            return islands;
        }
    
        private void sink(char[][] grid, int i, int j) {
            int row = grid.length;
            int column = grid[0].length;
    
            // boundary + water check
            if (i < 0 || j < 0 || i >= row || j >= column || grid[i][j] == '0') {
                return;
            }
    
            // mark current land as visited (sink it)
            grid[i][j] = '0';
    
            // explore neighbors
            sink(grid, i + 1, j); // down
            sink(grid, i - 1, j); // up
            sink(grid, i, j + 1); // right
            sink(grid, i, j - 1); // left
        }
    }

    // ⏱️ Time Complexity:  O(rows * columns)
    // 🧠 Space Complexity:  O(rows * columns) in the worst case,