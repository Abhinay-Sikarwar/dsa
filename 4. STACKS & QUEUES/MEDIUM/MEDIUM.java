//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 4. STACKS & QUEUES
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 150: EVALUATE REVERSE POLISH NOTATION
// STACK FOR POSTFIX, MAINTAIN ORDER WHILE EVALUATING EXPRESSION

// import java.util.Stack;
    class Solution {
        public int evalRPN(String[] tokens) {
            // Stack to store operands
            Stack<Integer> stack = new Stack<>();
    
            // Iterate over each token in the input
            for (String token : tokens) {
                // Check if the token is an operator
                if (token.equals("+")) {
                    // Pop top two elements and apply addition
                    stack.push(stack.pop() + stack.pop());
                } else if (token.equals("-")) {
                    // Pop top two elements, subtract in correct order: a - b
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                } else if (token.equals("*")) {
                    // Pop top two elements and apply multiplication
                    stack.push(stack.pop() * stack.pop());
                } else if (token.equals("/")) {
                    // Pop top two elements, divide in correct order: a / b
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a / b);
                } else {
                    // Token is a number, push it onto the stack
                    stack.push(Integer.parseInt(token));
                }
            }
    
            // Final result will be the only element left in the stack
            return stack.pop();
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n)