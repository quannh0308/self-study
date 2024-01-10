package com.quannh.guidetocompetitiveprogramming.chapter2programmingtechniques;

import java.util.ArrayList;
import java.util.List;

/**
 * Using recursion algorithm to generate subSets
 */
public class SubSet {
    private static final int N = 5;
    public static void main(String[] args) {
        List<Integer> subSet = new ArrayList<>();
        subSetFn(subSet, 0);
    }

    private static void subSetFn(List<Integer> subSet, int k) {
        if (k == N) {
            // print out the subSet
            System.out.println(subSet);
        } else {
            subSet.add(k);
            subSetFn(subSet, k+1);
            subSet.remove(subSet.size()- 1);
            subSetFn(subSet, k + 1);
        }

    }
}
