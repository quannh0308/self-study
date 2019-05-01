package com.quannh.paradigm.dynamic;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        List<Integer> inputList = Arrays.asList(10, 22, 9, 33, 21, 50, 41, 60);
        System.out.println("Longest increasing subsequence:");
        System.out.println(longestIncreasingSubsequence(inputList));
    }

    private static List<Integer> longestIncreasingSubsequence(List<Integer> inputList) {
        List<Integer> result = new ArrayList<>();
        int maxAtIndex = -1, max = 1;
        List<Integer> prevIndex = new ArrayList<>();
        List<Integer> lengthAtIndex = new ArrayList<>();
        for(int i = 0; i < inputList.size(); i++) {
            prevIndex.add(-1);
            lengthAtIndex.add(1);
        }
        for(int i = 1; i < inputList.size(); i++)
            for(int j = 0; j < i; j++)
                if(inputList.get(i) > inputList.get(j) && lengthAtIndex.get(i) < lengthAtIndex.get(j) + 1) {
                    prevIndex.set(i, j);
                    lengthAtIndex.set(i, lengthAtIndex.get(i) + 1);
                    if(lengthAtIndex.get(i) > max) {
                        max = lengthAtIndex.get(i);
                        maxAtIndex = i;
                    }
                }
        do {
            result.add(inputList.get(maxAtIndex));
            maxAtIndex = prevIndex.get(maxAtIndex); // re-trace
        } while (maxAtIndex != -1);
        // reverse the result
        Collections.reverse(result);
        return result;
    }
}
