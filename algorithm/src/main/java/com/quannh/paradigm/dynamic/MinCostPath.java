package com.quannh.paradigm.dynamic;

public class MinCostPath {
    public static void main(String[] args) {
        {
            int[][] cost = {
                    {1,2,3},
                    {4,8,2},
                    {1,5,3}
            };
            int toX = 2, toY = 2;
            System.out.printf("Distance to [%d,%d] = %d\n", toX, toY, minCostPath(cost, toX, toY));
        }

        {
            int[][] cost = {
                    {1,     2,      3,      3,      23},
                    {4,     8,      2,      324,    32},
                    {1,     5,      3,      231,    32},
                    {32,    43,     24,     2,      1}
            };
            int toX = 3, toY = 4;
            System.out.printf("Distance to [%d,%d] = %d\n", toX, toY, minCostPath(cost, toX, toY));
        }

        {
            int[][] cost = {
                    {1,     2,      3,      3,      23,     322},
                    {4,     8,      2,      324,    32,     3242},
                    {1,     5,      3,      231,    32,     3232},
                    {32,    43,     24,     26666,  323232, 34},
                    {23,    22,     32,     9999,   9999,   9}
            };
            int toX = 4, toY = 5;
            System.out.printf("Distance to [%d,%d] = %d\n", toX, toY, minCostPath(cost, toX, toY));
        }
    }
    private static int minCostPath(int[][] cost, int toX, int toY) {
        int[][] distance = new int[toX+1][toY+1];
        distance[0][0] = cost[0][0];
        for(int i = 1; i <= toY; i++)
            distance[0][i] = distance[0][i-1] + cost[0][i];
        for(int j = 1; j <= toX; j++)
            distance[j][0] = distance[j-1][0] + cost[j][0];
        for(int i = 1; i <= toX; i++)
            for(int j = 1; j <= toY; j++) {
                distance[i][j] = cost[i][j] + Math.min(
                        Math.min(distance[i][j-1], distance[i-1][j]),
                        distance[i-1][j-1]
                );
            }
        return distance[toX][toY];
    }
}
