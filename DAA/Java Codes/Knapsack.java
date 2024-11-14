// package Practicals;

import java.util.*;

public class Knapsack {

    static float fractionalKnapsack(int n, int[] p, int[] wt, int W, List<Integer> includedItems){
        float maxProfit = 0;

        float[][] ratios = new float[n][2];
        for(int i=0; i<n; i++){
            ratios[i][0] = i;
            ratios[i][1] = (float)p[i]/wt[i];
        }

        Arrays.sort(ratios, (o1, o2) -> Double.compare(o2[1], o1[1]));

        for(int i=0; i<n; i++){
            int currItem = (int)ratios[i][0];
            if(wt[currItem] < W){
                W -= wt[currItem];
                maxProfit += p[currItem];
                includedItems.add(currItem);
            }else{
                maxProfit += ((float)W/wt[currItem])*p[currItem];
                includedItems.add(currItem);
                break;
            }
        }

        System.out.println("Included items are : ");
        for(Integer i : includedItems){
            System.out.print((i+1) + " ");
        }
        System.out.println();
        
        return maxProfit;
    }
    
    static int dpKnapsack(int[] p, int[] wt, int cap, int n){
        int[][] dp = new int[n+1][cap+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=cap; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i-1]]);
            }
        }

        int includedItems[] = new int[n];
        int remWt = cap;

        for(int i=n; i>0; i--){
            if(dp[i][remWt] != dp[i-1][remWt]){
                includedItems[i-1] = 1;
                remWt -= wt[i-1];
            }
        }

        System.out.println("Included Items : ");
        for(int i=0; i<n; i++){
            if(includedItems[i] == 1) System.out.print(includedItems[i]+" ");
        }
        System.out.println();
        
        return dp[n][cap];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the no. of items : ");
        int n = sc.nextInt();

        int[] profits = new int[n];
        int[] wt = new int[n];

        System.out.print("Enter the weights : ");
        for(int i=0; i<n; i++){
            wt[i] = sc.nextInt();
        }

        System.out.print("Enter the profits : ");
        for(int i=0; i<n; i++){
            profits[i] = sc.nextInt();
        }

        System.out.print("Enter the capacity : ");
        int cap = sc.nextInt();

        float maxProfit1 = fractionalKnapsack(n, profits, wt, cap, new ArrayList<Integer>());
        System.out.println("Profit using Fractional Knapsack is : "+maxProfit1);

        int maxProfit2 = dpKnapsack(profits, wt, cap, n);
        System.out.println("Profit using 0/1 Knapsack is : "+maxProfit2);
    }
}