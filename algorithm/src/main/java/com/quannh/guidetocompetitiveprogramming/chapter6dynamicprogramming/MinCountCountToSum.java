package com.quannh.guidetocompetitiveprogramming.chapter6dynamicprogramming;

/**
 * Given input array of k coins, and a number N.
 * Find number of minimum coints from k coins to construct a sum of N.
 */
public class MinCountCountToSum {
    public static void main(String[] args) {
        int coins[] = new int[] {
            1, 3, 4, 5
        };
        int N = 10;

        int coinConstructAt[] = new int[N + 1];
        int minCoinAt[] = new int[N + 1];

        minCoinAt[0] = 0;

        for (int x = 1; x <= N; x++) {
            minCoinAt[x] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (x - coin >= 0 && minCoinAt[x] > minCoinAt[x - coin] + 1) {
                    minCoinAt[x] = minCoinAt[x - coin] + 1;
                    coinConstructAt[x] = coin;
                }
            }
        }

        System.out.println("Min coins: " + minCoinAt[N]);

        while (N > 0) {
            System.out.println(coinConstructAt[N]);
            N -= coinConstructAt[N];
        }
    }
}
