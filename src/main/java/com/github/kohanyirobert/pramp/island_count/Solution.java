package com.github.kohanyirobert.pramp.island_count;

import java.util.ArrayList;
import java.util.List;

// https://www.pramp.com/challenge/yZm60L6d5juM7K38KYZ6
public class Solution {

    public static void main(String[] args) {
        System.out.println(getNumberOfIslands(new int[][]{
            {0, 1, 1},
            {1, 0, 0},
            {0, 1, 1}
        })); // 3
        System.out.println(getNumberOfIslands(new int[][]{
            {0, 1, 0, 1, 0},
            {0, 0, 1, 1, 1},
            {1, 0, 0, 1, 0},
            {0, 1, 1, 0, 0},
            {1, 0, 1, 0, 1}
        })); // 6
    }

    private static int getNumberOfIslands(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int c = 0;
        int[][] flags = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                c += visitIslands(matrix, flags, i, j);
            }
        }
        return c;
    }

    private static int visitIslands(int[][] matrix, int[][] flags, int i, int j) {
        if (isVisited(flags, i, j)) {
            return 0;
        }
        flags[i][j] = 1;
        if (matrix[i][j] == 0) {
            return 0;
        }
        int[][] neighbors = findNeighbors(matrix, flags, i, j);
        for (int[] neighbor : neighbors) {
            int x = neighbor[0];
            int y = neighbor[1];
            visitIslands(matrix, flags, x, y);
        }
        return 1;
    }

    private static int[][] findNeighbors(int[][] matrix, int[][] flags, int i, int j) {
        List<int[]> neighbors = new ArrayList<>(4);
        tryAddNeighbors(neighbors, matrix, flags, i - 1, j);
        tryAddNeighbors(neighbors, matrix, flags, i + 1, j);
        tryAddNeighbors(neighbors, matrix, flags, i, j - 1);
        tryAddNeighbors(neighbors, matrix, flags, i, j + 1);
        return neighbors.toArray(new int[0][0]);
    }

    private static boolean isValid(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    private static boolean isVisited(int[][] flags, int i, int j) {
        return flags[i][j] != 0;
    }

    private static void tryAddNeighbors(List<int[]> neighbors, int[][] matrix, int[][] flags, int i, int j) {
        if (isValid(matrix, i, j) && !isVisited(flags, i, j)) {
            neighbors.add(new int[]{i, j});
        }
    }
}
