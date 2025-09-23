//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 6. TREES (BINARY TREES & BST's)
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
    
    // 437: PATH SUM III
// RECURSIVELY EXPLORE ALL PATHS FROM EACH NODE, COUNTING VALID PATHS
    
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
        private int pathCount = 0; // to track number of valid paths found
    
        public int pathSum(TreeNode root, int targetSum) {
            if (root == null)
                return 0; // base case: empty tree
    
            // Start path sum calculation from current node
            calculatePathSums(root, (long) targetSum);
    
            // Recursively check left and right subtrees as new starting points
            pathSum(root.left, targetSum);
            pathSum(root.right, targetSum);
    
            return pathCount; // return total count of valid paths
        }
    
        private void calculatePathSums(TreeNode node, long remainingSum) {
            if (node == null)
                return; // base case: empty node
    
            // Check if current node completes a valid path
            if (node.val == remainingSum)
                pathCount++;
    
            // Continue searching in left and right subtrees with updated remaining sum
            calculatePathSums(node.left, remainingSum - node.val);
            calculatePathSums(node.right, remainingSum - node.val);
        }
    }

    // ⏱️ Time Complexity:  O(n^2)               where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 538: CONVERT BST TO GREATER TREE
// REVERSE IN-ORDER TRAVERSAL TO ACCUMULATE SUM OF GREATER NODES

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
        private int cumulativeSum = 0; // to track sum of greater nodes
    
        public TreeNode convertBST(TreeNode root) {
            reverseInOrder(root);
            return root; // return the modified tree
        }
    
        private void reverseInOrder(TreeNode node) {
            if (node == null)
                return; // base case: empty node
    
            // Traverse right subtree first (greater values)
            reverseInOrder(node.right);
    
            // Update current node's value with cumulative sum
            cumulativeSum += node.val;
            node.val = cumulativeSum;
    
            // Traverse left subtree next (smaller values)
            reverseInOrder(node.left);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 863: ALL NODES DISTANCE K IN BINARY TREE
// BUILD PARENT MAP, THEN BFS FROM TARGET TO FIND NODES AT DISTANCE K

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
        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            Map<TreeNode, TreeNode> parentMap = new HashMap<>(); // map to store parent of each node
            buildParentMap(root, parentMap);
    
            Queue<TreeNode> queue = new LinkedList<>(); // queue to store nodes at distance k
            queue.offer(target);
    
            Set<TreeNode> visited = new HashSet<>(); // set to track visited nodes
            visited.add(target);
    
            while (!queue.isEmpty()) {
                if (k == 0) // if we've reached distance k, collect results
                    break;
    
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode current = queue.poll();
    
                    // Check left child
                    if (current.left != null && !visited.contains(current.left)) {
                        visited.add(current.left);
                        queue.offer(current.left);
                    }
    
                    // Check right child
                    if (current.right != null && !visited.contains(current.right)) {
                        visited.add(current.right);
                        queue.offer(current.right);
                    }
    
                    // Check parent
                    TreeNode parent = parentMap.get(current);
                    if (parent != null && !visited.contains(parent)) {
                        visited.add(parent);
                        queue.offer(parent);
                    }
                }
                k--; // decrement distance after processing current level
            }
    
            // Return nodes at distance k
            List<Integer> result = new ArrayList<>();
            while (!queue.isEmpty()) {
                result.add(queue.poll().val);
            }
            return result;
        }
    
        private void buildParentMap(TreeNode node, Map<TreeNode, TreeNode> parentMap) {
            if (node == null)
                return; // base case: empty node
    
            parentMap.put(node.left, node);
            parentMap.put(node.right, node);
    
            buildParentMap(node.left, parentMap);
            buildParentMap(node.right, parentMap);
        }
    }        

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 987: VERTICAL ORDER TRAVERSAL OF A BINARY TREE
// COLLECT NODES WITH COORDINATES, SORT, THEN GROUP BY COLUMN

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
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<int[]> nodePositions = new ArrayList<>();
            
            // Step 1: Collect all nodes with their column, row, and value
            collectNodePositions(root, 0, 0, nodePositions);
    
            // Step 2: Sort nodes by column, then row, then value
            nodePositions.sort((nodeA, nodeB) -> {
                if (nodeA[0] != nodeB[0]) return Integer.compare(nodeA[0], nodeB[0]); // Column
                if (nodeA[1] != nodeB[1]) return Integer.compare(nodeA[1], nodeB[1]); // Row
                return Integer.compare(nodeA[2], nodeB[2]);                           // Value
            });
    
            List<List<Integer>> verticalOrder = new ArrayList<>();
            int lastColumn = Integer.MIN_VALUE;
    
            // Step 3: Group nodes by column
            for (int[] node : nodePositions) {
                int column = node[0];
                int value = node[2];
    
                if (column != lastColumn) {
                    verticalOrder.add(new ArrayList<>());
                    lastColumn = column;
                }
                verticalOrder.get(verticalOrder.size() - 1).add(value);
            }
    
            return verticalOrder;
        }
    
        // DFS helper to collect node positions
        private void collectNodePositions(TreeNode currentNode, int row, int column, List<int[]> nodePositions) {
            if (currentNode == null) return;
    
            nodePositions.add(new int[]{column, row, currentNode.val});
            collectNodePositions(currentNode.left, row + 1, column - 1, nodePositions);  // Left subtree
            collectNodePositions(currentNode.right, row + 1, column + 1, nodePositions); // Right subtree
        }
    }

    // ⏱️ Time Complexity:  O(n log n)           where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 662: MAXIMUM WIDTH OF BINARY TREE
// LEVEL ORDER TRAVERSAL, TRACKING "VIRTUAL" INDICES TO CALCULATE WIDTH

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
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null)
                return 0;
    
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int maxWidth = 0;
    
            while (!queue.isEmpty()) {
                int size = queue.size();
    
                // Track leftmost and rightmost indices for this level
                int left = queue.peek().val;
                int right = queue.peek().val;
    
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
    
                    // Assign "virtual indices" as in a complete binary tree
                    if (node.left != null) {
                        node.left.val = 2 * node.val;       // left child index
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        node.right.val = 2 * node.val + 1;  // right child index
                        queue.offer(node.right);
                    }
    
                    // Update min and max index for this level
                    left = Math.min(left, node.val);
                    right = Math.max(right, node.val);
                }
    
                // Width = rightmost index - leftmost index + 1
                maxWidth = Math.max(maxWidth, right - left + 1);
            }
    
            return maxWidth;
        }
    }

    
    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 1145: BINARY TREE COLORING GAME
// CHECK IF ANY REGION AROUND X HAS MORE THAN HALF THE NODES.
 
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
        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            // Find node x
            TreeNode xNode = findNode(root, x);
    
            // Count nodes in left and right subtrees of x
            int leftCount = countNodes(xNode.left);
            int rightCount = countNodes(xNode.right);
    
            // Remaining nodes (excluding x and its subtrees)
            int rest = n - (leftCount + rightCount + 1);
    
            // If any region has more than half the nodes, player 2 can guarantee a win
            return (leftCount > n / 2) || (rightCount > n / 2) || (rest > n / 2);
        }
    
        // DFS to find node with value x
        private TreeNode findNode(TreeNode root, int x) {
            if (root == null)
                return null;
            if (root.val == x)
                return root;
            TreeNode left = findNode(root.left, x);
            return (left != null) ? left : findNode(root.right, x);
        }
    
        // DFS to count nodes in a subtree
        private int countNodes(TreeNode node) {
            if (node == null)
                return 0;
            return 1 + countNodes(node.left) + countNodes(node.right);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)