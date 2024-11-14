#include <iostream>
#include <vector>

using namespace std;

// Function to check if a queen can be placed at board[row][col]
bool isSafe(const vector<vector<int>> &board, int row, int col, int N) {
    // Check for queens in the same column
    for (int i = 0; i < row; i++) {
        if (board[i][col] == 1) {
            return false;
        }
    }

    // Check the upper left diagonal
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 1) {
            return false;
        }
    }

    // Check the upper right diagonal
    for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
        if (board[i][j] == 1) {
            return false;
        }
    }

    return true;
}

// Function to print the solution board
void printBoard(const vector<vector<int>> &board, int N) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << (board[i][j] == 1 ? "Q " : ". ");
        }
        cout << endl;
    }
    cout << endl;
}

// Recursive function to solve the N-Queens problem and print all solutions
void solveNQueens(vector<vector<int>> &board, int row, int N) {
    if (row == N) {
        // All queens are placed successfully; print the board
        printBoard(board, N);
        return;
    }

    // Try placing a queen in each column of the current row
    for (int col = 0; col < N; col++) {
        if (isSafe(board, row, col, N)) {
            board[row][col] = 1; // Place the queen

            // Recurse to place queens in the remaining rows
            solveNQueens(board, row + 1, N);

            // Backtrack if placing the queen here doesn't lead to a solution
            board[row][col] = 0;
        }
    }
}

int main() {
    int N;
    cout << "Enter the value of N: ";
    cin >> N;

    vector<vector<int>> board(N, vector<int>(N, 0));
    solveNQueens(board, 0, N);
    return 0;
}