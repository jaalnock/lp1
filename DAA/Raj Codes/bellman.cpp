#include <bits/stdc++.h>
using namespace std;
#define INF INT_MAX

// Bellman-Ford Algorithm as a function
vector<int> bellman_ford(int V, vector<vector<int>>& edges, int S) {
    vector<int> dist(V, INF);
    dist[S] = 0;

    // Relax all edges V-1 times
    for (int i = 0; i < V - 1; i++) {
        for (auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if (dist[u] != INF && dist[u] + wt < dist[v]) {
                dist[v] = dist[u] + wt;
            }
        }
    }

    // Check for negative weight cycles (Nth relaxation)
    for (auto& edge : edges) {
        int u = edge[0];
        int v = edge[1];
        int wt = edge[2];
        if (dist[u] != INF && dist[u] + wt < dist[v]) {
            return {-1};  // Indicate negative cycle detected
        }
    }

    return dist;
}

int main() {
    int V, E;
    cout << "Enter the number of vertices: ";
    cin >> V;

    cout << "Enter the number of edges: ";
    cin >> E;

    vector<vector<int>> edges(E, vector<int>(3));

    cout << "Enter the edges (source destination weight) one by one:" << endl;
    for (int i = 0; i < E; i++) {
        cin >> edges[i][0] >> edges[i][1] >> edges[i][2];
    }

    int S;
    cout << "Enter the source vertex: ";
    cin >> S;

    // Call the Bellman-Ford function
    vector<int> dist = bellman_ford(V, edges, S);

    if (dist[0] == -1) {
        cout << "Graph contains a negative weight cycle." << endl;
    } else {
        cout << "The shortest distances from source " << S << " are:" << endl;
        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                cout << "INF ";
            } else {
                cout << dist[i] << " ";
            }
        }
        cout << endl;
    }

    return 0;
}