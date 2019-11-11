package Leetcode;

public class P0311_SparseMatrixMultiplication {

    // approach 1: calculate each result cell and then proceed to next

    public int[][] multiply(int[][] A, int[][] B) {
        int r = A.length, c = B[0].length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < B.length; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }

    // approach 2:
    // skip brute force which calculates each cell's final result,
    // we calculate result on the go which means we add the result of  one
    // multiplication to a cell each time. In this way, if we encounter 0 in the
    // matrix A, we can skip a whole for loop iteration

    public int[][] multiply_2(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, p = B[0].length;
        int[][] res = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) continue;
                for (int k = 0; k < p; k++) {
                    if (B[j][k] != 0) {
                        res[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }
        return res;
    }
}
