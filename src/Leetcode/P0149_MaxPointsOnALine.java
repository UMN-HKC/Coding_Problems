package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0149_MaxPointsOnALine {

    // approach 1

    // compare points pair by pair, and use two points' slope to denote
    // points are in the same line. Note that, we need to deal with edge
    // cases of duplicate points (each time we encounter a duplicate point,
    // we increment dup and continue to the next point without calculating
    // current two points' slope), and points lie in the vertical line and
    // horizontal line(this case is handled by gcd and storing slope as string)

    // use gcd to help get simplified numerator and denominator and
    // store slope as string to improve upon the other approach in which
    // float's precision is not precise enough to differentiate slopes

    // time: O(n^2)
    // space: O(n)

    public int maxPoints_2(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) return 0;
        int m = points.length, n = points[0].length;
        int max = 1;

        for (int i = 0; i < m - 1; i++) {
            int dup = 1;
            int cnt = 0;
            int[] pointA = points[i];
            Map<String, Integer> slopeMap = new HashMap<>();
            for (int j = i + 1; j < m; j++) {
                int[] pointB = points[j];
                if (pointB[0] == pointA[0] && pointB[1] == pointA[1]) {
                    dup++;
                    continue;
                }
                String slope = getSlope(pointA, pointB);
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                cnt = Math.max(cnt, slopeMap.get(slope));
            }
            max = Math.max(dup + cnt, max);
        }
        return max;
    }
    public String getSlope(int[] pointA, int[] pointB) {
        int dy = pointA[0] - pointB[0];
        int dx = pointA[1] - pointB[1];
        int gcd = gcd(dx, dy);
        if (gcd != 0) {
            dy /= gcd;
            dx /= gcd;
        }
        return dy + ":" + dx;
    }
    public int gcd(int a,int b){
        if (b==0) return a;
        else return gcd(b,a%b);
    }
}
