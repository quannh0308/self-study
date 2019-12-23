package com.quannh.paradigm.dynamic;

import java.util.Arrays;

/**
 * Largest divisible pairs subset
 * Given an array of n distinct elements, find length of the largest subset such that every pair in the subset is such that the larger element of the pair is divisible by smaller element.
 *
 * Examples:
 *
 * Input : arr[] = {10, 5, 3, 15, 20}
 * Output : 3
 * Explanation: The largest subset is 10, 5, 20.
 * 10 is divisible by 5, and 20 is divisible by 10.
 *
 * Input : arr[] = {18, 1, 3, 6, 13, 17}
 * Output : 4
 * Explanation: The largest subset is 18, 1, 3, 6,
 * In the subsequence, 3 is divisible by 1,
 * 6 by 3 and 18 by 6.
 */
public class LargestDivisiblePairsSubset {
    public static void main(String[] args) {
        int arr[] = new int[] {18,1,3,6,13,17};
        int largestDivisiblePairSubSet = largestDivisiblePairSubSet(arr);
        System.out.println("Output: " + largestDivisiblePairSubSet);
    }

    private static int largestDivisiblePairSubSet(int arr[]) {
        Arrays.sort(arr);
        int maxLength[] = new int[arr.length];
        maxLength[arr.length - 1] = 1;
        for(int i = arr.length - 2; i >= 0; i--) {
            maxLength[i] = 1;
            for(int j = i + 1; j < arr.length; j++)
                if(arr[j] % arr[i] == 0 && maxLength[j] + 1 > maxLength[i])
                    maxLength[i] = maxLength[j] + 1;
        }
        int max = 1;
        for(int i = 0; i < arr.length - 1; i++)
            if(maxLength[i] > max)
                max = maxLength[i];
        return max;
    }
}
