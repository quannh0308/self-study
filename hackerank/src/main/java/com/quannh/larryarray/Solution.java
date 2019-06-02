package com.quannh.larryarray;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the larrysArray function below.
    static String larrysArray(int[] A) {
        List<Integer> numList = new ArrayList<>();
        List<Integer> sortedNumList = new ArrayList<>();
        for(int a : A) {
            numList.add(a);
            sortedNumList.add(a);
        }
        Collections.sort(sortedNumList);
        int sortedIdx = 0;
        while(true) {
            if(numList.size() == 0 || numList.size() == 1)
                return "YES";
            if(numList.size() == 2) {
                if(numList.get(0) < numList.get(1))
                    return "YES";
                else
                    return "NO";
            }
            int index = numList.indexOf(sortedNumList.get(sortedIdx));
            if(index == 0) {
                numList.remove(0);
                sortedIdx++;
            } else if(index == 1) {
                Collections.rotate(numList.subList(0, 3), 2);
                numList.remove(0);
                sortedIdx++;
            } else {
                Collections.rotate(numList.subList(index-2, index+1), 1);
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] A = new int[n];

            String[] AItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            String result = larrysArray(A);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
