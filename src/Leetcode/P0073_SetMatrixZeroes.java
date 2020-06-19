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

    // approach 2: similar but more intuitive to me
    // The idea is to use the first row to store the metadata about if
    // a specific column should be set to 0s. But before we do that, we traverse
    // the first row first to preserve the information about whether the first
    // row should be set to zero so that later when we override the data in the
    // first row, we have already preserved that information.
    // While we traverse each row, we record if we need to set this row to 0s
    // by keeping a flag. After traversing each row, we update this row(setting 0s) according to our flag.
    // Finally, we use the first row's meta data we preserved earlier to set the first row.

    public void setZeroes_2(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        // since we will be modifying first row, preserve
        // the first row infiormation first
        boolean hasZeroFirstRow = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                hasZeroFirstRow = true;
            }
        }
        // set first row at this column to 0
        // if this column at current row is 0
        for (int i = 1; i < m; i++) {
            boolean hasZeroThisRow = false;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    hasZeroThisRow = true;
                }
            }
            // update current row
            if (hasZeroThisRow) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        // use information stored in the first row
        // to update each column
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        // update first row itself
        if (hasZeroFirstRow) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
    }


}
