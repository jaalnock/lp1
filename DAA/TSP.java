//package Practicals;

import java.util.*;

public class TSP {

    // Define infinity value for comparison
    static final int INF = Integer.MAX_VALUE;

    // Node structure to store the state of the TSP
    static class Node {
        List<Pair> path;                      // Path visited so far
        int[][] matrixReduced;                // Reduced cost matrix
        int cost;                             // Lower bound or cost
        int vertex;                           // Current city number
        int level;                            // Level of the node in the search tree

        public Node(List<Pair> path, int[][] matrix, int cost, int vertex, int level) {
            this.path = path;
            this.matrixReduced = matrix;
            this.cost = cost;
            this.vertex = vertex;
            this.level = level;
        }
    }

    // Pair class to represent the pair of cities (from, to)
    static class Pair {
        int from, to;
        public Pair(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    // Function to create a new Node
    public static Node newNode(int[][] matrixParent, List<Pair> path, int level, int i, int j) {
        int N = matrixParent.length;
        int[][] matrixReduced = new int[N][N];
        for (int m = 0; m < N; m++) {
            matrixReduced[m] = Arrays.copyOf(matrixParent[m], N);
        }

        // Set outgoing and incoming edges to infinity
        for (int k = 0; level != 0 && k < N; k++) {
            matrixReduced[i][k] = INF;
            matrixReduced[k][j] = INF;
        }
        matrixReduced[j][0] = INF;

        List<Pair> newPath = new ArrayList<>(path);
        if (level != 0) {
            newPath.add(new Pair(i, j));
        }

        return new Node(newPath, matrixReduced, 0, j, level);
    }

    // Row reduction function
    public static void rowReduction(int[][] matrixReduced, int[] row) {
        int N = matrixReduced.length;
        Arrays.fill(row, INF);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrixReduced[i][j] < row[i]) {
                    row[i] = matrixReduced[i][j];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrixReduced[i][j] != INF && row[i] != INF) {
                    matrixReduced[i][j] -= row[i];
                }
            }
        }
    }

    // Column reduction function
    public static void columnReduction(int[][] matrixReduced, int[] col) {
        int N = matrixReduced.length;
        Arrays.fill(col, INF);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrixReduced[i][j] < col[j]) {
                    col[j] = matrixReduced[i][j];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrixReduced[i][j] != INF && col[j] != INF) {
                    matrixReduced[i][j] -= col[j];
                }
            }
        }
    }

    // Function to calculate the cost of the reduced matrix
    public static int costCal(int[][] matrixReduced) {
        int cost = 0;
        int N = matrixReduced.length;
        int[] row = new int[N];
        rowReduction(matrixReduced, row);
        int[] col = new int[N];
        columnReduction(matrixReduced, col);

        for (int i = 0; i < N; i++) {
            cost += (row[i] != INF) ? row[i] : 0;
            cost += (col[i] != INF) ? col[i] : 0;
        }

        return cost;
    }

    // Function to print the path taken by the salesman
    public static void pathTaken(List<Pair> path) {
        System.out.println("\nPath taken: ");
        for (Pair p : path) {
            System.out.println((p.from + 1) + "->" + (p.to + 1));
        }
    }

    // Comparator for priority queue to order nodes based on their cost
    static class NodeComparator implements Comparator<Node> {
        public int compare(Node lhs, Node rhs) {
            return Integer.compare(lhs.cost, rhs.cost);
        }
    }

    // Function to solve the Traveling Salesman Problem using Branch and Bound
    public static int TSP(int[][] costMatrix) {
        int N = costMatrix.length;
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        List<Pair> path = new ArrayList<>();
        Node root = newNode(costMatrix, path, 0, -1, 0);
        root.cost = costCal(root.matrixReduced);

        pq.add(root);

        while (!pq.isEmpty()) {
            Node mini = pq.poll();
            int i = mini.vertex;

            if (mini.level == N - 1) {
                mini.path.add(new Pair(i, 0));
                pathTaken(mini.path);
                return mini.cost;
            }

            for (int j = 0; j < N; j++) {
                if (mini.matrixReduced[i][j] != INF) {
                    Node child = newNode(mini.matrixReduced, mini.path, mini.level + 1, i, j);
                    child.cost = mini.cost + mini.matrixReduced[i][j] + costCal(child.matrixReduced);
                    pq.add(child);
                }
            }
        }

        return 0;
    }

    // Main function to take input and call the TSP function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int N = sc.nextInt();

        // Create a dynamic cost matrix
        int[][] costMatrix = new int[N][N];

        System.out.println("Enter the cost matrix:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                costMatrix[i][j] = sc.nextInt();
                if (i == j) {
                    costMatrix[i][j] = INF; // Set diagonal elements to INF (no self-loops)
                }
            }
        }

        int result = TSP(costMatrix);
        System.out.println("\nTotal Cost :: " + result + "\n");
    }
}

