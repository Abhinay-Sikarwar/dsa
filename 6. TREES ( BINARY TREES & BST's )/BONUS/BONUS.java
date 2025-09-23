//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 6. TREES (BINARY TREES & BST's)
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 1373: MAXIMUM SUM BST IN BINARY TREE
// POST-ORDER DFS TO COLLECT SUBTREE INFO AND TRACK MAX SUM OF VALID BSTS

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    
    class Solution {
        // Helper class to store subtree info
        private class Info {
            boolean isBST; // whether subtree is BST
            int sum; // sum of nodes in subtree
            int min, max; // min & max values in subtree
    
            Info(boolean isBST, int sum, int min, int max) {
                this.isBST = isBST;
                this.sum = sum;
                this.min = min;
                this.max = max;
            }
        }
    
        private int maxSum = 0; // stores maximum sum of any BST subtree
    
        public int maxSumBST(TreeNode root) {
            postOrder(root);
            return maxSum;
        }
    
        // Post-order traversal returning Info for each subtree
        private Info postOrder(TreeNode node) {
            if (node == null) {
                // Empty subtree is a valid BST with neutral min/max
                return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }
    
            Info left = postOrder(node.left); // left subtree info
            Info right = postOrder(node.right); // right subtree info
    
            // Check if current subtree is BST
            if (left.isBST && right.isBST && node.val > left.max && node.val < right.min) {
                int sum = left.sum + right.sum + node.val; // sum of this BST
                maxSum = Math.max(maxSum, sum); // update global max
    
                int minVal = Math.min(node.val, left.min);
                int maxVal = Math.max(node.val, right.max);
    
                return new Info(true, sum, minVal, maxVal);
            }
    
            // Not a BST → mark as invalid
            return new Info(false, 0, 0, 0);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)