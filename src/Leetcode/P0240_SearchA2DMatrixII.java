package Leetcode;

public class P0240_SearchA2DMatrixII {

    // approach 1:

    // We start search the matrix from top right corner, initialize the current
    // position to top right corner, if the target is greater than the value in
    // current position, then the target can not be in entire row of current position
    // because the row is sorted, if the target is less than the value in current
    // position, then the target can not in the entire column because the column
    // is sorted too. We can rule out one row or one column each time, so the time
    // complexity is O(m+n).

    // This problem can not be solved in O(logn + logm) or O(log(m*n)) time

    // time: O(m + n)

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) {
                col--;
            }
            else {
                row++;
            }
        }
        return false;
    }
}
