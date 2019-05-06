package com.quannh.pairs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the pairs function below.
    static int pairs(int k, int[] arr) {
        List<Integer> numberList = new ArrayList<>();
        for(int item : arr) {
            numberList.add(item);
        }
        Collections.sort(numberList);
        int totalDiff = 0;
        for(int i = 0; i < numberList.size() - 1; i++) {
            totalDiff += Solution.countDiffAtIndex(numberList, k, i);
        }
        return totalDiff;
    }

    private static int countDiffAtIndex(List<Integer> numberList, int diff, int index) {
        int count = 0;
        for(int i = index+1; i < numberList.size();i++) {
            int currentDiff = numberList.get(i) - numberList.get(index);
            if(currentDiff == diff) {
                count++;
            } else if(currentDiff > diff) {
                break;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
