//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 5. LINKED LISTS
// EASY

    // 21: MERGE TWO SORTED LISTS
// USING DUMMY NODE WITH ITERATIVE SOLUTION

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
     
    class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // Create a dummy node to act as the starting point of the merged list.
            // This simplifies edge cases, such as empty input lists.
            ListNode dummy = new ListNode(-1);
    
            // `current` will point to the last node in the merged list as we build it.
            ListNode current = dummy;
    
            // Traverse both lists while neither is exhausted
            while (list1 != null && list2 != null) {
                // Compare the current nodes from each list
                if (list1.val <= list2.val) {
                    // If list1's value is smaller or equal, link it to the merged list
                    current.next = list1;
                    // Move list1 forward
                    list1 = list1.next;
                } else {
                    // If list2's value is smaller, link it to the merged list
                    current.next = list2;
                    // Move list2 forward
                    list2 = list2.next;
                }
                // Advance the current pointer to the last node added
                current = current.next;
            }
    
            // After the loop, at least one of the lists is null.
            // Directly link the non-null list to the end of the merged list
            // because it's already sorted.
            current.next = (list1 != null) ? list1 : list2;
    
            // Return the merged list, starting from the node after the dummy
            return dummy.next;
        }
    }
    
    // ⏱️ Time Complexity:  O(n + m)                   Where n & m are lengths of the both lists
    // 🧠 Space Complexity:  O(1)

    // 141: LINKED LIST CYCLE
// FLOYD'S TORTOISE AND HARE ALGORITHM 

    /**
     * Definition for singly-linked list.
     * class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    
    public class Solution {
        public boolean hasCycle(ListNode head) {
            // Initialize two pointers at the head of the list
            ListNode slow = head; // Moves one step at a time
            ListNode fast = head; // Moves two steps at a time
    
            // Traverse the list as long as fast and fast.next are not null
            while (fast != null && fast.next != null) {
                slow = slow.next; // Move slow by 1 step
                fast = fast.next.next; // Move fast by 2 steps
    
                // If slow and fast meet at the same node, a cycle exists
                if (slow == fast) {
                    return true;
                }
            }
    
            // If fast reaches the end, the list has no cycle
            return false;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 83: REMOVE DUPLICATES FROM SORTED LIST
// TRAVERSING THE LIST AND CHECK FOR CONSECUTIVE SAME VALUES

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            // Edge case: if the list is empty or has only one node, return it as is
            if (head == null || head.next == null)
                return head;
    
            // Use a pointer to traverse the list
            ListNode current = head;
    
            // Iterate through the list until the end is reached
            while (current != null && current.next != null) {
                // If the current node has the same value as the next node
                if (current.val == current.next.val) {
                    // Skip the next node (remove the duplicate)
                    current.next = current.next.next;
                } else {
                    // Otherwise, move to the next node
                    current = current.next;
                }
            }
    
            // Return the head of the modified list
            return head;
        }
    }

    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 234: PALINDROME LINKED LIST
// FIND THE MIDDLE AND REVERSE THE SECOND HALF TO COMPARE

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    
    class Solution {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null)
                return true;
    
            // Step 1: Find the end of the first half using slow and fast pointers
            ListNode firstHalfEnd = endOfFirstHalf(head);
    
            // Step 2: Reverse the second half
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);
    
            // Step 3: Compare the two halves
            ListNode firstPart = head;
            ListNode secondPart = secondHalfStart;
            boolean result = true;
    
            while (result && secondPart != null) {
                if (firstPart.val != secondPart.val) {
                    result = false;
                }
                firstPart = firstPart.next;
                secondPart = secondPart.next;
            }
    
            // step 4: Return the result
            return result;
        }
    
        // Helper method to find the end of the first half
        private ListNode endOfFirstHalf(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
    
            // Move fast by 2 steps and slow by 1 step until fast reaches the end
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    
        // Helper method to reverse a linked list
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode current = head;
    
            while (current != null) {
                ListNode nextNode = current.next;
                current.next = prev;
                prev = current;
                current = nextNode;
            }
    
            return prev;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 876: MIDDLE OF THE LINKED LIST
// FLOYD'S TORTOISE AND HARE ALGORITHM TECHNIQUE
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    
    class Solution {
        public ListNode middleNode(ListNode head) {
            if (head == null || head.next == null)
                return head;
    
            ListNode slow = head;
            ListNode fast = head;
        
            // Move fast by 2 steps and slow by 1 step until fast reaches the end
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
    
            return slow;   
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 160: INTERSECTION OF TWO LINKED LISTS