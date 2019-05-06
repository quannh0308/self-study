package com.quannh.hackerlandradiotransmitters;
import java.io.*;
import java.util.*;

public class Solution {

    // Complete the hackerlandRadioTransmitters function below.
    static int hackerlandRadioTransmitters(int[] x, int k) {
        List<Integer> houseList = new ArrayList<>();
        for(int item : x) {
            houseList.add(item);
        }

        Collections.sort(houseList);
        int countLeftToRight = 0;
        {
            int nextIndexNotToBeCovered = 0;
            while (nextIndexNotToBeCovered < houseList.size()) {
                countLeftToRight++;
                nextIndexNotToBeCovered = coverNextLeftToRight(houseList, nextIndexNotToBeCovered, k);
            }
        }

        int countRightToLeft = 0;
        {
            int nextIndexNotToBeCovered = houseList.size()-1;
            while (nextIndexNotToBeCovered >= 0) {
                countRightToLeft++;
                nextIndexNotToBeCovered = coverNextRightToLeft(houseList, nextIndexNotToBeCovered, k);
            }
        }

        return countLeftToRight > countRightToLeft ? countRightToLeft : countLeftToRight;

    }

    private static int coverNextLeftToRight(List<Integer> houseList, int currentIndex, int range) {
        if(currentIndex == houseList.size()-1)
            return houseList.size();
        int indexOfStation = currentIndex;
        while(indexOfStation < houseList.size() - 1 && houseList.get(indexOfStation + 1) - houseList.get(currentIndex) <= range)
            indexOfStation++;
        int nextIndex = indexOfStation;
        while(nextIndex < houseList.size() - 1 && houseList.get(nextIndex + 1) - houseList.get(indexOfStation) <= range)
            nextIndex++;
        // return next item not to be covered
        return nextIndex + 1;
    }

    private static int coverNextRightToLeft(List<Integer> houseList, int currentIndex, int range) {
        if(currentIndex == 0)
            return -1;
        int indexOfStation = currentIndex;
        while(indexOfStation >= 1 && houseList.get(currentIndex) - houseList.get(indexOfStation-1) <= range)
            indexOfStation--;
        int nextIndex = indexOfStation;
        while(nextIndex >= 1 && houseList.get(indexOfStation) - houseList.get(nextIndex - 1) <= range)
            nextIndex--;
        // return next item not to be covered
        return nextIndex-1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] x = new int[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int xItem = Integer.parseInt(xItems[i]);
            x[i] = xItem;
        }

        int result = hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}