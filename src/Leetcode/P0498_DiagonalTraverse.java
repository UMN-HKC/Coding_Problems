package Leetcode;

public class P0498_DiagonalTraverse {

    // approach 1: boolean flag indicating directions
    // when running into 'walls', we need to navigate directions and update boolean flag

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int r = matrix.length, c = matrix[0].length, i = 0, j = 0, cnt = 0;
        int[] res = new int[r * c];
        boolean upward = true;
        while (cnt < res.length) {
            res[cnt++] = matrix[i][j];
            // going towards top right
            if (upward) {
                if (i - 1 >= 0 && j + 1 < c) {
                    i--;
                    j++;
                }
                else if (j + 1 < c) {
                    j++;
                    upward = !upward;
                }
                else {
                    i++;
                    upward = !upward;
                }
            }
            // going towards bottom left
            else {
                if (i + 1 < r && j - 1 >= 0) {
                    i++;
                    j--;
                }
                else if (i + 1 < r) {
                    i++;
                    upward = !upward;
                }
                else {
                    j++;
                    upward = !upward;
                }
            }
        }
        return res;
    }
}
