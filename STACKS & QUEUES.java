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

    // 155: MIN STACK
// CUSTOM NODE WITH CONSTRUCTOR

// import java.util.EmptyStackException;
    class MinStack {
    
        // Custom Node class to store:
        // - val: actual value
        // - min: minimum value at this point in the stack
        // - next: pointer to the next node
        private class Node {
            int val;
            int min;
            Node next;
    
            // Constructor to create a new node
            Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    
        // Head points to the top of the stack
        private Node head;
    
        // Constructor to initialize an empty stack
        public MinStack() {
            head = null;
        }
    
        // Pushes a value onto the stack
        public void push(int val) {
            if (head == null) {
                // Stack is empty: this value is the min so far
                head = new Node(val, val, null);
            } else {
                // Stack has elements: compare with current min to update
                int newMin = Math.min(val, head.min);
                head = new Node(val, newMin, head); // Create a new node on top
            }
        }
    
        // Removes the top element from the stack
        public void pop() {
            if (head != null) {
                head = head.next; // Move the head pointer down
            }
        }
    
        // Returns the top element of the stack
        public int top() {
            if (head != null) {
                return head.val;
            }
            
            throw new EmptyStackException(); // Optional: handle edge case
        }
    
        // Retrieves the current minimum element in the stack
        public int getMin() {
            if (head != null) {
                return head.min;
            }
    
            throw new EmptyStackException(); // Optional: handle edge case
        }
    }
    
    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */ 

    // ⏱️ Time Complexity:  O(1)
    // 🧠 Space Complexity:  O(1)

    // 232: IMPLEMENT QUEUE USING STACKS