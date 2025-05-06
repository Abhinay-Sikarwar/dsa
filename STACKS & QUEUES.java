//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 4. STACKS & QUEUES
// EASY

    // 20: VALID PARENTHESES
// PUSHING OPENING BRACKETS TO STACK
    class Solution {
        public boolean isValid(String s) {
            // Stack to keep track of opening brackets
            Stack<Character> stack = new Stack<>();
    
            // Loop through each character in the input string
            for (char ch : s.toCharArray()) {
                // If it's an opening bracket, push it onto the stack
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch);
                } else {
                    // If it's a closing bracket and stack is empty, it's invalid
                    if (stack.isEmpty())
                        return false;
    
                    // Pop the last opening bracket from the stack
                    char last = stack.pop();
    
                    // Check if the popped bracket and current closing bracket match
                    if (!isPair(last, ch))
                        return false;
                }
            }
    
            // If the stack is empty at the end, all brackets matched properly
            return stack.isEmpty();
        }
    
        // Helper method to check if a pair of brackets match
        private boolean isPair(char open, char close) {
            return (open == '(' && close == ')') ||
                    (open == '{' && close == '}') ||
                    (open == '[' && close == ']');
        }
    }
    
    // ⏱️ Time Complexity:  O(n)              Where n = length of string
    // 🧠 Space Complexity:  O(n)