package Leetcode;

public class P0059_SpiralMatrixII {

    // approach 1:
    // almost same to spiral matrix, keep four variables and brute force

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int cur = 1;
        int topLeft = 0, topRight = n - 1, bottomRight = n - 1, bottomLeft = 0;
        while (topLeft <= bottomRight && bottomLeft <= topRight) {
            for (int i = bottomLeft; i <= topRight; i++) {
                matrix[topLeft][i] = cur++;
            }
            topLeft++;
            for (int i = topLeft; i <= bottomRight; i++) {
                matrix[i][topRight] = cur++;
            }
            topRight--;
            for (int i = topRight; i >= bottomLeft; i--) {
                matrix[bottomRight][i] = cur++;
            }
            bottomRight--;
            for (int i = bottomRight; i >= topLeft; i--) {
                matrix[i][bottomLeft] = cur++;
            }
            bottomLeft++;
        }
        return matrix;
    }
}
