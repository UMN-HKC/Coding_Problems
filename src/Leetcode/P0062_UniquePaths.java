package Leetcode;

public class P0062_UniquePaths {

    // Approach 1: DP with O(m*n) space
    // The basic idea is to use DP since we have overlapping property in this problem

    public int uniquePaths(int m, int n) {
        int[][] grid = new int[n+1][m+1];
        grid[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;
                grid[i][j] = grid[i-1][j] + grid[i][j - 1];
            }
        }
        return grid[n][m];
    }

    // O(m) space with two arrays
    // since we only utilize information at [i-1][j] and [i][j-1] position, we can only keep two arrays
    public int uniquePaths_optimize_one(int m, int n) {
        int[] prev = new int[m];
        int[] cur = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    cur[j] = 1;
                }
                else {
                    cur[j] = cur[j-1] + prev[j];
                }
            }
            prev = cur;
        }
        return cur[m-1];
    }

    // O(m) space with only one array
    // optimize to one array

    public int uniquePaths_optimize_two(int m, int n) {
        int[] steps = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    steps[j] = 1;
                }
                else {
                    steps[j] = steps[j-1] + steps[j];
                }
            }
        }
        return steps[m-1];
    }
}
