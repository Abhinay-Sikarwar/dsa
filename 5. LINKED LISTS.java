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
// TWO POINTER TO TRAVERSE TOTAL LENGTH OF TWO LISTS

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
    
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // If either list is empty, there's no intersection
            if (headA == null || headB == null)
                return null;
    
            ListNode A = headA;
            ListNode B = headB;
    
            // Traverse both lists
            while (A != B) {
                // If A reaches the end, redirect it to headB
                A = (A == null) ? headB : A.next;
    
                // If B reaches the end, redirect it to headA
                B = (B == null) ? headA : B.next;
            }
    
            // Either intersection node or null
            return A;
        }
    }
    
    // ⏱️ Time Complexity:  O(m + n)                    Where m & n are length of the lists respectively
    // 🧠 Space Complexity:  O(1)

    // 206: REVERSE LINKED LIST
// TWO POINTER WITH TEMPORARY NODE

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
        public ListNode reverseList(ListNode head) {
            // early exit when list is null or unilength
            if (head == null || head.next == null)
                return head;
            ListNode prev = null;
            ListNode curr = head;
    
            while (curr != null) {
                ListNode nextTemp = curr.next; // temporarily store next node
                curr.next = prev; // reverse the current node's pointer
                prev = curr; // move prev and curr one step forward
                curr = nextTemp;
            }
    
            return prev; // prev will be the new head
    
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

// MEDIUM

    // 2: ADD TWO NUMBER
// DUMMY NODE, CURRENT POINTER AND KEEP TRACK OF CARRY

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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0); // Dummy node for result list
            ListNode current = dummy;
            int carry = 0;
    
            while (l1 != null || l2 != null || carry != 0) {
                int x = (l1 != null) ? l1.val : 0; // Value from l1 or 0
                int y = (l2 != null) ? l2.val : 0; // Value from l2 or 0
                int sum = x + y + carry;
                carry = sum / 10; // Compute carry for next iteration
    
                current.next = new ListNode(sum % 10); // Store digit
                current = current.next;
    
                if (l1 != null)
                    l1 = l1.next; // Move to next node
                if (l2 != null)
                    l2 = l2.next;
            }
    
            return dummy.next; // Return result (skip dummy head)
        }
    }
    
    // ⏱️ Time Complexity:  O(n)                           Where n = max(l1, l2)
    // 🧠 Space Complexity:  O(n)

    // 19: REMOVE NTH NODE FROM END OF LIST
// TWO POINTERS FAST AND SLOW, MAINING GAP N + 1 FROM SLOW

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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0); // Dummy node before head
            dummy.next = head;
    
            ListNode fast = dummy;
            ListNode slow = dummy;
    
            // Move fast pointer n+1 steps ahead to maintain gap
            for (int i = 0; i <= n; i++) {
                fast = fast.next;
            }
    
            // Move both fast and slow until fast reaches the end
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
    
            // Skip the nth node from the end
            slow.next = slow.next.next;
    
            return dummy.next;
        }
    } 
    
    // ⏱️ Time Complexity:  O(l)                           Where l is length of the linked list
    // 🧠 Space Complexity:  O(1)

    // 24: SWAP NODES IN PAIRS
// TWO POINTERS, DUMMY NODE TO TRAVERSE 

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
        public ListNode swapPairs(ListNode head) {
            // Base case: if there's 0 or 1 node left, return head
            if (head == null || head.next == null) {
                return head;
            }
    
            // Create a dummy node to simplify swapping the head node
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode current = dummy;
    
            while (current.next != null && current.next.next != null) {
                // Nodes to be swapped
                ListNode first = current.next;
                ListNode second = current.next.next;
    
                // Swapping
                first.next = second.next;
                second.next = first;
                current.next = second;
    
                // Move to the next pair
                current = first;
            }
    
            return dummy.next;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)