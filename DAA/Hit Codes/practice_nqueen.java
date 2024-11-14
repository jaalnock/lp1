import java.util.*;

//import static java.lang.System.out;

public class practice_nqueen {

// Java program to solve N Queen Problem using backtracking
   // final int N = 4;

    // A utility function to print solution
    static void printSolution(int board[][], int N)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

    // A utility function to check if a queen can
    // be placed on board[row][col]. Note that this
    // function is called when "col" queens are already
    // placeed in columns from 0 to col -1. So we need
    // to check only left side for attacking queens
    static boolean isSafe(int board[][], int row, int col, int N)
    {
        int i, j;

        // Check this row on left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // A recursive utility function to solve N
    // Queen problem
    static boolean solveNQUtil(int board[][], int col ,int N)
    {
        // Base case: If all queens are placed
        // then return true
        if (col >= N)
            return true;

        // Consider this column and try placing
        // this queen in all rows one by one
        for (int i = 0; i < N; i++) {

            // Check if the queen can be placed on
            // board[i][col]
            if (isSafe(board, i, col,N)) {

                // Place this queen in board[i][col]
                board[i][col] = 1;

                // Recur to place rest of the queens
                if (solveNQUtil(board, col + 1, N) == true)
                    return true;

                // If placing queen in board[i][col]
                // doesn't lead to a solution then
                // remove queen from board[i][col]
                board[i][col] = 0; // BACKTRACK
            }
        }

        // If the queen can not be placed in any row in
        // this column col, then return false
        return false;
    }

    // This function solves the N Queen problem using
    // Backtracking.  It mainly uses solveNQUtil () to
    // solve the problem. It returns false if queens
    // cannot be placed, otherwise, return true and
    // prints placement of queens in the form of 1s.
    // Please note that there may be more than one
    // solutions, this function prints one of the
    // feasible solutions.
    static boolean solveNQ(int N)
    {   int board[][] = new int[N][N];
        for(int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;

            }
        }

        if (solveNQUtil(board, 0,N) == false) {
            System.out.print("Solution does not exist");
            return false;
        }

        printSolution(board,N);
        return true;
    }
    // Driver program to test above function
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number");
        int N=sc.nextInt();
       // practice_nqueen Queen = new practice_nqueen();
        solveNQ(N);
    }
}



