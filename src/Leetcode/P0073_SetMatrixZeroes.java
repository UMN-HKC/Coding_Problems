package Leetcode;

public class P0073_SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {

        // approach 1:

        // The basic idea is to use the first row and first column of the
        // matrix to store our meta information. The first time, we iterate
        // in a top-down manner and use col0 to store whether the first column
        // should be set to 0 later since we use the first column to store meta
        // info, we cannot change its values.
        // The second iteration we will go from bottom-up manner, because we want
        // to use the first row's meta info and we want to change the first row's
        // value in the end when we have updated the bottom rows.

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            // note that we only iterate to column 1, the value at column 0
            // will be updated after the end of the inner loop, because we
            // use col0 variable to update value at column 0, instead of
            // using matrix[i][0] and matrix[0][j] as we used to determine
            // other columns
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) matrix[i][0] = 0;
        }
        return;
    }
}
