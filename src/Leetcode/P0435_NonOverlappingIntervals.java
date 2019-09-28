package Leetcode;

import java.util.Arrays;

public class P0435_NonOverlappingIntervals {

    // approach 1: greedy
    // The intution here comes into play when we choose which interval to delete. If 2
    // intervals are overlapping always delete the interval with bigger end value.
    // That way we ensure the upcoming intervals have lesser chance of overlapping
    // with previous intervals and thus can gurantee lesser deletions.

    // 1.Why {all intervals} - {max compatible intervals} = minimum deleted intervals?

    // Suppose interval A in the latter max compatible set B and A causes two other intervals
    // be deleted. If we delete A instead and insert those two deleted intervals to B can obtain
    // a larger set, then it contradicts B is the max compatible intervals.

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 2) return 0;

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int cnt = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                cnt++;
            }
            else {
                end = intervals[i][1];
            }
        }
        return cnt;
    }
}
