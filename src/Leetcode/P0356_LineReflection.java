package Leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class P0356_LineReflection {

    // approach 1: sort + two pointers

    // kind of complicated and hard to get it correct. Better use second approach

    public boolean isReflected(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) return true;
        int m = points.length;
        // get the reflection line
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            min = Math.min(min, points[i][0]);
            max = Math.max(max, points[i][0]);
        }
        double ref = (double)(min + max) / 2;

        // sort the points so points on same horizontal line lie consecutively
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1]) return a[0] - b[0];
                return a[1] - b[1];
            }
        });
        // check each horizontal line
        int l = 0, r = 0;
        while (r < m) {
            // find two ends of points lying on the same horizontal line
            while (r + 1 < m && points[r + 1][1] == points[r][1]) r++;
            // if just a single point and does not lie on the reflection line, return false
            if (l == r && (double)points[l][0] != ref) {
                return false;
            }
            // check this horizontal line
            int i = l, j = r;
            while (i <= j) {
                // skip duplicate points
                while (i < j && points[i][0] == points[i+1][0] && points[i][1] == points[i+1][1]) i++;
                while (i < j && points[j][0] == points[j-1][0] && points[j][1] == points[j-1][1]) j--;
                if (i < j && ((double)(points[i][0] + points[j][0]) / 2 != ref)) {
                    return false;
                }
                else if (i == j && (double)points[i][0] != ref) {
                    return false;
                }
                i++;
                j--;
            }
            r++;
            l = r;
        }
        return true;
    }

    // approach 2: hashset
    // use set to store the points position as string. Get the min and max of
    // the x axis, so we know when we check each point, the set must have its reflective
    // point as well which is (min + max - x).

    public boolean isReflected_2(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<String> set = new HashSet<>();
        for(int[] p:points){
            max = Math.max(max,p[0]);
            min = Math.min(min,p[0]);
            String str = p[0] + "a" + p[1];
            set.add(str);
        }
        int sum = max+min;
        for(int[] p:points){
            //int[] arr = {sum-p[0],p[1]};
            String str = (sum-p[0]) + "a" + p[1];
            if( !set.contains(str))
                return false;

        }
        return true;
    }
}
