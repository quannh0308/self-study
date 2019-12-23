package com.quannh.bearandsteadygene;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the steadyGene function below.
    static int steadyGene(String gene) {
        int equalCount = gene.length()/4;
        int maxLeft = 0, minRight = gene.length() - 1;
        // move left to right
        int countALeft = 0, countCLeft = 0, countGLeft = 0, countTLeft = 0;
        while(maxLeft < gene.length()) {
            char charAt = gene.charAt(maxLeft++);
            if(charAt == 'A')
                countALeft++;
            else if(charAt == 'C')
                countCLeft++;
            else if(charAt == 'G')
                countGLeft++;
            else
                countTLeft++;

            if(countALeft > equalCount) {
                countALeft--;
                maxLeft--;
                break;
            }
            if(countCLeft > equalCount) {
                countALeft--;
                maxLeft--;
                break;
            }
            if(countGLeft > equalCount) {
                countALeft--;
                maxLeft--;
                break;
            }
            if(countTLeft > equalCount) {
                countALeft--;
                maxLeft--;
                break;
            }
        }

        // move right to left
        int countARight = 0, countCRight = 0, countGRight = 0, countTRight = 0;
        while(minRight >= 0) {
            char charAt = gene.charAt(minRight--);
            if(charAt == 'A')
                countARight++;
            else if(charAt == 'C')
                countCRight++;
            else if(charAt == 'G')
                countGRight++;
            else
                countTRight++;

            if(countARight > equalCount) {
                countARight--;
                minRight++;
                break;
            }
            if(countCRight > equalCount) {
                countARight--;
                minRight++;
                break;
            }
            if(countGRight > equalCount) {
                countARight--;
                minRight++;
                break;
            }
            if(countTRight > equalCount) {
                countARight--;
                minRight++;
                break;
            }
        }
//        if(minRight < maxLeft) // todo < or <=
//            return 0;
        int countA = countALeft + countARight;
        int countC = countCLeft + countCRight;
        int countG = countGLeft + countGRight;
        int countT = countTLeft + countTRight;
        // todo should use map<char, count> to be shorter

        int minLength = gene.length();

        int MIN_RIGHT = minRight;

        while (maxLeft >= 0) {
            minRight = MIN_RIGHT;
            while(minRight < gene.length()-1) {
                minRight++;
                if(minRight < maxLeft)
                    continue;
                char c = gene.charAt(minRight);
                if(c == 'A')
                    countA--;
                else if(c == 'C')
                    countC--;
                else if(c == 'G')
                    countG--;
                else if(c == 'T')
                    countT--;
                int lack = Math.max(0, equalCount - countA) + Math.max(0, equalCount - countC) + Math.max(0, equalCount - countG) + Math.max(0, equalCount - countT);
                if(lack <= minRight - maxLeft + 1)
                    minLength = Math.min(minLength, minRight - maxLeft + 1);
            }
            maxLeft--;
        }

        return minLength;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String gene = scanner.nextLine();

        int result = steadyGene(gene);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
        System.out.println(result);

        scanner.close();
    }
}
