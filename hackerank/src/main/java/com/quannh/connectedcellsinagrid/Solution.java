package com.quannh.connectedcellsinagrid;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix, int n, int m) {
        int max = 0;
        int current = 0;
        do {
            Map.Entry<Integer, Integer> firstNotZero = getFirstNotZero(matrix, n, m);
            int r = firstNotZero.getKey();
            int c = firstNotZero.getValue();
            if(r == n || c == m)
                return max; // not find any non-zero item
            current = inflate(matrix, n, m, r, c);
//            if(current == 0)
//                return max;
            if(current > max)
                max = current;
        } while (current >0);
        return max;
    }

    private static int inflate(int[][] matrix, int row, int col, int r, int c) {
        // first, mark the current value zero
        int returnValue = 1;
        matrix[r][c] = 0;
        // inflate 8 adjacent values
        if(r > 0 && c > 0 && matrix[r-1][c-1] != 0) {
            returnValue += inflate(matrix, row, col, r-1, c-1);
        }
        if(r > 0 && matrix[r-1][c] != 0) {
            returnValue += inflate(matrix, row, col,r-1, c);
        }
        if(r > 0 && c < col -1 && matrix[r-1][c+1] != 0) {
            returnValue += inflate(matrix, row, col,r-1, c+1);
        }

        if(c > 0 && matrix[r][c-1] != 0) {
            returnValue += inflate(matrix, row, col, r, c-1);
        }
        if(c < col -1 && matrix[r][c+1] != 0) {
            returnValue += inflate(matrix, row, col,r, c+1);
        }

        if(r < row -1 && c > 0 && matrix[r+1][c-1] != 0) {
            returnValue += inflate(matrix, row, col, r+1, c-1);
        }
        if(r < row -1 && matrix[r+1][c] != 0) {
            returnValue += inflate(matrix, row, col,r+1, c);
        }
        if(r < row -1 && c < col -1 && matrix[r+1][c+1] != 0) {
            returnValue += inflate(matrix, row, col,r+1, c+1);
        }
        return returnValue;
    }

    private static Map.Entry<Integer, Integer> getFirstNotZero(int[][] matrix, int row, int col) {
        for(int i = 0; i < row; i++)
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] != 0) {
                    return Map.entry(i,j);

                }
            }
        return Map.entry(row, col);

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix, n, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
