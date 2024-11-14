#include <iostream> 
#include <vector> 
#include <climits> 
using namespace std;

int final_res = INT_MAX;  // Final result storing the minimum cost
vector<int> final_path;   // Path that leads to minimum cost

// Function to copy temporary solution to the final path 
void copyToFinal(vector<int>& curr_path) { 
    final_path = curr_path; 
} 

// Function to find the minimum edge cost in a row
int findMinEdge(vector<vector<int>>& adjMatrix, int i, int N) { 
    int min = INT_MAX; 
    for (int k = 0; k < N; k++) { 
        if (adjMatrix[i][k] < min && i != k) { 
            min = adjMatrix[i][k]; 
        } 
    } 
    return min; 
}

// Function to find the second minimum edge cost in a row
int findSecondMinEdge(vector<vector<int>>& adjMatrix, int i, int N) { 
    int first = INT_MAX, second = INT_MAX; 
    for (int j = 0; j < N; j++) { 
        if (i == j) continue; 
        if (adjMatrix[i][j] <= first) { 
            second = first; 
            first = adjMatrix[i][j]; 
        } 
        else if (adjMatrix[i][j] <= second && adjMatrix[i][j] != first) { 
            second = adjMatrix[i][j]; 
        } 
    } 
    return second; 
}

// Recursive function for branch and bound
void tspRecursive(vector<vector<int>>& adjMatrix, int curr_bound, int curr_weight, int level, 
                  vector<int>& curr_path, vector<bool>& visited, int N) { 
    if (level == N) { 
        // Check for returning to the starting point
        int last_to_first = adjMatrix[curr_path[level - 1]][curr_path[0]]; 
        if (last_to_first != 0) { 
            int curr_res = curr_weight + last_to_first; 
            if (curr_res < final_res) { 
                copyToFinal(curr_path); 
                final_res = curr_res; 
            } 
        } 
        return; 
    }

    for (int i = 0; i < N; i++) { 
        if (!visited[i]) { 
            int temp_bound = curr_bound;
            int temp_weight = curr_weight + adjMatrix[curr_path[level - 1]][i];

            // Update the bound to exclude the current edge
            if (level == 1) { 
                curr_bound -= (findMinEdge(adjMatrix, curr_path[0], N) + 
                               findMinEdge(adjMatrix, i, N)) / 2; 
            } else { 
                curr_bound -= (findSecondMinEdge(adjMatrix, curr_path[level - 1], N) + 
                               findMinEdge(adjMatrix, i, N)) / 2; 
            } 

            // Check if the new bound is promising
            if (curr_bound + temp_weight < final_res) { 
                curr_path[level] = i; 
                visited[i] = true; 
                // Recur to the next level
                tspRecursive(adjMatrix, curr_bound, temp_weight, level + 1, curr_path, visited, N); 
            }

            // Backtrack
            curr_bound = temp_bound; 
            visited[i] = false; 
        } 
    } 
}

// Function to solve TSP using Branch and Bound
void tspBranchAndBound(vector<vector<int>>& adjMatrix, int N) { 
    vector<int> curr_path(N + 1); 
    vector<bool> visited(N, false); 

    int curr_bound = 0;

    // Calculate initial lower bound
    for (int i = 0; i < N; i++) { 
        curr_bound += (findMinEdge(adjMatrix, i, N) + findSecondMinEdge(adjMatrix, i, N)) / 2; 
    } 

    visited[0] = true; 
    curr_path[0] = 0; 

    tspRecursive(adjMatrix, curr_bound, 0, 1, curr_path, visited, N); 

    cout << "The minimum cost to travel is: " << final_res << endl; 
    cout << "The path is: "; 
    for (int i = 0; i < N; i++) { 
        cout << final_path[i] << " -> "; 
    } 
    cout << final_path[0] << endl; 
}

int main() { 
    int N; 
    cout << "Enter the number of cities: "; 
    cin >> N;

    // Dynamically create the adjacency matrix
    vector<vector<int>> adjMatrix(N, vector<int>(N)); 

    // Input adjacency matrix 
    cout << "Enter the cost matrix (Enter 0 for no self-loops): " << endl; 
    for (int i = 0; i < N; i++) { 
        for (int j = 0; j < N; j++) { 
            cin >> adjMatrix[i][j]; 
        } 
    } 

    // Call the function to solve the TSP problem 
    tspBranchAndBound(adjMatrix, N); 

    return 0; 
}

/* 
  
Input: 
 
N - No. of cities 
Vector adjmatrix – Distance between cities 
 
Output: 
 
 #Testcase-1 
 
Enter the number of cities: 4 
Enter the cost matrix (Enter 0 for no self-loops):  
0 10 15 20 
10 0 35 25 
15 35 0 30 
20 25 30 0 
The minimum cost to travel is: 80 
The path is: 0 -> 1 -> 3 -> 2 -> 0 
 
#Testcase-2 
 
Enter the number of cities: 4 
Enter the cost matrix (Enter 0 for no self-loops):  
0 29 20 21 
29 0 15 17 
20 15 0 28 
21 17 28 0 
The minimum cost to travel is: 73 
The path is: 0 -> 2 -> 1 -> 3 -> 0 
 
 
#Testcase-3 
 
Enter the number of cities: 5 
Enter the cost matrix (Enter 0 for no self-loops):  
0 10 15 20 25 
10 0 35 30 20 
15 35 0 25 30 
20 30 25 0 15 
25 20 30 15 0 
The minimum cost to travel is: 85 
The path is: 0 -> 1 -> 4 -> 3 -> 2 -> 0
*/