package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P0057_InsertInterval {

    // approach 1:
    // The basic idea is to
    // - add all intervals ending before the start of the new interval
    // - merge overlapping intervals
    // - add all intervals starting after the end of the new interval

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int m = intervals.length;
        int i = 0;
        // add all intervals which finishe before the new interval
        while (i < m && intervals[i][1] < newInterval[0]) list.add(intervals[i++]);
        if (i < m) {
            // add the new interval and merge any overlapping intervals
            list.add(newInterval);
            int[] last = list.get(list.size() - 1);
            while (i < m && ((intervals[i][0] <= last[0] && intervals[i][1] >= last[0]) || intervals[i][0] <= last[1])) {
                last[0] = Math.min(intervals[i][0], last[0]);
                last[1] = Math.max(intervals[i][1], last[1]);
                i++;
            }
            // add intervals that start after the new interval
            while (i < m) list.add(intervals[i++]);
        }
        else {
            list.add(newInterval);
        }
        int[][] res = new int[list.size()][2];
        for (int j = 0; j < res.length; j++) res[j] = list.get(j);
        return res;
    }

    // approach 2: binary search (not recommended, still O(n) in the worst case and it is hard to get it right)

    public int[][] insert_2(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) {
            int[][] res = new int[1][2];
            res[0] = newInterval;
            return res;
        }
        int m = intervals.length;
        int l = insertStartPosition(intervals, newInterval);
        int r = insertEndPosition(intervals, newInterval);
        // update start and end time
        if (l >= 0 && l < m) newInterval[0] = Math.min(intervals[l][0], newInterval[0]);
        if (r >= 0 && r < m) newInterval[1] = Math.max(intervals[r][1], newInterval[1]);
        // edge case
        if (l < 0) l++;
        if (r == m) r--;
        int len = l + m - r;
        int[][] res = new int[len][2];
        int cnt = 0;
        // add any interval before the inserted interval's start time
        for (int i = 0; i < l; i++) {
            res[cnt++] = intervals[i];
        }
        res[cnt++] = newInterval;
        // add any interval after the inserted interval's end time
        for (int i = r + 1; i < m; i++) {
            res[cnt++] = intervals[i];
        }
        return res;
    }
    public int insertStartPosition(int[][] intervals, int[] newInterval) {
        int l = 0, r = intervals.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (intervals[m][1] == newInterval[0]) {
                return m;
            }
            else if (intervals[m][1] > newInterval[0]) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }
    public int insertEndPosition(int[][] intervals, int[] newInterval) {
        int l = 0, r = intervals.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (intervals[m][0] == newInterval[1]) {
                return m;
            }
            else if (intervals[m][0] < newInterval[1]) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return r;
    }
}
