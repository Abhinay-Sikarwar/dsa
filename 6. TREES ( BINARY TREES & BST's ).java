//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 5. TREES (BINARY TREES & BST's)
//------------------------------------------------------------- EASY ----------------------------------------------------------------
    
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
    
    // 104: MAXIMUM DEPTH OF BINARY TREE
// RECURSIVE APPROACH

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
        public int maxDepth(TreeNode root) {
            if (root == null)
                return 0; // base case: empty tree has depth 0

            // Recursively find the max depth of left and right subtrees
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            // The depth of the current node is 1 + max depth of its subtrees
            return 1 + Math.max(leftDepth, rightDepth);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 112: PATH SUM
// CHECK EACH PATH RECURSIVELY
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
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null)
                return false; // base case: empty tree

            // If it's a leaf node, check if the path sum equals targetSum
            if (root.left == null && root.right == null) {
                return root.val == targetSum;
            }

            // Recursively check left and right subtrees with updated targetSum
            int newTarget = targetSum - root.val;
            return hasPathSum(root.left, newTarget) || hasPathSum(root.right, newTarget);
        }
    }
    
    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 226: INVERT BINARY TREE
// SWAP LEFT AND RIGHT SUBTREES RECURSIVELY

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
        public TreeNode invertTree(TreeNode root) {
            if (root == null) // base case: if tree is empty, return null
                return null;
    
            // swap left and right children
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
    
            // recursively invert left and right subtrees
            invertTree(root.left);
            invertTree(root.right);
    
            return root; // return root of inverted tree
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 572: SUBTREE OF ANOTHER TREE
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
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null) // if main tree is empty, it can't contain subRoot
                return false;
    
            // if trees match starting at this node, return true
            if (isReplica(root, subRoot))
                return true;
    
            // otherwise, recursively check left and right subtrees
            return isSubtree(root.left, subRoot) ||
                    isSubtree(root.right, subRoot);
        }
    
        public boolean isReplica(TreeNode base, TreeNode context) {
            if (base == null && context == null) // both nodes empty -> match
                return true;
            if (base == null || context == null) // one empty, one not -> mismatch
                return false;
    
            // recursively check if left and right subtrees are identical
            return base.val == context.val &&
                    isReplica(base.left, context.left) &&
                    isReplica(base.right, context.right);
        }
    }
    
    // ⏱️ Time Complexity:  O(m * n)             where m = no of nodes in main tree, n = no of nodes in subRoot   
    // 🧠 Space Complexity:  O(m + n)
       
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 98: VALIDATE BINARY SEARCH TREE
// RECURSIVELY CHECK EACH NODE WITH UPDATED MIN/MAX LIMITS

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
        public boolean isValidBST(TreeNode root) {
            return validate(root, null, null);
        }
    
        // Helper function to validate the BST
        private boolean validate(TreeNode node, Integer min, Integer max) {
            if (node == null)
                return true; // An empty node is valid
    
            // Check current node's value against min and max constraints
            if ((min != null && node.val <= min) || (max != null && node.val >= max))
                return false;
    
            // Recursively validate left and right subtrees with updated constraints
            return validate(node.left, min, node.val) && validate(node.right, node.val, max);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 102: BINARY TREE LEVEL ORDER TRAVERSAL
// ITERATIVE APPROACH USING QUEUE

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>(); // Stores the final level order traversal
    
            if (root == null) // Edge case: empty tree
                return result;
    
            Queue<TreeNode> queue = new LinkedList<>(); // Queue for BFS
            queue.offer(root);
    
            while (!queue.isEmpty()) {
                int levelSize = queue.size(); // Number of nodes in the current level
                List<Integer> currentLevel = new ArrayList<>(); // Stores values of this level
    
                // Process all nodes at the current level
                for (int i = 0; i < levelSize; i++) {
                    TreeNode current = queue.poll(); // Remove node from queue
                    currentLevel.add(current.val);   // Add its value
    
                    // Add children to queue for the next level
                    if (current.left != null)
                        queue.offer(current.left);
                    if (current.right != null)
                        queue.offer(current.right);
                }
    
                result.add(currentLevel); // Add this level's result
            }
    
            return result; // Return full traversal
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 103: BINARY TREE ZIGZAG LEVEL ORDER TRAVERSAL
// ITERATIVE APPROACH USING QUEUE

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
    
            // If tree is empty, return empty result
            if (root == null)
                return result;
    
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root); 
            boolean leftToRight = true; // flag to track traversal direction
    
            // Perform BFS (level-order traversal)
            while (!queue.isEmpty()) {
                int levelSize = queue.size(); // number of nodes at current level
                List<Integer> currentLevel = new ArrayList<>(levelSize);
    
                // Process all nodes in the current level
                for (int i = 0; i < levelSize; i++) {
                    TreeNode current = queue.poll(); // remove front node from queue
    
                    // Insert node's value depending on traversal direction
                    if (leftToRight) {
                        currentLevel.add(current.val); // append to the end
                    } else {
                        currentLevel.add(0, current.val); // insert at the beginning
                    }
    
                    // Add child nodes to queue for next level
                    if (current.left != null)
                        queue.offer(current.left);
                    if (current.right != null)
                        queue.offer(current.right);
                }
    
                // Store the current level's values in result
                result.add(currentLevel);
    
                // Switch traversal direction for the next level
                leftToRight = !leftToRight;   
            }
    
            return result; // final zigzag level order list
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 105: CONSTRUCT BINARY TREE FROM PREORDER AND INORDER TRAVERSAL
   
