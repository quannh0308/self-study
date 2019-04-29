package com.quannh.paradigm.dynamic;

public class Fibonacci {
    public static void main(String[] args) {
        int N = 9;
        System.out.println("Fibonnaci(" + N + ") = " + fib(N));
    }

    private static long fib(int n) {
        int a = 0, b = 1, c;
        if(n == 0)
            return a;
        for(int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
