#include <iostream>
using namespace std;

struct Item {
    int profit, weight;
};

int mymax(int a, int b) {
    return (a > b) ? a : b;
}

int knapsack(Item arr[], int W, int n, int selectedItems[]) {
    int table[100][100] = {0}; // Assuming a maximum of 100 items and weight

    // Fill the table using the standard 0/1 knapsack DP approach
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= W; j++) {
            if (i == 0 || j == 0) {
                table[i][j] = 0;
            } else if (arr[i - 1].weight <= j) {
                table[i][j] = mymax(arr[i - 1].profit + table[i - 1][j - arr[i - 1].weight], table[i - 1][j]);
            } else {
                table[i][j] = table[i - 1][j];
            }
        }
    }

    // Backtrack to find which items are included
    int w = W;
    int count = 0;
    for (int i = n; i > 0 && w > 0; i--) {
        if (table[i][w] != table[i - 1][w]) {
            selectedItems[count++] = i - 1; // Store the index of selected item
            w -= arr[i - 1].weight;
        }
    }

    return table[n][W];
}

int main() {
    int n, W;

    // Input the number of items
    cout << "Enter the number of items (n): ";
    cin >> n;

    // Input the maximum capacity of the knapsack
    cout << "Enter the capacity of the knapsack (W): ";
    cin >> W;

    // Array to hold items
    Item arr[100]; // Assuming a maximum of 100 items
    cout << "Enter the profit and weight of each item:\n";
    for (int i = 0; i < n; i++) {
        cout << "Item " << i + 1 << " - Profit: ";
        cin >> arr[i].profit;
        cout << "Item " << i + 1 << " - Weight: ";
        cin >> arr[i].weight;
    }

    int selectedItems[100]; // Array to hold selected item indices
    int maxProfit = knapsack(arr, W, n, selectedItems);

    // Output the maximum profit
    cout << "\nMaximum profit: " << maxProfit << endl;

    // Output the selected items in 1-based index
    cout << "Items included (1-based index): ";
    for (int i = 0; selectedItems[i] != -1 && i < n; i++) {
        cout << (selectedItems[i] + 1) << " "; // Print 1-based index
    }
    cout << endl;

    return 0;
}
