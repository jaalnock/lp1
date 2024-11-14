#include <iostream>
#include <vector>

using namespace std;

// Function to print the current subset
void printSubset(const vector<int>& subset) {
    for (int num : subset) {
        cout << num << " ";
    }
    cout << endl;
}

// Function to find all subsets with sum equal to the target using backtracking
void subsetSum(const vector<int>& set, int target, int index, int currentSum, vector<int>& currentSubset) {
    // If current sum matches target, print the subset
    if (currentSum == target) {
        printSubset(currentSubset);
        return;
    }
    
    // If we have considered all elements, return
    if (index == set.size()) {
        return;
    }

    // Include the current element in the subset
    currentSubset.push_back(set[index]);
    subsetSum(set, target, index + 1, currentSum + set[index], currentSubset);
    
    // Exclude the current element from the subset (backtrack)
    currentSubset.pop_back();
    subsetSum(set, target, index + 1, currentSum, currentSubset);
}

int main() {
    vector<int> set = {3, 34, 4, 12, 5, 2};  // Example set
    int target = 9;  // Example target sum
    vector<int> currentSubset;  // Vector to store the current subset being considered
    
    cout << "Subsets with sum " << target << ":" << endl;
    subsetSum(set, target, 0, 0, currentSubset);

    return 0;
}