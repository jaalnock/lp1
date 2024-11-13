//package DAA;

import java.util.Scanner;

public class DPKnapsack {  // TC & SC : O(n*W)

    public static int knapsack(int W, int n, int[] profits, int[] weights, int[][] v) {
        for (int i = 1; i<=n; i++) {
            for (int j = 0; j <= W; j++) {
                int inc = 0;
                if (j >= weights[i-1]) {
                    inc = v[i - 1][j - weights[i-1]] + profits[i-1];
                }
                int exc = v[i - 1][j];
                v[i][j] = Math.max(inc, exc);
            }
        }

        // To find the included items, we need to trace back the optimal solution
        int[] includedItems = new int[n];  // Tracks whether each item is included
        int remainingWeight = W;
        
        // Trace back the optimal solution from the DP table
        for (int i = n; i > 0; i--) {
            if (v[i][remainingWeight] != v[i - 1][remainingWeight]) {
                includedItems[i - 1] = 1;  // Item i is included
                remainingWeight -= weights[i - 1];  // Reduce the remaining weight by the weight of the item
            } else {
                includedItems[i - 1] = 0;  // Item i is not included
            }
        }

        // Print the included items
        System.out.print("Included items: ");
        for (int i = 0; i < n; i++) {
            if (includedItems[i] == 1) {
                System.out.print((i + 1) + " ");  // Print the item number (1-indexed)
            }
        }
        System.out.println();

        return v[n][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter the number of items: ");
        int n = 4;
//        System.out.print("Enter the maximum weight capacity: ");
        int W = 8;

        int[] profits = {1,2,5,6};
        int[] weights = {2, 3, 4, 5};

//        System.out.println("Enter Profits: ");
//        for (int i = 0; i < n; i++) {
//            profits[i] = scanner.nextInt();
//        }

//        System.out.println("Enter Weights: ");
//        for (int i = 0; i < n; i++) {
//            weights[i] = scanner.nextInt();
//        }

        int[][] v = new int[n + 1][W + 1];
        int result = knapsack(W, n, profits, weights, v);
        System.out.println("Maximum Profit: " + result);
    }
}