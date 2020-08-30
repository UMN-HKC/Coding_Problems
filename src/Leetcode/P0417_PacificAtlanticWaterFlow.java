package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0417_PacificAtlanticWaterFlow {

    // approach 1: dfs

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visitedPacific = new boolean[m][n];
        boolean[][] visitedAtlantic = new boolean[m][n];

        for (int i = 0; i < n; i++) {
            traverse(matrix, visitedPacific, 0, i, matrix[0][i]);
            traverse(matrix, visitedAtlantic, m - 1, i, matrix[m - 1][i]);
        }
        for (int i = 0; i < m; i++) {
            traverse(matrix, visitedPacific, i, 0, matrix[i][0]);
            traverse(matrix, visitedAtlantic, i, n - 1, matrix[i][n - 1]);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visitedPacific[i][j] && visitedAtlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    private void traverse(int[][] matrix, boolean[][] visited, int i, int j, int preVal) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] < preVal || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        traverse(matrix, visited, i + 1, j, matrix[i][j]);
        traverse(matrix, visited, i - 1, j, matrix[i][j]);
        traverse(matrix, visited, i, j + 1, matrix[i][j]);
        traverse(matrix, visited, i, j - 1, matrix[i][j]);

    }
}
