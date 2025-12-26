//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  11. BACKTRACKING
//------------------------------------------------------------- HARD -----------------------------------------------------------------

    // 51: N-QUEENS
// BACKTRACK TO GENERATE ALL VALID CONFIGURATIONS, USING BOOLEAN ARRAYS FOR CONFLICT CHECKING.

    class Solution {
        private List<List<String>> result = new ArrayList<>();
    
        public List<List<String>> solveNQueens(int n) {
    
            int[] board = new int[n];             // board[row] = col of queen
    
            boolean[] col = new boolean[n];       // columns used
            boolean[] diag1 = new boolean[2 * n]; // main diag (r - c + n)
            boolean[] diag2 = new boolean[2 * n]; // anti diag (r + c)
    
            backtrack(0, n, board, col, diag1, diag2);
    
            return result;
        }
    
        private void backtrack(int row, int n, int[] board,
                boolean[] col, boolean[] diag1, boolean[] diag2) {
    
            // placed queens in all rows → valid solution
            if (row == n) {
                result.add(buildBoard(board, n));
                return;
            }
    
            for (int c = 0; c < n; c++) {
    
                int d1 = row - c + n;
                int d2 = row + c;
    
                // conflict check → skip
                if (col[c] || diag1[d1] || diag2[d2])
                    continue;
    
                // place queen
                board[row] = c;
                col[c] = diag1[d1] = diag2[d2] = true;
    
                // explore
                backtrack(row + 1, n, board, col, diag1, diag2);
    
                // remove queen (backtrack)
                col[c] = diag1[d1] = diag2[d2] = false;
            }
        }
    
        // convert board[row] = col → string representation
        private List<String> buildBoard(int[] board, int n) {
            List<String> config = new ArrayList<>();
    
            for (int r = 0; r < n; r++) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[board[r]] = 'Q';
                config.add(new String(row));
            }
    
            return config;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(N!)   // Strong pruning, explore valid queen permutations.
    // 🧠 SPACE COMPLEXITY: O(N)   // Recursion depth + board + constraint arrays.

    // 37: SUDOKU SOLVER