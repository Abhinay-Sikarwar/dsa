//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 1. ARRAYS & STRINGS
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 37: SUDOKU SOLVER
// BACKTRACKING & BITMASKING
    class Solution {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];
        List<int[]> emptyCells = new ArrayList<>();

        public void solveSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.') {
                        int num = board[i][j] - '1';
                        setBit(i, j, num);
                    } else {
                        emptyCells.add(new int[]{i, j});
                    }
                }
            }

            backtrack(board, 0);
        }

        boolean backtrack(char[][] board, int idx) {
            if (idx == emptyCells.size()) return true;

            int[] cell = emptyCells.get(idx);
            int i = cell[0], j = cell[1];
            int boxIdx = (i / 3) * 3 + j / 3;

            int mask = ~(rows[i] | cols[j] | boxes[boxIdx]) & 0x1FF;

            while (mask != 0) {
                int bit = mask & -mask;
                int num = Integer.numberOfTrailingZeros(bit);

                setBit(i, j, num);
                board[i][j] = (char) (num + '1');

                if (backtrack(board, idx + 1)) return true;

                unsetBit(i, j, num);
                board[i][j] = '.';
                mask &= (mask - 1);
            }

            return false;
        }

        void setBit(int i, int j, int num) {
            int box = (i / 3) * 3 + j / 3;
            rows[i] |= 1 << num;
            cols[j] |= 1 << num;
            boxes[box] |= 1 << num;
        }

        void unsetBit(int i, int j, int num) {
            int box = (i / 3) * 3 + j / 3;
            rows[i] &= ~(1 << num);
            cols[j] &= ~(1 << num);
            boxes[box] &= ~(1 << num);
        }
    } 
    
    // ⏱️ Time Complexity:  O(9^k)        Where k = number of empty cells (≤ 81)
    // 🧠 Space Complexity:  O(k)