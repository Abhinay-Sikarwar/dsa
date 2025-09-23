//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 5. LINKED LISTS
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------
    
    // 2296: DESIGN A TEXT EDITOR
// USE A DOUBLY LINKED LIST WITH A CURSOR POINTER FOR EFFICIENT TEXT EDITING OPERATIONS    
    
    /**
     * Your TextEditor object will be instantiated and called as such:
     * TextEditor obj = new TextEditor();
     * obj.addText(text);
     * int param_2 = obj.deleteText(k);
     * String param_3 = obj.cursorLeft(k);
     * String param_4 = obj.cursorRight(k);
     */    
    class TextEditor {
        // Doubly linked list node representing each character
        private class Node {
            char c;
            Node prev, next;
    
            Node(char c) {
                this.c = c;
            }
        }
    
        private Node dummyHead; // Dummy head node to simplify edge cases
        private Node cursor; // Cursor points to the node before the current position
    
        public TextEditor() {
            dummyHead = new Node('#'); // Initialize dummy head
            cursor = dummyHead; // Cursor starts at dummy head (empty text)
        }
    
        // Add text after the cursor
        public void addText(String text) {
            for (char ch : text.toCharArray()) {
                Node newNode = new Node(ch); // Create new node
                newNode.prev = cursor; // Link new node with previous node
                newNode.next = cursor.next; // Link with next node (if exists)
                if (cursor.next != null) {
                    cursor.next.prev = newNode; // Update next node's previous pointer
                }
                cursor.next = newNode; // Update cursor's next
                cursor = newNode; // Move cursor after inserted char
            }
        }
    
        // Delete up to k characters to the left of the cursor
        public int deleteText(int k) {
            int deleted = 0;
            while (k > 0 && cursor != dummyHead) {
                Node prevNode = cursor.prev; // Node before current
                if (cursor.next != null) {
                    cursor.next.prev = prevNode; // Bypass current node
                }
                prevNode.next = cursor.next; // Link previous node to next
                cursor = prevNode; // Move cursor left
                deleted++;
                k--;
            }
            return deleted;
        }
    
        // Move cursor left by k positions
        public String cursorLeft(int k) {
            while (k > 0 && cursor != dummyHead) {
                cursor = cursor.prev;
                k--;
            }
            return getLeftText(); // Return last up to 10 characters to the left
        }
    
        // Move cursor right by k positions
        public String cursorRight(int k) {
            while (k > 0 && cursor.next != null) {
                cursor = cursor.next;
                k--;
            }
            return getLeftText(); // Return last up to 10 characters to the left
        }
    
        // Get up to 10 characters to the left of the cursor
        private String getLeftText() {
            StringBuilder sb = new StringBuilder();
            Node temp = cursor;
            int count = 10;
            while (count-- > 0 && temp != dummyHead) {
                sb.append(temp.c);
                temp = temp.prev;
            }
            return sb.reverse().toString(); // Reverse to get correct order
        }
    }

    // ⏱️ Time Complexity:  O(n), O(k), O(k), O(1)     respectively             where n : length of the text
    // 🧠 Space Complexity:  O(n)