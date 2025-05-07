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
// TWO STACKS, HELPER FUNCTION FOR REVERSING

// import java.util.Stack;
    class MyQueue {
        // Stack used for pushing new elements
        private Stack<Integer> stackIn;
        // Stack used for popping/peeking elements in correct FIFO order
        private Stack<Integer> stackOut;
    
        // Constructor initializes both stacks
        public MyQueue() {
            stackIn = new Stack<>();
            stackOut = new Stack<>();
        }
    
        // Push element x to the back of the queue
        public void push(int x) {
            // Always push to stackIn
            stackIn.push(x);
        }
    
        // Removes the element from the front of the queue and returns it
        public int pop() {
            // Ensure stackOut has the current front at the top
            transfer();
            // Pop from stackOut, which represents the front of the queue
            return stackOut.pop();
        }
    
        // Returns the element at the front of the queue without removing it
        public int peek() {
            // Ensure stackOut has the current front at the top
            transfer();
            // Peek at the top of stackOut, which is the front of the queue
            return stackOut.peek();
        }
    
        // Returns true if the queue is empty
        public boolean empty() {
            // Queue is empty only when both stacks are empty
            return stackIn.isEmpty() && stackOut.isEmpty();
        }
    
        // Helper method to transfer elements from stackIn to stackOut
        // This reverses the order so the oldest element is on top
        private void transfer() {
            // Only transfer if stackOut is empty (to preserve correct order)
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    // Pop from stackIn and push to stackOut to reverse order
                    stackOut.push(stackIn.pop());
                }
            }
        }
    }
    
    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */ 
    
    // ⏱️ Time Complexity:  O(1)
    // 🧠 Space Complexity:  O(n)

// MEDIUM

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

// BONUS QUESTION

    // 85: MAXIMAL RECTANGLE