// package Practicals;

import java.util.ArrayList;

public class SubsetSum{
    static ArrayList<ArrayList<Integer>> ls;

    public static void getSubSets(int[] arr, int n, int target, ArrayList<Integer> res){
        if(target == 0) {
            ls.add(new ArrayList<>(res));
            return;
        }

        if(n == 0 || target < 0) return;

        //not include
        getSubSets(arr, n-1, target, res);

        //included
        if(arr[n-1] <= target) {
            res.add(arr[n - 1]);
            getSubSets(arr, n - 1, target - arr[n - 1], res);
            res.removeLast();
        }
    }

    public static void main(String[] args) {
        ls = new ArrayList<>();
        int n = 5;
        int[]  arr = {1,2,3,5,2};
        int target = 5;
        getSubSets(arr, n, target, new ArrayList<>());

        if(!ls.isEmpty()){
            for(ArrayList<Integer> ans : ls){
                for(Integer x : ans){
                    System.out.print(x+" ");
                }
                System.out.println();
            }
        }else{
            System.out.println("No such target is possible");
        }
    }
}