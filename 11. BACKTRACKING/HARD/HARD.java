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
// BACKTRACK TO FILL EMPTY CELLS, USING BITMASKS FOR CONFLICT CHECKING.

    class Solution {
        // Bitmasks to track used numbers in rows, cols, and 3x3 boxes
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];
        
        // List to store positions of empty cells
        List<int[]> emptyCells = new ArrayList<>();
    
        public void solveSudoku(char[][] board) {
            // Initialize bitmasks and record empty cells
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1'; // convert char to 0–8
                        setBit(i, j, num);           // mark number as used
                    } else {
                        emptyCells.add(new int[] { i, j });
                    }
                }
            }
            backtrack(board, 0); // start solving
        }
    
        boolean backtrack(char[][] board, int idx) {
            // All cells filled → solved
            if (idx == emptyCells.size()) return true;
    
            int[] cell = emptyCells.get(idx);
            int i = cell[0], j = cell[1];
            int boxIdx = (i / 3) * 3 + j / 3;
    
            // Mask of available numbers (1 bits = possible candidates)
            int mask = ~(rows[i] | cols[j] | boxes[boxIdx]) & 0x1FF;
    
            // Try each available number
            while (mask != 0) {
                int bit = mask & -mask;              // extract lowest set bit
                int num = Integer.numberOfTrailingZeros(bit); // get number index
    
                setBit(i, j, num);                   // mark as used
                board[i][j] = (char) (num + '1');    // place digit
    
                if (backtrack(board, idx + 1)) return true; // recurse
    
                // Backtrack: undo placement
                unsetBit(i, j, num);
                board[i][j] = '.';
                mask &= (mask - 1);                  // remove tried bit
            }
            return false; // no valid number found
        }
    
        // Mark number as used in row, col, and box
        void setBit(int i, int j, int num) {
            int box = (i / 3) * 3 + j / 3;
            rows[i] |= 1 << num;
            cols[j] |= 1 << num;
            boxes[box] |= 1 << num;
        }
    
        // Unmark number (for backtracking)
        void unsetBit(int i, int j, int num) {
            int box = (i / 3) * 3 + j / 3;
            rows[i] &= ~(1 << num);
            cols[j] &= ~(1 << num);
            boxes[box] &= ~(1 << num);
        }
    }

    // ⏱️ TIME COMPLEXITY: O(9 ^ m)   // m = number of empty cells, each can try up to 9 digits.
    // 🧠 SPACE COMPLEXITY: O(m)      // 9×9 empty board + 3×9 bitmask arrays + recursion stack.

    // 301: REMOVE INVALID PARENTHESES
// BACKTRACK TO EXPLORE DELETION/KEPT OPTIONS, TRACKING COUNTS TO ENSURE VALIDITY.

    class Solution {
        private Set<String> result = new HashSet<>();
    
        public List<String> removeInvalidParentheses(String s) {
            int leftRem = 0, rightRem = 0;
    
            // First determine how many parentheses must be removed
            for (char ch : s.toCharArray()) {
                if (ch == '(') {
                    leftRem++;
                } else if (ch == ')') {
                    if (leftRem > 0)
                        leftRem--;
                    else
                        rightRem++;
                }
            }
    
            backtrack(s, 0, new StringBuilder(), 0, leftRem, rightRem);
    
            return new ArrayList<>(result);
        }
    
        private void backtrack(String s, int idx, StringBuilder path,
                int open, int leftRem, int rightRem) {
    
            // reached end
            if (idx == s.length()) {
                if (open == 0 && leftRem == 0 && rightRem == 0) {
                    result.add(path.toString());
                }
                return;
            }
    
            char ch = s.charAt(idx);
            int len = path.length();
    
            // Option 1: delete current parenthesis if possible
            if (ch == '(' && leftRem > 0) {
                backtrack(s, idx + 1, path, open, leftRem - 1, rightRem);
            } else if (ch == ')' && rightRem > 0) {
                backtrack(s, idx + 1, path, open, leftRem, rightRem - 1);
            }
    
            // Option 2: keep character
            path.append(ch);
    
            if (ch != '(' && ch != ')') {
                // always keep letters
                backtrack(s, idx + 1, path, open, leftRem, rightRem);
    
            } else if (ch == '(') {
                // keeping '(' increases open balance
                backtrack(s, idx + 1, path, open + 1, leftRem, rightRem);
    
            } else if (open > 0) {
                // keeping ')' only allowed if matching '(' exists
                backtrack(s, idx + 1, path, open - 1, leftRem, rightRem);
            }
    
            // undo choice
            path.setLength(len);
        }
    }

    // ⏱️ TIME COMPLEXITY: O(2^n)   // Each parenthesis can be either deleted or kept.
    // 🧠 SPACE COMPLEXITY: O(n)    // Recursion depth + path buffer.