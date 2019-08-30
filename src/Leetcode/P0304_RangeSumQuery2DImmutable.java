package Leetcode;

public class P0304_RangeSumQuery2DImmutable {

    // approach 1: prefix sum
    // precompute sum

    int[][] sum;
    public NumMatrix(int[][] matrix) {
        if (matrix != null && matrix.length != 0 && matrix[0].length != 0) {
            int m = matrix.length, n = matrix[0].length;
            sum = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sum[i+1][j+1] = sum[i+1][j] + sum[i][j+1] - sum[i][j] + matrix[i][j];
                }
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] + sum[row1][col1] - sum[row1][col2 + 1] - sum[row2 + 1][col1];
    }
}
