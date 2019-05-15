package com.quannh.paradigm.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        int N = 10;
        permutation(N);
    }

    private static void permutation(int n) {
        List<Integer> numberList = new ArrayList<>();
        for(int i = 1; i <= n; i++)
            numberList.add(i);
        recursivePermutation("", numberList);
    }

    private static void recursivePermutation(String prefix, List<Integer> numberList) {
        String PREFIX = prefix;
        if(numberList.isEmpty()) {
            System.out.println(prefix.substring(0, prefix.length()-2)); // remove the last character ","
        } else {
            // pick one number from number list
            for(int i = 0; i < numberList.size(); i++) {
                prefix = PREFIX + numberList.get(i) + ", ";
                List<Integer> newNumberList = new ArrayList<>();
                for(int j = 0; j < numberList.size(); j++) {
                    if(j != i)
                        newNumberList.add(numberList.get(j));
                }
                recursivePermutation(prefix, newNumberList);
            }
        }
    }
}
