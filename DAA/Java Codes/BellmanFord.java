//package Practicals;

import java.util.*;

public class BellmanFord {
    public static void main(String[] args) {
        int V = 6;  // Number of vertices
        int S = 0;  // Source vertex

        // Define edges as a 2D array
        int[][] edges = {
                {3, 2, 6},  // edge from 3 to 2 with weight 6
                {5, 3, 1},  // edge from 5 to 3 with weight 1
                {0, 1, 5},  // edge from 0 to 1 with weight 5
                {1, 5, -3}, // edge from 1 to 5 with weight -3
                {1, 2, -2}, // edge from 1 to 2 with weight -2
                {3, 4, -2}, // edge from 3 to 4 with weight -2
                {2, 4, 3}   // edge from 2 to 4 with weight 3
        };

        // Call the Bellman-Ford function
        int[] dist = bellman_ford(V, edges, S);

        // Print the distance from the source to each vertex
        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println("");
    }

    // Bellman-Ford function
    static int[] bellman_ford(int V, int[][] edges, int S) {
        int[] dist = new int[V];

        // Initialize distances to a large value
        for (int i = 0; i < V; i++) dist[i] = (int)(1e8);
        dist[S] = 0;  // Distance to source is 0

        // V-1 times relax the edges
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Check for negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                int[] temp = new int[1];
                temp[0] = -1;  // Return -1 to indicate a negative cycle
                return temp;
            }
        }

        return dist;
    }
}
