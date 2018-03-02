package com.github.kohanyirobert.pramp.deletion_distance;

// https://www.pramp.com/challenge/61ojWAjLJbhob2nP2q1O
public class Solution {

    public static void main(String[] args) {
        // recursive
        System.out.println(deletionDistance("", "")); // 0
        System.out.println(deletionDistance("some", "some")); // 0
        System.out.println(deletionDistance("cat", "dog")); // 6
        System.out.println(deletionDistance("heat", "hit")); // 3
        System.out.println(deletionDistance("some", "tom")); // 3

        // iterative
        System.out.println(deletionDistance2("", "")); // 0
        System.out.println(deletionDistance2("some", "some")); // 0
        System.out.println(deletionDistance2("cat", "dog")); // 6
        System.out.println(deletionDistance2("heat", "hit")); // 3
        System.out.println(deletionDistance2("some", "tom")); // 3
    }

    private static int[][] createMemory(int m, int n, int v) {
        int[][] mem = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mem[i][j] = v;
            }
        }
        return mem;
    }

    private static int deletionDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        return deletionDistance(createMemory(m, n, -1), s, m, t, n);
    }

    private static int deletionDistance(int[][] mem, String s, int m, String t, int n) {
        if (m == 0) return n;
        if (n == 0) return m;

        int i = m - 1;
        int j = n - 1;

        int r = mem[i][j];
        if (r != -1) {
            return r;
        }

        if (s.charAt(i) == t.charAt(j))
            return deletionDistance(mem, s, i, t, j);

        int a = deletionDistance(mem, s, i, t, n);
        int b = deletionDistance(mem, s, m, t, j);

        int c;
        if (a < b) {
            c = a;
        } else {
            c = b;
        }
        c++;

        mem[i][j] = c;

        return c;
    }

    private static int deletionDistance2(String s, String t) {
        int m = s.length();
        int n = t.length();
        return deletionDistance2(createMemory(m + 1, n + 1, 0), s, m, t, n);
    }

    private static int deletionDistance2(int[][] mem, String s, int m, String t, int n) {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    mem[i][j] = j;
                } else if (j == 0) {
                    mem[i][j] = i;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    mem[i][j] = mem[i - 1][j - 1];
                } else {
                    int a = mem[i][j - 1];
                    int b = mem[i - 1][j];
                    int c;
                    if (a < b) {
                        c = a;
                    } else {
                        c = b;
                    }
                    mem[i][j] = 1 + c;
                }
            }
        }
        return mem[m][n];
    }
}
