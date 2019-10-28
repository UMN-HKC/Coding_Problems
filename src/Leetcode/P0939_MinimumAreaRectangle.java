package Leetcode;

import java.util.*;

public class P0939_MinimumAreaRectangle {

    // approach 1: brute force O(n^4)

    public int minAreaRect(int[][] points) {
        int area = Integer.MAX_VALUE;
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < points.length - 3; i++) {
            if (points[i][0] == points[i + 1][0]) {
                for (int j = i + 1; j < points.length - 2 && points[j][0] == points[i][0]; j++) {
                    for (int k = j + 1; k < points.length - 1; k++) {
                        if (points[k][0] == points[j][0]) continue;
                        if (points[k][0] == points[k + 1][0]) {
                            for (int l = k + 1; l < points.length && points[k][0] == points[l][0]; l++) {
                                if (points[j][1] == points[l][1] && points[k][1] == points[i][1]) {
                                    area = Math.min(area, (points[l][1] - points[k][1]) * (points[l][0] - points[j][0]));
                                }
                            }
                        }
                    }

                }
            }
        }
        return area == Integer.MAX_VALUE ? 0 : area;
    }

    // approach 2: map

    // The key idea is that we will use a map to store all y values on each x-axis, since
    // points are all unique, we don't need to worry about duplicate values.
    // Then we iterate points pair by pair and look for diagonal points (two points that
    // do not lie on the same x-axie or y-axis). Once found a pair, we check our map and
    // see if there are two other points that could make a rectangle with found two points
    // If so, update our reuslt.

    // time: O(n^2)

    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            if (!map.containsKey(point[0])) {
                map.put(point[0], new HashSet<>());
            }
            map.get(point[0]).add(point[1]);
        }
        int min = Integer.MAX_VALUE;
        for (int[] p1 : points) {
            for (int[] p2 : points) {
                if (p1[0] == p2[0] || p1[1] == p2[1]) continue;
                if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p2[1] - p1[1]));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
