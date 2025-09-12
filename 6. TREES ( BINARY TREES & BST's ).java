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
// RECURSIVELY BUILD EACH SUBTREE, FINDING ROOT IN INORDER, THEN DIVIDING LEFT/RIGHT SUBTREES

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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 1)
                return new TreeNode(inorder[0]);
    
            // Build the binary tree from inorder and postorder traversals
            return buildSubtree(inorder, postorder,
                    0, postorder.length - 1, 0,
                    inorder.length - 1);
        }
    
        private TreeNode buildSubtree(int[] inorder, int[] postorder,
                int postStart, int postEnd,
                int inStart, int inEnd) {
            // Base case: no elements to build from
            if (inStart > inEnd || postStart > postEnd) {
                return null;
            }
    
            // Root is always the last element in the current postorder segment
            TreeNode root = new TreeNode(postorder[postEnd]);
    
            // Find root index in inorder traversal
            int inorderRootIndex = findIndex(inorder, root.val, inStart, inEnd);
            int leftSubtreeSize = inorderRootIndex - inStart; // size of left subtree
    
            // Recursively build left subtree
            root.left = buildSubtree(inorder, postorder,
                    postStart, postStart + leftSubtreeSize - 1,
                    inStart, inorderRootIndex - 1);
    
            // Recursively build right subtree
            root.right = buildSubtree(inorder, postorder,
                    postStart + leftSubtreeSize, postEnd - 1,
                    inorderRootIndex + 1, inEnd);
    
            return root;
        }
    
        private int findIndex(int[] inorder, int value, int start, int end) {
            // Linear search for value in inorder segment
            for (int i = start; i <= end; i++) {
                if (inorder[i] == value) {
                    return i;
                }
            }
            return -1; // should not happen if inputs are valid
        }
    }

    // ⏱️ Time Complexity:  O(n^2)               where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 113: PATH SUM II
// CHECK EACH PATH RECURSIVELY, BACKTRACKING TO EXPLORE ALL PATHS, STORING VALID PATHS

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
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            findPaths(root, targetSum, currentPath, result);
            return result;
        }
    
        private void findPaths(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
            if (node == null)
                return; // base case: empty node
    
            // Add the current node's value to the path
            currentPath.add(node.val);
    
            // Check if it's a leaf node and the path sum matches target
            if (node.left == null && node.right == null && node.val == remainingSum) {
                result.add(new ArrayList<>(currentPath)); // add a copy of current path to results
            } else {
                // Continue searching in left and right subtrees
                findPaths(node.left, remainingSum - node.val, currentPath, result);
                findPaths(node.right, remainingSum - node.val, currentPath, result);
            }
    
            // Backtrack: remove the last added node value before returning to the caller
            currentPath.remove(currentPath.size() - 1);
        }
    }

    // ⏱️ Time Complexity:  O(n^2)               where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 114: FLATTEN BINARY TREE TO LINKED LIST
// RECURSIVELY FLATTEN RIGHT SUBTREE, THEN LEFT SUBTREE, THEN ATTACH LEFT TO RIGHT

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
        public void flatten(TreeNode root) {
            if (root == null)
                return; // base case: empty tree
    
            // Recursively flatten left and right subtrees
            flatten(root.left);
            flatten(root.right);
    
            // Store the flattened right subtree
            TreeNode tempRight = root.right;
    
            // Move the flattened left subtree to the right
            root.right = root.left;
            root.left = null; // set left to null
    
            // Find the end of the new right subtree (which was the left subtree)
            TreeNode current = root;
            while (current.right != null) {
                current = current.right;
            }
    
            // Attach the originally flattened right subtree
            current.right = tempRight;
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 129: SUM ROOT TO LEAF NUMBERS
// RECURSIVELY BUILD NUMBERS ALONG PATHS, SUMMING AT LEAF NODES

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
        public int sumNumbers(TreeNode root) {
            return sumRootToLeaf(root, 0);
        }
    
        private int sumRootToLeaf(TreeNode node, int currentNumber) {
            if (node == null)
                return 0; // base case: empty node contributes 0
    
            // Update the current number by appending the node's value
            currentNumber = currentNumber * 10 + node.val;
    
            // If it's a leaf node, return the current number
            if (node.left == null && node.right == null)
                return currentNumber;
    
            // Recursively sum numbers from left and right subtrees
            return sumRootToLeaf(node.left, currentNumber) +
                    sumRootToLeaf(node.right, currentNumber);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 199: BINARY TREE RIGHT SIDE VIEW
// RECURSIVELY COLLECT RIGHTMOST NODE AT EACH DEPTH

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
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> rightView = new ArrayList<>();
            collectRightView(root, 0, rightView);
            return rightView; // final list of right-side values
        }
    
        private void collectRightView(TreeNode currNode, int currDepth, List<Integer> rightView) {
            if (currNode == null) // base case: no node
                return;
    
            // If this depth is being visited for the first time, record the node
            if (currDepth == rightView.size())
                rightView.add(currNode.val);
    
            // Prioritize right child to ensure rightmost nodes are captured first
            collectRightView(currNode.right, currDepth + 1, rightView);
            collectRightView(currNode.left, currDepth + 1, rightView);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 230: KTH SMALLEST ELEMENT IN A BST
// IN-ORDER TRAVERSAL RECURSIVELY

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
        private int count = 0; // to track number of nodes visited
        private int result = -1; // to store the kth smallest value
    
        public int kthSmallest(TreeNode root, int k) {
            inOrderTraversal(root, k);
            return result; // return the kth smallest value found
        }
    
        private void inOrderTraversal(TreeNode node, int k) {
            if (node == null || result != -1) // base case: empty node or already found
                return;
    
            // Traverse left subtree first (smaller values)
            inOrderTraversal(node.left, k);
    
            count++; // visit current node
            if (count == k) { // if this is the kth node visited
                result = node.val; // store its value
                return; // no need to continue
            }
    
            // Traverse right subtree next (larger values)
            inOrderTraversal(node.right, k);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 235: LOWEST COMMON ANCESTOR OF A BINARY SEARCH TREE
// ITERATIVELY TRAVERSE THE BST TO FIND SPLIT POINT

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    
    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // If root itself is p or q, it's the LCA
            if (root == p || root == q)
                return root;
    
            // Traverse the BST
            while (root != null) {
                if (p.val > root.val && q.val > root.val)  // Both nodes are in the right subtree
                    root = root.right;
                else if (p.val < root.val && q.val < root.val)  // Both nodes are in the left subtree
                    root = root.left;
                else  // Current root is the split point (LCA)
                    return root;
            }
    
            return null; // Shouldn't reach here if p and q exist in the tree
        }
    }

    // ⏱️ Time Complexity:  O(h)                 where h = height of the tree   
    // 🧠 Space Complexity:  O(1)

    // 337: HOUSE ROBBER III
// RECURSIVELY CALCULATE MAX ROBBERY AMOUNT, CHOOSING BETWEEN ROBBING OR NOT ROBBING CHILDREN

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
        public int rob(TreeNode root) {
            int[] result = robSubtree(root);
            return Math.max(result[0], result[1]); // max of robbing or not robbing root
        }
    
        private int[] robSubtree(TreeNode node) {
            if (node == null)
                return new int[] {0, 0}; // base case: empty node
    
            // Recursively calculate for left and right subtrees
            int[] left = robSubtree(node.left);
            int[] right = robSubtree(node.right);
    
            // If we rob this node, we can't rob its children
            int robThis = node.val + left[1] + right[1];
    
            // If we don't rob this node, we can choose to rob its children
            int skipThis = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    
            return new int[] {robThis, skipThis}; // return both options
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)    