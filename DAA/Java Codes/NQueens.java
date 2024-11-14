// package Practicals;

public class NQueens {
    public static void main(String[] args) {
        int n = 5;
        int[][] grid = new int[n][n];
        displayPos(0, n, grid);
    }
    public static boolean displayPos(int currQ, int n, int[][] grid){
        if(currQ==n){
            displayGrid(grid, n);
            return false;
        }

        for(int j=0; j<n; j++) {
            if (isSafe(grid, n, currQ, j)) {
                grid[currQ][j] = 1;
                displayPos(currQ+1, n, grid);
                grid[currQ][j] = 0;
            }
        }
        return false;
    }
    public static boolean isSafe(int[][] grid, int n, int row, int col){
        //Up
        for(int i=row; i>=0; i--){
            if(grid[i][col] == 1) return false;
        }

        //Left Up
        for(int i=row, j=col; i>=0 && j>=0; i--,j--){
            if(grid[i][j] == 1) return false;
        }

        //Right Up
        for(int i=row, j=col; i>=0 && j<n; i--,j++){
            if(grid[i][j] == 1) return false;
        }

        return true;
    }
    public static void displayGrid(int[][]  grid, int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0)
                    System.out.print(" _ ");
                else
                    System.out.print(" Q ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
