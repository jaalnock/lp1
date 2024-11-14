#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Structure for an item in the knapsack
struct Item {
    int weight;
    int value;
};

// Comparison function to sort items based on value-to-weight ratio
bool compare(const Item &a, const Item &b) {
    double r1 = (double)a.value / a.weight;
    double r2 = (double)b.value / b.weight;
    return r1 > r2;
}

// Function to get the maximum value of items that can be carried
double fractionalKnapsack(int W, vector<Item> &items) {
    // Sort items based on their value-to-weight ratio
    sort(items.begin(), items.end(), compare);

    double maxValue = 0.0; // Maximum value accumulated
    for (const auto &item : items) {
        if (W >= item.weight) {
            // If the knapsack can carry the whole item, take it all
            W -= item.weight;
            maxValue += item.value;
        } else {
            // If not, take a fraction of the remaining item
            maxValue += item.value * ((double)W / item.weight);
            break;
        }
    }
    return maxValue;
}

int main() {
    int W = 50; // Total weight the knapsack can carry
    vector<Item> items = {{10, 60}, {20, 100}, {30, 120}};

    cout << "Maximum value in Knapsack = " << fractionalKnapsack(W, items) << endl;
    return 0;
}