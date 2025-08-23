//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 5. TREES (BINARY TREES & BST's)
// EASY

    // 100: SAME TREE
// CHECK EACH SUBTREE RECURSIVELY

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
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // If both nodes are null, the trees match here
            if (p == null && q == null)
                return true;
    
            // If both nodes are non-null and values match,
            // check left and right subtrees recursively
            if (p != null && q != null && p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
    
            // Otherwise, trees differ
            return false;
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in a smaller tree (or both, if same size)   
    // 🧠 Space Complexity:  O(n)               
    
    // 101: SYMMETRIC TREE
// CHECK EACH SUBTREE RECURSIVELY

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
    // Check if tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true; // empty tree is symmetric
        return isMirror(root.left, root.right);
    }

    // Check if two subtrees are mirror images
    public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true; // both null → mirror
        if (p == null || q == null)
            return false; // one null → not mirror

        // values equal + cross children mirror
        return (p.val == q.val) &&
                isMirror(p.left, q.right) &&
                isMirror(p.right, q.left);
    }
}

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)