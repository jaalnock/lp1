import java.util.Arrays;

public class practice_bellman_ford {
    // Java program to find single source shortest path using Bellman-Ford algorithm
    static int[] bellmanFord(int V, int[][] edges, int src) {

        // Initially distance from source to all other vertices is set to a large value (Infinity).
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;

        // Step 1: Relax all edges (V - 1) times.
        for (int i = 0; i < V; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];

                // If the distance to u is not "infinity" and a shorter path is found, update it.
                if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v]) {
                   if(i==V-1){
                       return new int[]{-1};
                   }
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Step 2: Check for negative-weight cycles by relaxing all edges one more time.

        // If no negative cycle is found, return the shortest distances.
        return dist;
    }

    public static void main(String[] args) {
        int V = 6;
        int[][] edges = new int[][] {
                {0, 1, 5},
                {1, 2, 2},
                {2, 3, 2},
                {3, 4, -3},
                {1, 5, 1},
                {4, 2, -1},
                {5, 4, 1}
        };

        int src = 0;
        int[] ans = bellmanFord(V, edges, src);
        if (ans.length > 1) {
            for (int dist = 0; dist < V; dist++) {
                System.out.println("vertex " + dist + " -> " + ans[dist]);
            }
        }else{
            System.out.println("negative cycle found.0");
        }
    }
}
