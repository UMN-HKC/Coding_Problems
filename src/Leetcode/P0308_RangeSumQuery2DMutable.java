package Leetcode;

public class P0308_RangeSumQuery2DMutable {

    // approach 1: Binary Index Tree(Fenwick tree)

    // the basic idea is to use BIT to partially store the sum,
    // query and update both cost O(logn)

    int[][] tree;
    int[][] nums;
    public NumMatrix(int[][] matrix) {
        if (matrix != null && matrix.length != 0 && matrix[0].length != 0) {
            int m = matrix.length, n = matrix[0].length;
            tree = new int[m + 1][n + 1];
            nums = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    update(i, j, matrix[i][j]);
                }
            }
        }
    }

    public void update(int row, int col, int val) {
        int m = tree.length, n = tree[0].length;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i < m; i += i & (-i)) {
            for (int j = col + 1; j < n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRegion(row2, col2) + sumRegion(row1-1, col1-1) - sumRegion(row2, col1-1) - sumRegion(row1-1, col2);
    }
    public int sumRegion(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}
