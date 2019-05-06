package com.quannh.paradigm.dynamic;
/*
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
knapsack-problem
A simple solution is to consider all subsets of items and calculate the total weight and value of all subsets. Consider the only subsets whose total weight is smaller than W. From all such subsets, pick the maximum value subset.

1) Optimal Substructure:
To consider all subsets of items, there can be two cases for every item: (1) the item is included in the optimal subset, (2) not included in the optimal set.
Therefore, the maximum value that can be obtained from n items is max of following two values.
1) Maximum value obtained by n-1 items and W weight (excluding nth item).
2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).

If weight of nth item is greater than W, then the nth item cannot be included and case 1 is the only possibility.

2) Overlapping Subproblems
Following is recursive implementation that simply follows the recursive structure mentioned above.
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int numItems = 3;
        int itemValues[] = {60, 100, 120};
        int itemWeights[] = {10, 20, 30};
        int totalItemsWeight = 50;
        System.out.printf("[Recursive] Maximum value with total weight %d is: %d\n", totalItemsWeight, recursiveBackpack(totalItemsWeight, itemWeights, itemValues, numItems));
        System.out.printf("[Dynamic  ] Maximum value with total weight %d is: %d\n", totalItemsWeight, dynamicBackpack(totalItemsWeight, itemWeights, itemValues, numItems));
    }

    private static int recursiveBackpack(int totalItemsWeight, int[] itemWeights, int[] itemValues, int numItems) {
        if(numItems == 0 || totalItemsWeight == 0)
            return 0;
        // if weight of nth item is greater than total wiehgt, exclude it
        if(itemWeights[numItems-1] > totalItemsWeight)
            return recursiveBackpack(totalItemsWeight, itemWeights, itemValues, numItems - 1);
        else {
            // return max of (1) nth item included or (2) nth item excluded
            return Math.max(
                    itemValues[numItems - 1] + recursiveBackpack(totalItemsWeight - itemWeights[numItems - 1], itemWeights, itemValues, numItems - 1),
                    recursiveBackpack(totalItemsWeight, itemWeights, itemValues, numItems - 1)
                    );
        }
    }

    private static int dynamicBackpack(int totalItemsWeight, int[] itemWeights, int[] itemValues, int numItems) {
        int weightMatrix[][] = new int[numItems + 1][totalItemsWeight + 1];
        for(int itemIdx = 0; itemIdx <= numItems; itemIdx++) {
            for(int weightIdx = 0; weightIdx <= totalItemsWeight; weightIdx++) {
                if(itemIdx == 0 || weightIdx == 0)
                    weightMatrix[itemIdx][weightIdx] = 0;
                else if(itemWeights[itemIdx - 1] <= weightIdx)
                    weightMatrix[itemIdx][weightIdx] = Math.max(itemValues[itemIdx - 1] + weightMatrix[itemIdx - 1][weightIdx - itemWeights[itemIdx-1]], weightMatrix[itemIdx-1][weightIdx]);
                else
                    weightMatrix[itemIdx][weightIdx] = weightMatrix[itemIdx-1][weightIdx];
            }
        }
        return weightMatrix[numItems][totalItemsWeight];
    }
}
