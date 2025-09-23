//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 4. STACKS & QUEUES
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 85: MAXIMAL RECTANGLE
// CONSIDER EACH AS HISTOGRAM, MAINTAIN MONOTONIC STACK FOR MAX AREA
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            // Edge case: empty matrix
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return 0;
    
            int columns = matrix[0].length; // Total number of columns
            int rows = matrix.length; // Total number of rows
    
            // Array to store the heights of "histogram bars" for each column
            // +1 for a sentinel 0 at the end, which ensures all bars are processed
            int[] heights = new int[columns + 1];
            heights[columns] = 0; // Sentinel value
            int max = 0; // To keep track of the maximum rectangle area found
    
            // Iterate over each row of the matrix
            for (int row = 0; row < rows; row++) {
                Stack<Integer> stack = new Stack<>(); // Stack to maintain indices of increasing bar heights
    
                // Build/update the histogram for this row
                for (int i = 0; i < columns + 1; i++) {
                    if (i < columns) {
                        // If current cell is '1', increase the bar height
                        if (matrix[row][i] == '1') {
                            heights[i] += 1;
                        } else {
                            // If cell is '0', reset the height (break in rectangle)
                            heights[i] = 0;
                        }
                    }
    
                    // Process the histogram: maintain an increasing stack
                    // If the current height is greater or equal to the top of the stack, push it
                    if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                        stack.push(i);
                    } else {
                        // If current height is less than the stack's top:
                        // this means rectangles ending at the top bar must be calculated
                        while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                            int top = stack.pop(); // Get the height of the last bar
    
                            // Calculate the width of the rectangle:
                            // if stack is empty, it spans from 0 to i
                            // otherwise, it spans between the next top and current i
                            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
    
                            int area = heights[top] * width; // Area = height × width
                            max = Math.max(max, area); // Update max area if needed
                        }
    
                        // Push the current index to maintain the increasing stack
                        stack.push(i);
                    }
                }
            }
    
            return max; // Return the largest rectangle area found
        }
    }

    // ⏱️ Time Complexity:  O(m * n)       Where m = no of rows, n = no of columns
    // 🧠 Space Complexity:  O(n)          Where n = size heights array