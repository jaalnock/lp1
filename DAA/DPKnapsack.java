//package DAA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DPKnapsack {  // TC & SC : O(n*W)

    public static int knapsack(int W, int n, int[] profits, int[] weights, int[][] v) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= W; j++) {
                int inc = 0;
                if (j - weights[i] >= 0) {
                    inc = v[i + 1][j - weights[i]] + profits[i];
                }
                int exc = v[i + 1][j];
                v[i][j] = Math.max(inc, exc);
            }
        }
        return v[0][W];
    }

    public static void findIncludedItems(int W, int n, int[] profits, int[] weights, int[][] v) {
        List<Integer> includedItems = new ArrayList<>();
        int j = W;

        for (int i = 0; i < n; i++) {
            if (j > 0 && (i == n - 1 || v[i][j] != v[i + 1][j])) {
                includedItems.add(i);
                j -= weights[i];
            }
        }

        System.out.println("Items included in the knapsack:");
        for (int item : includedItems) {
            System.out.println("Item " + (item + 1) + " (Profit: " + profits[item] + ", Weight: " + weights[item] + ")");
        }
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

        findIncludedItems(W, n, profits, weights, v);

    }
}