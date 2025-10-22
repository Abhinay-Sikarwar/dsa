//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 8. HEAPS & PRIORITY QUEUES
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 420: STRONG PASSWORD CHECKER
// CHECKS TYPES AND REPEATS, MINIMIZES EDITS WITH REPLACEMENTS AND DELETIONS(MIN-HEAP).

    class Solution {
        public int strongPasswordChecker(String password) {
            int n = password.length();
            boolean hasLower = false, hasUpper = false, hasDigit = false;
    
            // Check character type presence
            for (char c : password.toCharArray()) {
                if (Character.isLowerCase(c)) hasLower = true;
                else if (Character.isUpperCase(c)) hasUpper = true;
                else if (Character.isDigit(c)) hasDigit = true;
            }
    
            int missingTypes = 3 - (hasLower ? 1 : 0) - (hasUpper ? 1 : 0) - (hasDigit ? 1 : 0);
            List<Integer> runs = new ArrayList<>();  // Collect repeating runs
    
            // Collect lengths of repeating character groups
            for (int i = 0; i < n;) {
                int j = i;
                while (j < n && password.charAt(j) == password.charAt(i)) j++;
                int len = j - i;
                if (len >= 3) runs.add(len);
                i = j;
            }
    
            // Case 1: Too short → need insertions or type fixes
            if (n < 6) return Math.max(missingTypes, 6 - n);
            
            // Case 2: Valid length → need replacements for triples
            if (n <= 20) {
                int replace = 0;
                for (int len : runs) replace += len / 3;
                return Math.max(missingTypes, replace);
            }
    
            // Case 3: long passwords: use minHeap to decide where to delete
            int toDelete = n - 20;
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            for (int len : runs) minHeap.add(new int[]{len % 3, len});
    
            int replace = 0;
    
            // Use deletions to minimize future replacements
            while (toDelete > 0 && !minHeap.isEmpty()) {
                int[] cur = minHeap.poll();
                int mod = cur[0], len = cur[1];
    
                len--;  // Delete 1 character
                toDelete--;  // delete 1 char
    
                if (len >= 3) minHeap.add(new int[]{len % 3, len});
            }
    
            // After all deletions, calculate replacements left
            while (!minHeap.isEmpty()) {
                int[] cur = minHeap.poll();
                replace += cur[1] / 3;
            }
    
            // Total = deletions + max(type fixes, replacements)
            return (n - 20) + Math.max(missingTypes, replace);
        }
    }

    // ⏱️ Time complexity: O(N log N) for heap operations on repeating sequences.
    // 🧠 Space complexity: O(N) for storing repeating runs and the min-heap.
