package Leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

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

    // approach 2: treemap
    // sort by start time
    // The idea is that whenever we start a meeting, we increment room cnt, and
    // whenever we end a meeting, we decrement room cnt. So, whenever during our whole
    // time interval, we have room cnt > 1, there's a conflict

    public boolean canAttendMeetings_2(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] t : intervals) {
            map.put(t[0], map.getOrDefault(t[0], 0) + 1);
            map.put(t[1], map.getOrDefault(t[1], 0) - 1);
        }
        int room = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            room += entry.getValue();
            if (room > 1) return false;
        }
        return true;
    }


}
