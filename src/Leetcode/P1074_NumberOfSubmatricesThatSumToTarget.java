package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1074_NumberOfSubmatricesThatSumToTarget {

    // approach 1: prefix sum + hashmap
    // https://www.youtube.com/watch?v=4Aq5I_P4Ihw

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;
        int[][] prefixSum = new int[r + 1][c + 1];
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                prefixSum[i + 1][j + 1] = prefixSum[i][j + 1] + matrix[i][j];
            }
        }
        int res = 0;
        for (int rowStart = 1; rowStart <= r; rowStart++) {
            for (int row = rowStart; row <= r; row++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int sum = 0;
                for (int col = 1; col <= c; col++) {
                    sum += prefixSum[row][col] - prefixSum[rowStart - 1][col];
                    if (map.containsKey(sum - target)) {
                        res += map.get(sum - target);
                    }
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return res;
    }
}
