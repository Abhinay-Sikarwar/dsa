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
    // 🧠 Space Complexity:  O(rows * columns) in the worst case.

    // 130: SURROUNDED REGIONS
// DFS TO MARK BORDER-CONNECTED 'O's AND FLIP THE REST

    class Solution {
        public void solve(char[][] board) {
            int rows = board.length;
            int cols = board[0].length;
    
            // Step 1: Mark border-connected 'O's as temporary ('T')
            for (int i = 0; i < rows; i++) {
                dfs(board, i, 0);           // left border
                dfs(board, i, cols - 1);    // right border
            }
            for (int j = 0; j < cols; j++) {
                dfs(board, 0, j);           // top border
                dfs(board, rows - 1, j);    // bottom border
            }
    
            // Step 2: Flip remaining 'O' -> 'X', restore 'T' -> 'O'
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X'; // capture region
                    if (board[i][j] == 'T') board[i][j] = 'O'; // restore safe cell
                }
            }
        }
    
        // DFS to mark safe 'O's connected to the border
        private void dfs(char[][] board, int i, int j) {
            int rows = board.length, cols = board[0].length;
    
            // Stop if out of bounds or not an 'O'
            if (i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] != 'O') return;
    
            board[i][j] = 'T'; // mark as safe
    
            // Explore 4 directions
            dfs(board, i + 1, j);
            dfs(board, i - 1, j);
            dfs(board, i, j + 1);
            dfs(board, i, j - 1);
        }
    }

    // ⏱️ Time Complexity:  O(rows * columns)
    // 🧠 Space Complexity:  O(rows * columns) in the worst case.