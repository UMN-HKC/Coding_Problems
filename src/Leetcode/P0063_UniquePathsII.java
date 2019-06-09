package Leetcode;

public class P0063_UniquePathsII {

    // Approach 1: DP
    // The idea is similar to 'UniquePath' without block
    // in here, we only add one operation, that is, when grid cell is blocked,
    // we set cur[j] = 0

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        int[] cur = new int[column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cur[j] = 0;
                }
                else if (i == 0 || j == 0) {
                    if (i == 0 && j == 0) {
                        cur[j] = 1;
                    }
                    else if (i == 0) {
                        cur[j] = cur[j - 1];
                    }
                }
                else {
                    cur[j] = cur[j - 1] + cur[j];
                }
            }
        }
        return cur[column-1];
    }

    // more concise
    public int uniquePathsWithObstacles_concise(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;
        int[] cur = new int[column];
        cur[0] = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (obstacleGrid[i][j] == 1) {
                    cur[j] = 0;
                }
                // current cell is available
                // When i == 0, cur[j] = cur[j]. So, we shorten our code to just deal with j > 0
                else if (j > 0) {
                    cur[j] += cur[j - 1];
                }
            }
        }
        return cur[column-1];
    }
}
