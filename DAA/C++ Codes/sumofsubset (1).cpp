#include <iostream>
using namespace std;

bool foundSolution = false;

void findSubsetSums(int index, int n, int set[], int targetSum, int subset[], int subsetSize) {
    // Base case: if targetSum is zero and subset is non-empty, print the subset in reverse order
    if (targetSum == 0 && subsetSize > 0) {
        foundSolution = true;
        cout << "[ ";
        for (int i = subsetSize - 1; i >= 0; i--) {  // Print in reverse order
            cout << subset[i] << " ";
        }
        cout << "] ";
        return;
    }

    // If all elements are considered and no solution found, return
    if (index == n) return;

    // Recur without including the current element
    findSubsetSums(index + 1, n, set, targetSum, subset, subsetSize);

    // Include the current element and recur with reduced target
    subset[subsetSize] = set[index];
    findSubsetSums(index + 1, n, set, targetSum - set[index], subset, subsetSize + 1);
}

int main() {
    int n, targetSum;

    // Input number of elements in the set
    cout << "Enter the number of elements in the set: ";
    cin >> n;

    int set[n];

    // Input the elements of the set
    cout << "Enter the elements of the set: ";
    for (int i = 0; i < n; i++) {
        cin >> set[i];
    }

    // Input the target sum
    cout << "Enter the target sum: ";
    cin >> targetSum;

    int subset[n];  // Array to store current subset

    cout << "Subsets that sum to " << targetSum << ": ";
    findSubsetSums(0, n, set, targetSum, subset, 0);

    // If no solution is found
    if (!foundSolution) {
        cout << "No subset found that sums to " << targetSum << endl;
    }

    return 0;
}
