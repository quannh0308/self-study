package com.quannh.guidetocompetitiveprogramming.chapter3effieciency;

public class MaxSubArraySum {
    public static void main(String[] args) {
        int arr[] = new int[] {10, 93, -193,2 , 3 , 3, 99, 12, 32, 4, 2, -2, -8, 99, -11, -22, -55, 99, 23, 32, 43, 12, 55, -7};

        int best = 0, localBest = 0;
        
        for (int i = 0; i < arr.length; i++) {
            localBest = Math.max(arr[i], localBest + arr[i]);
            best = Math.max(best, localBest);
        }

        System.out.println("Max sum: " + best);
    }
}
