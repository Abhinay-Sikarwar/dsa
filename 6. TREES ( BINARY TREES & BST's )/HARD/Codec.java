//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  6. TREES (BINARY TREES & BST's)
//------------------------------------------------------------- HARD -----------------------------------------------------------------   

    // 99: RECOVER BINARY SEARCH TREE
// IN-ORDER TRAVERSAL TO IDENTIFY THE TWO SWAPPED NODES, THEN SWAP THEM BACK

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
        private TreeNode firstElement = null; // first swapped node
        private TreeNode secondElement = null; // second swapped node
        private TreeNode prevElement = null; // previous node in in-order traversal
    
        public void recoverTree(TreeNode root) {
            // Step 1: Perform in-order traversal to find the two swapped nodes
            traverse(root);
    
            // Step 2: Swap the values of the two nodes to correct the BST
            if (firstElement != null && secondElement != null) {
                int temp = firstElement.val;
                firstElement.val = secondElement.val;
                secondElement.val = temp;
            }
        }
    
        private void traverse(TreeNode node) {
            if (node == null)
                return; // base case: empty node
    
            // Traverse left subtree
            traverse(node.left);
    
            // Check for swapped nodes
            if (firstElement == null && prevElement != null && prevElement.val >= node.val) {
                firstElement = prevElement; // first violation found
            }
            if (firstElement != null && prevElement != null && prevElement.val >= node.val) {
                secondElement = node; // second violation found
            }
            prevElement = node; // update previous node
    
            // Traverse right subtree
            traverse(node.right);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 124: BINARY TREE MAXIMUM PATH SUM
// RECURSIVELY CALCULATE MAX PATH SUM INCLUDING EACH NODE, TRACKING GLOBAL MAX

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
        private int maxPathSum = Integer.MIN_VALUE; // to track the maximum path sum found
    
        public int maxPathSum(TreeNode root) {
            calculateMaxPath(root);
            return maxPathSum; // return the overall maximum path sum
        }
    
        private int calculateMaxPath(TreeNode node) {
            if (node == null)
                return 0; // base case: empty node contributes 0
    
            // Recursively calculate max path sums for left and right subtrees
            int leftMax = Math.max(0, calculateMaxPath(node.left));   // ignore negative paths
            int rightMax = Math.max(0, calculateMaxPath(node.right)); // ignore negative paths
    
            // Calculate the path sum including this node and both children
            int currentPathSum = node.val + leftMax + rightMax;
    
            // Update global maximum path sum if current is greater
            maxPathSum = Math.max(maxPathSum, currentPathSum);
    
            // Return the maximum path sum including this node and one child
            return node.val + Math.max(leftMax, rightMax);
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 297: SERIALIZE AND DESERIALIZE BINARY TREE
// LEVEL ORDER TRAVERSAL FOR SERIALIZATION, RECONSTRUCTION FOR DESERIALIZATION

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    
    public class Codec {
    
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null)
                return "null";
    
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
    
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
    
                if (node == null) {
                    sb.append("null,"); // placeholder for null
                    continue;
                }
    
                sb.append(node.val).append(","); // append value
                queue.add(node.left); // add left child
                queue.add(node.right); // add right child
            }
    
            return sb.toString();
        }
    
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.equals("null"))
                return null;
    
            String[] arr = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
    
            int i = 1; // index for traversing serialized array
    
            while (!queue.isEmpty() && i < arr.length) {
                TreeNode node = queue.poll();
    
                // Process left child
                if (!arr[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(arr[i]));
                    queue.add(node.left);
                }
                i++;
    
                // Process right child
                if (i < arr.length && !arr[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(arr[i]));
                    queue.add(node.right);
                }
                i++;
            }
    
            return root;
        }
    }
    
    // Your Codec object will be instantiated and called as such:
    // Codec ser = new Codec();
    // Codec deser = new Codec();
    // TreeNode ans = deser.deserialize(ser.serialize(root));

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)

    // 968: BINARY TREE CAMERAS
// POST-ORDER DFS TO DETERMINE CAMERA PLACEMENT BASED ON CHILDREN'S STATES

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
        private int cameras = 0;
    
        public int minCameraCover(TreeNode root) {
            // If root is not covered, we need an extra camera
            if (dfs(root) == 0) {
                cameras++;
            }
            return cameras;
        }
    
        private int dfs(TreeNode node) {
            if (node == null) return 2; // null nodes are considered covered
    
            int left = dfs(node.left);
            int right = dfs(node.right);
    
            if (left == 0 || right == 0) {
                // If any child is not covered, put a camera here
                cameras++;
                return 1;
            }
    
            if (left == 1 || right == 1) {
                // If any child has a camera, current node is covered
                return 2;
            }
    
            // Both children are covered but don't have cameras,
            // so current node is not covered
            return 0;
        }
    }

    // ⏱️ Time Complexity:  O(n)                 where n = no of nodes in the tree   
    // 🧠 Space Complexity:  O(n)