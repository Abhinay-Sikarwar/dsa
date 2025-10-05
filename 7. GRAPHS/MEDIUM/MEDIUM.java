//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 7. GRAPHS
//------------------------------------------------------------ MEDIUM ----------------------------------------------------------------

    // 200: NUMBER OF ISLANDS
// DFS FLOOD-FILL TO SINK CONNECTED LAND CELLS AND COUNT ISLANDS

    class Solution {
        public int numIslands(char[][] grid) {
            int rows = grid.length;
            int columns = grid[0].length;
            int islands = 0;
    
            // Traverse the entire grid
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // Found a land cell
                    if (grid[i][j] == '1') {
                        islands++;
                        sink(grid, i, j); // sink the island
                    }
                }
            }
            return islands;
        }
    
        private void sink(char[][] grid, int i, int j) {
            int row = grid.length;
            int column = grid[0].length;
    
            // boundary + water check
            if (i < 0 || j < 0 || i >= row || j >= column || grid[i][j] == '0') {
                return;
            }
    
            // mark current land as visited (sink it)
            grid[i][j] = '0';
    
            // explore neighbors
            sink(grid, i + 1, j); // down
            sink(grid, i - 1, j); // up
            sink(grid, i, j + 1); // right
            sink(grid, i, j - 1); // left
        }
    }

    // ⏱️ Time Complexity:  O(rows * columns)
    // 🧠 Space Complexity:  O(rows * columns) in the worst case.

    // 130: SURROUNDED REGIONS
// DFS TO MARK BORDER-CONNECTED 'O's AND FLIP THE REST

    class Solution {
        public void solve(char[][] board) {
            int rows = board.length;
            int cols = board[0].length;
    
            // Step 1: Mark border-connected 'O's as temporary ('T')
            for (int i = 0; i < rows; i++) {
                dfs(board, i, 0);           // left border
                dfs(board, i, cols - 1);    // right border
            }
            for (int j = 0; j < cols; j++) {
                dfs(board, 0, j);           // top border
                dfs(board, rows - 1, j);    // bottom border
            }
    
            // Step 2: Flip remaining 'O' -> 'X', restore 'T' -> 'O'
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X'; // capture region
                    if (board[i][j] == 'T') board[i][j] = 'O'; // restore safe cell
                }
            }
        }
    
        // DFS to mark safe 'O's connected to the border
        private void dfs(char[][] board, int i, int j) {
            int rows = board.length, cols = board[0].length;
    
            // Stop if out of bounds or not an 'O'
            if (i < 0 || j < 0 || i >= rows || j >= cols || board[i][j] != 'O') return;
    
            board[i][j] = 'T'; // mark as safe
    
            // Explore 4 directions
            dfs(board, i + 1, j);
            dfs(board, i - 1, j);
            dfs(board, i, j + 1);
            dfs(board, i, j - 1);
        }
    }

    // ⏱️ Time Complexity:  O(rows * columns)
    // 🧠 Space Complexity:  O(rows * columns) in the worst case.

    // 133: CLONE GRAPH
// DFS WITH A HASHMAP TO CLONE NODES AND MAINTAIN MAPPING    

    /*
    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    */
    
    class Solution {
        public Node cloneGraph(Node node) {
            if (node == null)
                return null; // empty graph
    
            Map<Node, Node> map = new HashMap<>(); // original → cloned mapping
            return clone(node, map);
        }
    
        private Node clone(Node node, Map<Node, Node> map) {
            // If node already cloned, return its copy
            if (map.containsKey(node))
                return map.get(node);
    
            // Create clone of current node
            Node newNode = new Node(node.val);
            map.put(node, newNode);
    
            // Recursively clone all neighbors
            for (Node neighbor : node.neighbors) {
                newNode.neighbors.add(clone(neighbor, map));
            }
    
            return newNode;
        }
    }
    
    // ⏱️ Time Complexity:  O(V + E) where V is the number of vertices and E is the number of edges.
    // 🧠 Space Complexity:  O(V) for the hashmap and recursion stack.

    // 207: COURSE SCHEDULE
// DFS TO DETECT CYCLES IN A DIRECTED GRAPH

    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] pre : prerequisites) {
                graph.get(pre[1]).add(pre[0]); // edge from pre[1] to pre[0]
            }
    
            boolean[] visited = new boolean[numCourses];
            boolean[] onPath = new boolean[numCourses];
    
            for (int i = 0; i < numCourses; i++) {
                if (!visited[i]) {
                    if (hasCycle(graph, i, visited, onPath)) {
                        return false; // cycle detected
                    }
                }
            }
            return true; // no cycles found
        }
    
        private boolean hasCycle(List<List<Integer>> graph, int node, boolean[] visited, boolean[] onPath) {
            if (onPath[node]) return true; // cycle detected
            if (visited[node]) return false; // already processed
    
            visited[node] = true;
            onPath[node] = true;
    
            for (int neighbor : graph.get(node)) {
                if (hasCycle(graph, neighbor, visited, onPath)) {
                    return true; // cycle detected in recursion
                }
            }
    
            onPath[node] = false; // backtrack
            return false; // no cycle found
        }
    }

    // ⏱️ Time Complexity:  O(V + E) where V is the number of courses and E is the number of prerequisites.
    // 🧠 Space Complexity:  O(V) for the graph, visited and onPath.

    // 210: COURSE SCHEDULE II
// TOPOLOGICAL SORT USING KAHN'S ALGORITHM (BFS)

    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // Step 1: Build adjacency list and in-degree array
            List<List<Integer>> graph = new ArrayList<>();
            int[] inDegree = new int[numCourses];
    
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
    
            for (int[] pre : prerequisites) {
                graph.get(pre[1]).add(pre[0]); // course pre[1] must be taken before pre[0]
                inDegree[pre[0]]++;           // track number of prerequisites for each course
            }
    
            // Step 2: Initialize queue with courses having no prerequisites
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
    
            int[] topoOrder = new int[numCourses];
            int idx = 0;
    
            // Step 3: Process courses in topological order (BFS)
            while (!queue.isEmpty()) {
                int course = queue.poll();
                topoOrder[idx++] = course;
    
                for (int neighbor : graph.get(course)) {
                    inDegree[neighbor]--;       // one prerequisite completed
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor);  // course is now available
                    }
                }
            }
    
            // Step 4: If not all courses are scheduled → cycle detected
            if (idx != numCourses) {
                return new int[0]; // no valid order
            }
            return topoOrder;
        }
    }

    // ⏱️ Time Complexity:  O(V + E) where V is the number of courses and E is the number of prerequisites.
    // 🧠 Space Complexity:  O(V + E) for the graph and inDegree Array.

    // 261: GRAPH VALID TREE
// DFS TO CHECK FOR CYCLES AND CONNECTIVITY IN AN UNDIRECTED GRAPH

    class Solution {
        public boolean validTree(int n, int[][] edges) {
            // Step 1: Build adjacency list
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
    
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                graph.get(u).add(v);
                graph.get(v).add(u);
            }
    
            // Step 2: DFS to check for cycles and connectivity
            boolean[] visited = new boolean[n];
            if (hasCycle(graph, 0, -1, visited)) {
                return false; // cycle detected
            }
    
            // Step 3: Check if all nodes are connected
            for (boolean v : visited) {
                if (!v) return false; // disconnected node found
            }
    
            return true; // valid tree
        }
    
        // Helper function to detect cycle using DFS
        private boolean hasCycle(List<List<Integer>> graph, int node, int parent, boolean[] visited) {
            visited[node] = true;
    
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    if (hasCycle(graph, neighbor, node, visited)) {
                        return true; // cycle found in recursion
                    }
                } else if (neighbor != parent) {
                    return true; // back-edge found → cycle
                }
            }
    
            return false; // no cycle
        }
    }

    // ⏱️ Time Complexity:  O(V + E) where V is the number of nodes and E is the number of edges.
    // 🧠 Space Complexity:  O(V + E) for the graph and recursion.

    // 399: EVALUATE DIVISION
// DFS TO FIND PATH PRODUCTS IN A WEIGHTED DIRECTED GRAPH

    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            // Build graph: variable -> (neighbor -> ratio)
            Map<String, Map<String, Double>> graph = new HashMap<>();
    
            for (int i = 0; i < equations.size(); i++) {
                String A = equations.get(i).get(0);
                String B = equations.get(i).get(1);
                double val = values[i];
    
                graph.putIfAbsent(A, new HashMap<>());
                graph.putIfAbsent(B, new HashMap<>());
    
                graph.get(A).put(B, val); // A / B = val
                graph.get(B).put(A, 1.0 / val); // B / A = 1/val
            }
    
            double[] results = new double[queries.size()];
    
            // Evaluate each query using DFS
            for (int i = 0; i < queries.size(); i++) {
                String start = queries.get(i).get(0);
                String end = queries.get(i).get(1);
    
                if (!graph.containsKey(start) || !graph.containsKey(end)) {
                    results[i] = -1.0; // Undefined variable
                } else if (start.equals(end)) {
                    results[i] = 1.0; // Same variable
                } else {
                    results[i] = dfs(graph, start, end, 1.0, new HashSet<>());
                }
            }
    
            return results;
        }
    
        // DFS to find path product from curr -> target
        private double dfs(Map<String, Map<String, Double>> graph, String curr, String target,
                double accProduct, Set<String> visited) {
            if (curr.equals(target))
                return accProduct; // Found target
    
            visited.add(curr);
    
            for (Map.Entry<String, Double> neighbor : graph.get(curr).entrySet()) {
                String next = neighbor.getKey();
                double weight = neighbor.getValue();
    
                if (!visited.contains(next)) {
                    double result = dfs(graph, next, target, accProduct * weight, visited);
                    if (result != -1.0)
                        return result; // Valid path found
                }
            }
    
            return -1.0; // No path exists
        }
    }

    // ⏱️ Time Complexity:  O(E) per query in the worst case, where E is the number of edges.
    // 🧠 Space Complexity:  O(V + E) for the graph and recursion.

    // 417: PACIFIC ATLANTIC WATER FLOW
// DFS FROM OCEAN BORDERS TO FIND CELLS REACHABLE BY BOTH OCEANS

    class Solution {
        private int rows, columns;
        private int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }; // 4 possible movement directions
    
        public List<List<Integer>> pacificAtlantic(int[][] heights) {
            rows = heights.length;
            columns = heights[0].length;
            boolean[][] pacific = new boolean[rows][columns]; // Cells reachable from Pacific Ocean
            boolean[][] atlantic = new boolean[rows][columns]; // Cells reachable from Atlantic Ocean
    
            // Run DFS from all Pacific-border cells (top row + left column)
            for (int j = 0; j < columns; j++)
                dfs(0, j, heights, pacific);
            for (int i = 0; i < rows; i++)
                dfs(i, 0, heights, pacific);
    
            // Run DFS from all Atlantic-border cells (bottom row + right column)
            for (int j = 0; j < columns; j++)
                dfs(rows - 1, j, heights, atlantic);
            for (int i = 0; i < rows; i++)
                dfs(i, columns - 1, heights, atlantic);
    
            List<List<Integer>> result = new ArrayList<>();
            // Collect cells reachable by both oceans
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (pacific[i][j] && atlantic[i][j]) {
                        result.add(Arrays.asList(i, j));
                    }
                }
            }
            return result;
        }
    
        // DFS to explore all cells reachable from a given ocean border
        private void dfs(int i, int j, int[][] heights, boolean[][] visited) {
            if (visited[i][j]) // already visited -> skip
                return;
            visited[i][j] = true; // mark current cell as reachable
    
            // Explore all 4 directions (up, down, left, right)
            for (int[] direction : directions) {
                int x = i + direction[0], y = j + direction[1];
    
                // Skip out-of-bounds coordinates
                if (x < 0 || x >= rows || y < 0 || y >= columns)
                    continue;
    
                // Only move to neighbor if its height is >= current cell (reverse flow)
                if (heights[x][y] < heights[i][j])
                    continue;
    
                // Continue DFS from the neighbor
                dfs(x, y, heights, visited);
            }
        }
    }

    // ⏱️ Time Complexity:  O(rows * columns)
    // 🧠 Space Complexity:  O(rows * columns) for the visited arrays and recursion.