package com.quannh.paradigm.dynamic;

public class UglyNumber {
    public static void main(String[] args) {
        int N = 150;
        for(int i = 1; i <= N; i++) {
            System.out.printf("Ugly[%d] = %d\n", i, getUglyNumberAtIndex(i));
        }
    }

    private static long getUglyNumberAtIndex(int n) {
        long[] ugly = new long[n];
        int i2 = 0, i3 = 0, i5 = 0;
        long nextMultiplyOf2 = 2;
        long nextMultiplyOf3 = 3;
        long nextMultiplyOf5 = 5;
        long nextUglyNumber = 1;
        ugly[0] = 1;
        for(int i = 1; i < n; i++) {
            // find the next one to store to array
            nextUglyNumber = Math.min(Math.min(nextMultiplyOf2, nextMultiplyOf3), nextMultiplyOf5);
            ugly[i] = nextUglyNumber;
            // recalculate the current multiplies that has chosen
            if(nextUglyNumber == nextMultiplyOf2) {
                i2++;
                nextMultiplyOf2 = ugly[i2] * 2;
            }
            if(nextUglyNumber == nextMultiplyOf3) {
                i3++;
                nextMultiplyOf3 = ugly[i3] * 3;
            }
            if(nextUglyNumber == nextMultiplyOf5){
                i5++;
                nextMultiplyOf5 = ugly[i5] * 5;
            }
        }
        return ugly[n-1];
    }
}
