#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int knapsack(vector<int> &weights, vector<int> &values, int capacity) {
    int n = weights.size();
    vector<vector<int>> soln(n + 1, vector<int>(capacity + 1, 0));

    for (int i = 1; i <= n; ++i) {
        for (int w = 1; w <= capacity; ++w) {
            if (weights[i - 1] <= w) {
                soln[i][w] = max(soln[i - 1][w], soln[i - 1][w - weights[i - 1]] + values[i - 1]);
            } else {
                soln[i][w] = soln[i - 1][w];
            }
        }
    }

    // Print the DP table
    cout << "DP Table:" << endl;
    for (int i = 0; i <= n; ++i) {
        for (int w = 0; w <= capacity; ++w) {
            cout << soln[i][w] << " ";
        }
        cout << endl;
    }

    return soln[n][capacity];
}

int main() {
    int n{}, capacity{}, P{};
    cin >> n;
    
    vector<int> weights(n);
    vector<int> values(n);

    // Input the weights of items
    for (int i = 0; i < n; ++i) {
        cin >> weights[i];
    }

    // Input the values of items
    for (int i = 0; i < n; ++i) {
        cin >> values[i];
    }

    // Input the capacity of the knapsack
    cin >> capacity;

    if (capacity == 0) {
        cout << "No capacity found!" << endl;
    } else {
        P = knapsack(weights, values, capacity);
        cout << "The maximum value that can be obtained is: " << P << endl;
    }

    return 0;
}