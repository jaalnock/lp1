//package DAA;

public class FractionalKnapsack {
    // Function to swap elements in the ratio array
    static void swapFunc(double[][] ratio, int x, int y) {
        double temp1 = ratio[x][0];
        double temp2 = ratio[x][1];
        ratio[x][0] = ratio[y][0];
        ratio[x][1] = ratio[y][1];
        ratio[y][0] = temp1;
        ratio[y][1] = temp2;
    }

    static int partition(double[][] ratio, int low, int high) {
        double pivot = ratio[low][1];
        int i = low + 1;

        for (int j = low + 1; j <= high; j++) {
            if (ratio[j][1] > pivot) {
                swapFunc(ratio, i, j);
                i++;
            }
        }
        swapFunc(ratio, low, i - 1);
        return (i - 1);
    }

    static void sortDesRatio(double[][] ratio, int low, int high) {
        if (low < high) {
            int pi = partition(ratio, low, high);
            sortDesRatio(ratio, low, pi - 1);
            sortDesRatio(ratio, pi + 1, high);
        }
    }

    static void print2DArr(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println("ratio[" + i + "][0] " + arr[i][0] + "  ratio[" + i + "][1] " + arr[i][1]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 5, 7, 1, 4, 1};
        int[] profit = {10, 5, 15, 7, 6, 18, 3};
        int capacity = 15;

        // Step 1: Store profit-to-weight ratios in a 2D array
        double[][] ratio = new double[weights.length][2];
        System.out.println("Ratio array before sorting:");
        for (int i = 0; i < weights.length; i++) {
            ratio[i][0] = i;  // Index
            ratio[i][1] = (double) profit[i] / weights[i];  // Profit-to-weight ratio
            System.out.println("ratio[" + i + "][0] " + ratio[i][0] + "  ratio[" + i + "][1] " + ratio[i][1]);
        }
        System.out.println();

        sortDesRatio(ratio, 0, ratio.length - 1);
        System.out.println("Ratio array after sorting:");
        print2DArr(ratio);

        double maxProfit = 0;
        int cap = capacity;
        int i = 0;

        while (cap > 0 && i < weights.length) {
            int currIdx = (int) ratio[i][0];
            if (weights[currIdx] <= cap) {
                maxProfit += profit[currIdx];
                cap -= weights[currIdx];
            } else {
                maxProfit += ratio[i][1] * cap;
                System.out.println("currItem: " + currIdx + "  weights[currIdx]: " + weights[currIdx] + "  rem Cap: " + 0 + "  maxProfit: " + maxProfit);
                break;
            }
            i++;
            System.out.println("currItem: " + currIdx + "  weights[currIdx]: " + weights[currIdx] + "  rem Cap: " + cap + "  maxProfit: " + maxProfit);
        }

        System.out.println();
        System.out.println("Maximum Profit is " + maxProfit);
    }
}