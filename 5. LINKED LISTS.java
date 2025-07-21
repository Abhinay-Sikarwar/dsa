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

    // 61: ROTATE LIST
// MAKE THE LIST CIRCULAR, NORMALIZE K

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
        public ListNode rotateRight(ListNode head, int k) {
            // Early termination in case of null input or unilength list or rotation is zero
            if (head == null || head.next == null || k == 0)
                return head;
    
            // Step 1: Find the length and the tail node
            int length = 1;
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
                length++;
            }
    
            // Step 2: Normalize k
            k = k % length;
            if (k == 0)
                return head;
    
            // Step 3: Make it a circular list
            tail.next = head;
    
            // Step 4: Find the new tail and new head
            int stepsToNewTail = length - k;
            ListNode newTail = head;
            for (int i = 1; i < stepsToNewTail; i++) {
                newTail = newTail.next;
            }
    
            ListNode newHead = newTail.next;
            newTail.next = null; // break the circle
    
            return newHead;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 82: REMOVE DUPLICATES FROM SORTED LIST II
// KEEP TRACK, DUMMY FOR HEAD, PREV FOR UNIQUE ENTRY, CURRENT 

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
            // Early exit in case of null input or unilength list
            if (head == null || head.next == null)
                return head;
            // Create a dummy node that points to head (to handle head deletions)
            ListNode dummy = new ListNode(0, head);
            ListNode prev = dummy; // Points to the last node that is confirmed to be unique
            ListNode current = head;
    
            while (current != null) {
                // Check if current node has duplicates
                boolean isDuplicate = false;
                while (current.next != null && current.val == current.next.val) {
                    isDuplicate = true;
                    current = current.next; // Skip duplicate nodes
                }
    
                if (isDuplicate) {
                    // Skip all duplicates
                    prev.next = current.next;
                } else {
                    // No duplicate, move prev
                    prev = prev.next;
                }
    
                current = current.next;
            }
    
            return dummy.next;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 86: PARTITION LIST
// TWO DUMMY HEAD AND TWO TRAVERSING POINTERS

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
        public ListNode partition(ListNode head, int x) {
            // Dummy heads for less and greater lists
            ListNode lessHead = new ListNode(0);
            ListNode greaterHead = new ListNode(0);
    
            // Pointers to build the lists
            ListNode less = lessHead;
            ListNode greater = greaterHead;
    
            // Traverse the list
            while (head != null) {
                if (head.val < x) {
                    less.next = head;
                    less = less.next;
                } else {
                    greater.next = head;
                    greater = greater.next;
                }
                head = head.next;
            }
    
            // End the greater list to avoid cycle
            greater.next = null;
    
            // Connect less list with greater list
            less.next = greaterHead.next;
    
            return lessHead.next;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 138: COPY LIST WITH RANDOM POINTER
    // THREE PASS TECHNIQUE, FIRST TO COPY, SECOND TO COPY RANDOM, THIRD TO RETRIEVE ORIGINAL AND DEEPCOPY
    
    /*
    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    */
    
    class Solution {
        public Node copyRandomList(Node head) {
            // Early exit in case of null input
            if (head == null)
                return head;
    
            // 1st pass: Create new nodes and insert them after each original node
            Node curr = head;
            while (curr != null) {
                Node copy = new Node(curr.val);
                copy.next = curr.next;
                curr.next = copy;
                curr = copy.next;
            }
    
            // 2nd pass: Set random pointers for the copy nodes
            curr = head;
            while (curr != null) {
                if (curr.random != null)
                    curr.next.random = curr.random.next;
                curr = curr.next.next;
            }
    
            // 3rd pass: Separate the original and copied lists
            curr = head;
            Node deepCopy = new Node(0);
            Node copyCurr = deepCopy;
    
            while (curr != null) {
                Node copy = curr.next; // Get A'
                copyCurr.next = copy; // Append A' to the copied list
                copyCurr = copy; // Move forward in the copied list 
    
                curr.next = copy.next; // Restore original: A.next = B
                curr = curr.next; // Move to next original: curr = B
            }
    
            return deepCopy.next;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 142: LINKED LIST CYCLE II
// FLOYD'S TORTOISE AND HARE ALGORITHM, REARANGE THE SLOW POINTER TO START

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
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null)
                return null;
    
            ListNode slow = head;
            ListNode fast = head;
    
            // Phase 1: Detect if there's a cycle
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
    
                if (slow == fast) {
                    // Phase 2: Find the cycle start
                    slow = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow; // Cycle start
                }
            }
    
            return null; // No cycle
        }
    } 
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 143: REORDER LIST
// FIND THE MIDDLE, REVERSE THE SECOND PART, MERGE THE LIST ALTERNATIVELY

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
        public void reorderList(ListNode head) {
            if (head == null || head.next == null)
                return;
    
            // Step 1: Find middle
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
    
            // Step 2: Reverse second half
            ListNode second = reverseList(slow.next);
            slow.next = null; // cut first half
    
            // Step 3: Merge two halves
            ListNode first = head;
            while (second != null) {
                ListNode tmp1 = first.next;
                ListNode tmp2 = second.next;
    
                first.next = second;
                second.next = tmp1;
    
                first = tmp1;
                second = tmp2;
            }
        }

    // Function to reverse the list
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode nextNode = head.next;
                head.next = prev;
                prev = head;
                head = nextNode;
            }
            return prev;
        }
    }
    
    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(1)

    // 148: SORT LIST
// SPLIT THE LIST, SORT SAPERATELY, RECURSION, MERGE THEM

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
        public ListNode sortList(ListNode head) {
             // Base case: if 0 or 1 node, it's already sorted
            if (head == null || head.next == null) return head;
    
            // Step 1: Split the list into two halves using slow & fast pointers
            ListNode mid = getMid(head);
            ListNode left = head;
            ListNode right = mid.next;
            mid.next = null;  // Split the list into two parts
    
            // Step 2: Recursively sort each half
            left = sortList(left);
            right = sortList(right);
    
            // Step 3: Merge sorted halves
            return merge(left, right);
        }
    
        // Helper to find middle node (end of first half)
        private ListNode getMid(ListNode head) {
            ListNode slow = head, fast = head.next; // start fast at head.next to get mid on left side
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    
        // Merge two sorted lists
        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
    
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            cur.next = (l1 != null) ? l1 : l2;
            return dummy.next;
        }
    } 
    
    // ⏱️ Time Complexity:  O(n.logn)
    // 🧠 Space Complexity:  O(logn)

// HARD

    // 25: REVERSE NODES IN K-GROUP
// SPLIT K GROUP, REVERSE IT, RECURSION, THEN MERGE
    
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
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null)
                return null;
    
            // Check if there are at least k nodes left to reverse
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                if (tail == null)
                    return head; // Not enough nodes, return as it is
                tail = tail.next;
            }
    
            // Reverse current k-group: [head, tail)
            ListNode newHead = reverse(head, tail);
    
            // Recur for remaining list and connect
            head.next = reverseKGroup(tail, k);
            return newHead;
        }
    
        // Reverse nodes from 'cur' up to (but not including) 'end'
        private ListNode reverse(ListNode cur, ListNode end) {
            ListNode prev = null;
            while (cur != end) {
                ListNode next = cur.next; // Save next node
                cur.next = prev; // Reverse pointer
                prev = cur; // Move prev forward
                cur = next; // Move cur forward
            }
            return prev; // New head of the reversed group
        }
    }

    // ⏱️ Time Complexity:  O(n)
    // 🧠 Space Complexity:  O(n/k)