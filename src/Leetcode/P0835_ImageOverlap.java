package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P0835_ImageOverlap {

    // approach 1: hashmap
    // note that for the difference key, we need take both row diff and col diff and construct the key
    // we can not just take the position sequence in the matrix, this will result incorrect result.

    // time: O(n^4)
    // space: O(n^2)

    public int largestOverlap(int[][] A, int[][] B) {
        List<int[]> oneCordsOfA = new ArrayList<>();
        List<int[]> oneCordsOfB = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int n = A.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    oneCordsOfA.add(new int[]{i, j});
                }
                if (B[i][j] == 1) {
                    oneCordsOfB.add(new int[]{i, j});
                }
            }
        }
        for (int[] cordA : oneCordsOfA) {
            for (int[] cordB : oneCordsOfB) {
                String key = (cordA[0] - cordB[0]) + "," + (cordA[1] - cordB[1]);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        for (int val : map.values()) {
            res = Math.max(res, val);
        }
        return res;
    }
}
