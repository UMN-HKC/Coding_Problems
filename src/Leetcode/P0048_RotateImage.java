package Leetcode;

public class P0048_RotateImage {

    // approach 1:
    // The key point is to realize that by swapping values between first half rows
    // and second half rows and then swapping values between values from two sides
    // of the diagonal line, we can achieve the rotation.

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int size = matrix.length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size; j++) {
                swap(matrix, i, j, size - i - 1, j);
            }
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        return;
    }
    public void swap(int[][] matrix, int x, int y, int a, int b) {
        int temp = matrix[x][y];
        matrix[x][y] = matrix[a][b];
        matrix[a][b] = temp;
    }
}
