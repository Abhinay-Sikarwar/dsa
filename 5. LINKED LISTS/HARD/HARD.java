//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 5. LINKED LISTS
//------------------------------------------------------------- HARD -----------------------------------------------------------------

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

    // 23: MERGE K SORTED LISTS
// RECURSIVELY DIVIDE INTO HALVES, MERGE EACH PAIR, AND COMBINE THEM UNTIL ONE. 

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
        public ListNode mergeKLists(ListNode[] lists) {
            // Edge case: if the input array is null or empty, return null
            if (lists == null || lists.length == 0) {
                return null;
            }
    
            // Use divide and conquer to merge all lists
            return mergeKListsHelper(lists, 0, lists.length - 1);
        }
    
        private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
            // Base case: only one list to return
            if (start == end) {
                return lists[start];
            }
    
            // Base case: only two lists, merge them directly
            if (start + 1 == end) {
                return merge(lists[start], lists[end]);
            }
    
            // Divide the range into two halves
            int mid = start + (end - start) / 2;
    
            // Recursively merge the left half
            ListNode left = mergeKListsHelper(lists, start, mid);
    
            // Recursively merge the right half
            ListNode right = mergeKListsHelper(lists, mid + 1, end);
    
            // Merge the two sorted halves
            return merge(left, right);
        }
    
        public ListNode merge(ListNode list1, ListNode list2) {
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
    
    // ⏱️ Time Complexity:  O(n.logk)
    // 🧠 Space Complexity:  O(logk)