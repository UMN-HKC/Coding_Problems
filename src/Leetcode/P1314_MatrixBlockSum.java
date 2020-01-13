package Leetcode;

public class P1314_MatrixBlockSum {

    // approach 1: brute force, range sum
    // The basic idea is similar to range sum. Be careful about the coordination boundary

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int r = mat.length, c = mat[0].length;
        int[][] cumulatingSum = buildCumulatingMatrix(mat, r, c);
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int upperLeftCol = j - K < 0 ? 0 : j - K;
                int upperLeftRow = i - K < 0 ? 0 : i - K;
                int bottomRightCol = j + K >= c ? c - 1 : j + K;
                int bottomRightRow = i + K >= r ? r - 1 : i + K;
                res[i][j] = cumulatingSum[bottomRightRow + 1][bottomRightCol + 1] - cumulatingSum[bottomRightRow + 1][upperLeftCol]
                        - cumulatingSum[upperLeftRow][bottomRightCol + 1] + cumulatingSum[upperLeftRow][upperLeftCol];
            }
        }
        return res;
    }
    private int[][] buildCumulatingMatrix(int[][] mat, int r, int c) {
        int[][] cs = new int[r + 1][c + 1];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cs[i + 1][j + 1] = mat[i][j] + cs[i][j + 1] + cs[i + 1][j] - cs[i][j];
            }
        }
        return cs;
    }
}
