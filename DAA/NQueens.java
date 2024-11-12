//package DAA;

import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of Queens : ");
        int n = sc.nextInt();
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], '_');
        }
        nQueen(n, 0, grid);
    }

    public static boolean isSafe(char[][] grid, int i, int j) {
        int x, y;
        // column check
        for (x = 0; x < i; x++) {
            if (grid[x][j] == 'Q') {
                return false;
            }
        }
        // left diagonal check
        for (x = i - 1, y = j - 1; x >= 0 && y >= 0; x--, y--) {
            if (grid[x][y] == 'Q') {
                return false;
            }
        }
        // right diagonal check
        for (x = i - 1, y = j + 1; x >= 0 && y < grid.length; x--, y++) {
            if (grid[x][y] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static boolean nQueen(int n, int noQ, char[][] grid) {
        if (noQ >= n) {
            System.out.println("Final result: ");
            printGrid(grid);
            //go for next
//            return false;

            // only one
            return true;
        }
        for (int j = 0; j < n; j++) {
            if (isSafe(grid, noQ, j)) {
                grid[noQ][j] = 'Q';
//                System.out.println("Iteration "+(noQ+1)+": ");
//                printGrid(grid);
                if (nQueen(n, noQ + 1, grid)) {
                    return true;
                }
                grid[noQ][j] = '_';
//                System.out.println("Backtrack: ");
//                printGrid(grid);
            }
        }
        return false;
    }

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
