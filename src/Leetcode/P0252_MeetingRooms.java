package Leetcode;

import java.util.Arrays;

public class P0252_MeetingRooms {

    // sort by start time, then if any interval conflicts, it conflicts
    // initial approach

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i+1][0]) return false;
        }
        return true;
    }

    // approach 2:

    public boolean canAttendMeetings(Interval[] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Interval itl : intervals) {
            map.put(itl.start, map.getOrDefault(itl.start, 0) + 1);
            map.put(itl.end, map.getOrDefault(itl.end, 0) - 1);
        }
        int room = 0;
        for (int v : map.values()) {
            room += v;
            if (room > 1) return false;
        }
        return true;
    }


}
