package com.quannh.paradigm.dynamic;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String[] X = {"AGGTAB", "A", "BCD", "EFGH", "IKLMNOPAFEDDSI", "ABCBABCBABCBABBA"};
        String[] Y = {"GXTXAYB", "B", "C", "D", "ABCBBCBCBABCBACB"};
        for(String x : X)
            for(String y : Y)
                System.out.printf("LONGEST COMMON SUB SEQUENCE OF \"%s\" and \"%s\" is: \"%s\"\n",
                        x, y, longestCommonSubSequence(x, y));
    }
    private static String longestCommonSubSequence(String X, String Y) {
        int MAXLENGTH = Math.max(X.length(), Y.length()) + 1;
        int[][] L = new int[X.length() + 1][Y.length() + 1];
        int[][] prev = new int[X.length() + 1][Y.length() + 1];
        for(int i = 0; i <= X.length(); i++)
            for(int j = 0; j <= Y.length(); j++)
                prev[i][j] = -1;
        for(int i = 0; i <= X.length(); i++)
            for(int j = 0; j <= Y.length(); j++) {
                if(i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if(X.charAt(i-1) == Y.charAt(j-1)) {
                    L[i][j] = L[i-1][j-1] + 1;
                    prev[i][j] = (i-1) * MAXLENGTH + (j-1);
                } else {
                    L[i][j] = Math.max(L[i][j-1], L[i-1][j]);
                    if(L[i][j-1] >= L[i-1][j]) {
                        prev[i][j] = i * MAXLENGTH + (j -1);
                    } else {
                        prev[i][j] = (i-1) * MAXLENGTH + j;
                    }
                }
            }
        StringBuilder sb = new StringBuilder();
        int xIndex = X.length(), yIndex = Y.length();
        while (prev[xIndex][yIndex] != -1) {
            if(X.charAt(xIndex-1) == Y.charAt(yIndex-1)) {
                sb.append(X.charAt(xIndex-1));
            }
            int previousYIndex = prev[xIndex][yIndex] % MAXLENGTH;
            xIndex = (prev[xIndex][yIndex] - previousYIndex) / MAXLENGTH;
            yIndex = previousYIndex;
        }

        return sb.reverse().toString();
    }
}
