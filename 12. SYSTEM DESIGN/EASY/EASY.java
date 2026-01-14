//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  12. SYSTEM DESIGN
//------------------------------------------------------------- EASY ----------------------------------------------------------------

    // 706. DESIGN HASHMAP
// SEPARATE CHAINING USING LINKED LIST BUCKETS, HASH FUNCTION: key % SIZE.

    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */
    
    class MyHashMap {
        // Node for separate chaining
        private static class Node {
            int key, value;
            Node next;
    
            Node(int k, int v) {
                key = k;
                value = v;
            }
        }
    
        // number of buckets
        private final int SIZE = 1000;
        private Node[] table;
    
        public MyHashMap() {
            table = new Node[SIZE];
        }
    
        // Hash function
        private int hash(int key) {
            return key % SIZE;
        }
    
        // Insert or update key-value pair
        public void put(int key, int value) {
            int idx = hash(key);
            Node curr = table[idx];
    
            // Update value if key exists
            while (curr != null) {
                if (curr.key == key) {
                    curr.value = value;
                    return;
                }
                curr = curr.next;
            }
    
            // Insert new node at bucket head
            Node node = new Node(key, value);
            node.next = table[idx];
            table[idx] = node;
        }
    
        // Retrieve value for key
        public int get(int key) {
            int idx = hash(key);
            Node curr = table[idx];
    
            while (curr != null) {
                if (curr.key == key)
                    return curr.value;
                curr = curr.next;
            }
    
            return -1; // key not found
        }
    
        // Remove key-value pair
        public void remove(int key) {
            int idx = hash(key);
            Node curr = table[idx];
            Node prev = null;
    
            while (curr != null) {
                if (curr.key == key) {
                    if (prev == null) {
                        table[idx] = curr.next; // remove head
                    } else {
                        prev.next = curr.next; // bypass node
                    }
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
        }
    }

    // ⏱️ TIME COMPLEXITY: O(1) average, O(N) worst-case (all keys in one bucket).
    // 🧠 SPACE COMPLEXITY: O(N)  // storing N key-value pairs.

    // 1603. DESIGN PARKING SYSTEM
// SIMPLE COUNTERS FOR EACH PARKING SLOT TYPE.

    /**
     * Your ParkingSystem object will be instantiated and called as such:
     * ParkingSystem obj = new ParkingSystem(big, medium, small);
     * boolean param_1 = obj.addCar(carType);
     */
    
    class ParkingSystem {
        private int bigSlots;    // remaining big slots
        private int mediumSlots; // remaining medium slots
        private int smallSlots;  // remaining small slots
    
        // initialize slot counts
        public ParkingSystem(int big, int medium, int small) {
            this.bigSlots = big;
            this.mediumSlots = medium;
            this.smallSlots = small;
        }
    
        // try to park a car of given type
        public boolean addCar(int carType) {
            // Validate for big car
            if (carType == 1) {
                if (bigSlots == 0) return false;
                bigSlots--;
                return true;
            }
    
            // Validate for medium car
            if (carType == 2) {
                if (mediumSlots == 0) return false;
                mediumSlots--;
                return true;
            }
    
            // Validate for small car
            if (carType == 3) {
                if (smallSlots == 0) return false;
                smallSlots--;
                return true;
            }
    
            return false;
        }
    }

    // ⏱️ TIME COMPLEXITY: O(1) for both initialization and addCar.
    // 🧠 SPACE COMPLEXITY: O(1) for storing fixed number of parking slots.