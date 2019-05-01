package com.quannh.paradigm.dynamic;

/**
 * Edit Distance | DP-5
 * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
 *
 * Insert
 * Remove
 * Replace
 * All of the above operations are of equal cost.
 *
 * Examples:
 *
 * Input:   str1 = "geek", str2 = "gesek"
 * Output:  1
 * We can convert str1 into str2 by inserting a 's'.
 *
 * Input:   str1 = "cat", str2 = "cut"
 * Output:  1
 * We can convert str1 into str2 by replacing 'a' with 'u'.
 *
 * Input:   str1 = "sunday", str2 = "saturday"
 * Output:  3
 * Last three and first characters are same.  We basically
 * need to convert "un" to "atur".  This can be done using
 * below three operations.
 * Replace 'n' with 'r', insert t, insert a
 *
 * What are the subproblems in this case?
 * The idea is process all characters one by one staring from either from left or right sides of both strings.
 * Let us traverse from right corner, there are two possibilities for every pair of character being traversed.
 *
 * m: Length of str1 (first string)
 * n: Length of str2 (second string)
 * If last characters of two strings are same, nothing much to do. Ignore last characters and get count for remaining strings. So we recur for lengths m-1 and n-1.
 * Else (If last characters are not same), we consider all operations on ‘str1’, consider all three operations on last character of first string, recursively compute minimum cost for all three operations and take minimum of three values.
 * Insert: Recur for m and n-1
 * Remove: Recur for m-1 and n
 * Replace: Recur for m-1 and n-1
 */
public class TwoStringEditDistance {
    public static void main(String[] args) {
        String[] S1 = {"A", "BC", "DEF", "GHIK", "IROEOIEIOF"};
        String[] S2 = {"B", "BC", "BCDE", "GHII", "HIK", "IOIEJIFJSERFE"};
        for(String s1 : S1)
            for(String s2 : S2)
                System.out.printf("Distance of \"%s\" and \"%s\" is %d\n", s1, s2, twoStringDistance(s1, s2));
    }
    private static int twoStringDistance(String s1, String s2) {
        // create a table of distance
        int[][] distance = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 0; i <= s1.length(); i++)
            for(int j = 0; j <= s2.length(); j++) {
                // one of 2 strings empty - return the other length
                if(i == 0)
                    distance[i][j] = j; // j insertion or deletion
                else if(j == 0)
                    distance[i][j] = i; // i insertion or deletion
                // if the last characters of 2 strings are same, just cut it, do nothing
                else if(s1.charAt(i-1) == s2.charAt(j-1))
                    distance[i][j] = distance[i-1][j-1];
                /* else, distance = 1 +
                            MIN(
                                distance(s1-1, s2), // deletion
                                distance(s1, s2-1), // insertion
                                distance(s1-1, s2-1) // replacement
                 */
                else
                    distance[i][j] = 1 + Math.min(Math.min(distance[i-1][j], distance[i][j-1]), distance[i-1][j-1]);
            }
        return distance[s1.length()][s2.length()];
    }
}
