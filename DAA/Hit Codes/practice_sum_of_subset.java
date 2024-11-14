public class practice_sum_of_subset {
    static void displaySubset(int subSet[], int size) {
        for(int i = 0; i < size; i++) {
            System.out.print(subSet[i] + "  ");
        }
        System.out.println();
    }
    static void subsetSum(int set[], int subSet[], int n, int subSize, int total, int nodeCount ,int sum) {
        if(nodeCount == n) return;
        if( total == sum) {
            //print the subset
            displaySubset(subSet, subSize);
            //for other subsets
            if (subSize != 0)
                subsetSum(set,subSet,n,subSize-1,total-set[nodeCount],nodeCount+1,sum);
            return;
        } else {
            //find node along breadth
            for( int i = nodeCount; i < n; i++ ) {
                subSet[subSize] = set[i];
                //do for next node in depth
                subsetSum(set,subSet,n,subSize+1,total+set[i],i+1,sum);
            }
        }
    }
    static void findSubset(int set[], int size, int sum) {
        //create subset array to pass parameter of subsetSum
        int subSet[] = new int[size];
        subsetSum(set, subSet, size, 0, 0, 0, sum);
    }
    public static void main(String[] args) {
        int weights[] = {1, 2, 3 ,4,5,6};
        int size = 6;
        findSubset(weights, size, 6);
    }
}
