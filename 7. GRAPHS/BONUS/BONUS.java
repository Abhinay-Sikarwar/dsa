//  NOTE: QUESTION NUMBERS ARE REFERED TO LEETCODE
//  7. GRAPHS
//--------------------------------------------------------- BONUS QUESTION----------------------------------------------------------

    // 924: MINIMIZE MALWARE SPREAD
// REMOVE THE UNIQUE-INFECTED NODE SAVING THE MOST NODES USING UNION-FIND.

    class Solution {
        public int minMalwareSpread(int[][] graph, int[] initial) {
            int n = graph.length;
            UnionFind uf = new UnionFind(n);
    
            // Build connected components using Union-Find
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    if (graph[i][j] == 1) uf.union(i, j);
    
            // Compute size of each connected component
            var size = new int[n];
            for (int i = 0; i < n; i++) size[uf.find(i)]++;
    
            // Count infected nodes per component
            var infectedCount = new int[n];
            for (int node : initial) infectedCount[uf.find(node)]++;
    
            // Sort infected list to handle tie (smallest index)
            Arrays.sort(initial);
    
            int bestNode = initial[0], maxSaved = -1;
    
            // For each infected node, check if removing it saves its component
            for (int node : initial) {
                int root = uf.find(node);
                if (infectedCount[root] == 1 && size[root] > maxSaved) {
                    maxSaved = size[root];  // max nodes saved
                    bestNode = node;        // best node to remove
                }
            }
    
            return bestNode;
        }
    
        // Union-Find (Disjoint Set Union) with path compression + rank
        private class UnionFind {
            int[] parent, rank;
    
            UnionFind(int n) {
                parent = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i++) parent[i] = i;
            }
    
            int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }
    
            void union(int a, int b) {
                int pa = find(a), pb = find(b);
                if (pa == pb) return;
                if (rank[pa] < rank[pb]) parent[pa] = pb;
                else if (rank[pa] > rank[pb]) parent[pb] = pa;
                else { parent[pb] = pa; rank[pa]++; }
            }
        }
    }

    // ⏱️ Time Complexity:  O(N²) — we traverse the adjacency matrix once to build connected components.
    // 🧠 Space Complexity: O(N)  — for Union-Find parent/rank arrays and component tracking.