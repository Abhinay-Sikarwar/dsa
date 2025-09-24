//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
// 7. GRAPHS
//------------------------------------------------------------- EASY ----------------------------------------------------------------

    // 323: NUMBER OF CONNECTED COMPONENTS IN AN UNDIRECTED GRAPH
// DFS APPROACH, USING ADJACENCY LIST REPRESENTATION

    class Solution {
        public int countComponents(int n, int[][] edges) {
            // Build adjacency list for the graph
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]); // Add edge both ways (undirected)
                graph.get(edge[1]).add(edge[0]);
            }
    
            boolean[] visited = new boolean[n]; // Track visited nodes
            int count = 0; // Number of connected components
    
            // Visit each node
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(graph, visited, i); // Visit all nodes in this component
                    count++; // Increment component count
                }
            }
    
            return count;
        }
    
        private void dfs(List<List<Integer>> graph, boolean[] visited, int node) {
            visited[node] = true; // Mark current node as visited
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    dfs(graph, visited, neighbor); // Visit unvisited neighbors
                }
            }
        }
    }


    //⏱️ time complexity: O(V + E) where V is the number of vertices and E is the number of edges
    //🧠 space complexity: O(V + E) for the adjacency list