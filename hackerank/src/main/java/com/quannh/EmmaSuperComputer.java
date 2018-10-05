package com.quannh;

import sun.security.acl.GroupImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmmaSuperComputer {


    // Complete the twoPluses function below.
    static int twoPluses(String[] grid) {
        int ret = 0;
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                int MAX_LENGTH1 = maximumPlusLengthAt(grid, i, j);
                if(MAX_LENGTH1 != 0) {
                    for(int length1 = 1; length1 <= MAX_LENGTH1; length1+=4) {
                        markAPlusAsBadAtAPoint(grid, i, j, length1);
                        int length2 = maximumPlusLengthOfGrid(grid);
                        if(length1 * length2 > ret)
                            ret = length1 * length2;
                        markAPlusAsGoodAtAPoint(grid, i, j, length1);
                    }
                }
            }
        }

        return ret;
    }

    private static int maximumPlusLengthOfGrid(String[] grid) {
        int max = 0;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length(); j++) {
                int current = maximumPlusLengthAt(grid, i, j);
                if(current > max)
                    max = current;
            }
        return max;
    }


    private static int maximumPlusLengthAt(String[] grid, int row, int col) {
        if(grid[row].charAt(col) != 'G')
            return 0;
        int ret = 0;
        int numRow = grid.length;
        int numCol = grid[0].length();
        while(true) {
            if(row - ret <= 0 || row + ret >= numRow - 1 || col - ret <= 0 || col + ret >= numCol - 1) {
//                System.out.println("Max length: " + (ret*2-1));
                return ret * 4 +1;
            }

            if(grid[row].charAt(col-ret-1) != 'G' || grid[row].charAt(col+ret+1) != 'G' || grid[row-ret-1].charAt(col) != 'G' || grid[row+ret+1].charAt(col) != 'G') {
//                System.out.println("Max length: " + (ret*2-1));
                return ret * 4 +1;
            }
            ret++;
        }
    }

    private static void markAPlusAsBadAtAPoint(String[] grid, int row, int col, int length) {
//        System.out.println("markAPlusAsBadAtAPoint - length  = " + length);
        length = length/4; // (length-3)/4
        for(int i = row - length; i <= row + length; i++) {
            String old = grid[i];
            grid[i] = old.substring(0, col) + 'B' + old.substring(col + 1);
        }
        String old = grid[row];
        StringBuilder newGoodChainSB = new StringBuilder();
        for(int i = 0; i < length * 2  + 1; i++)
            newGoodChainSB.append('B');
//        grid[row] = old.substring(0, col - length) + newGoodChainSB.toString() + old.substring(col+length);
        grid[row] = new StringBuilder(old).replace(col-length, col + length+1, newGoodChainSB.toString()).toString();
    }

    private static void markAPlusAsGoodAtAPoint(String[] grid, int row, int col, int length) {
//        System.out.println("markAPlusAsGoodAtAPoint - length  = " + length);
        length = length/4; // (length-3)/4
        for(int i = row - length; i <= row + length; i++) {
            String old = grid[i];
            grid[i] = old.substring(0, col) + 'G' + old.substring(col + 1);
        }
        String old = grid[row];
        StringBuilder newGoodChainSB = new StringBuilder();
        for(int i = 0; i < length * 2  + 1; i++)
            newGoodChainSB.append('G');
//        grid[row] = old.substring(0, col - length) + newGoodChainSB.toString() + old.substring(col+length);
        grid[row] = new StringBuilder(old).replace(col-length, col + length+1, newGoodChainSB.toString()).toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);


        System.out.printf("Result: " + String.valueOf(result));

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        scanner.close();
    }
}
