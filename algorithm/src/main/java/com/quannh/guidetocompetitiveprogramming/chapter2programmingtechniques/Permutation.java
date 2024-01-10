package com.quannh.guidetocompetitiveprogramming.chapter2programmingtechniques;

import java.util.ArrayList;
import java.util.List;

/**
 * Using recursion algorithm to generate Permutations
 */
public class Permutation {
    private static final int N = 6;
    public static void main(String[] args) {
        List<Integer> permutation = new ArrayList<>();
        List<Boolean> chosen = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            chosen.add(false);
        }

        permutationFn(permutation, chosen);
    }

    private static void permutationFn(List<Integer> permutation, List<Boolean> chosen) {
        if (permutation.size() == N) {
            System.out.println(permutation);
        } else {
            for (int i = 0; i < N; i++) {
                if (chosen.get(i)) {
                    continue;
                }
                chosen.set(i, true);
                permutation.add(i);
                permutationFn(permutation, chosen);
                chosen.set(i, false);
                permutation.remove(permutation.size() - 1);
            }
        }
    }
}
