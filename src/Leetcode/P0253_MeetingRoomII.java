package Leetcode;
import java.util.*;

public class P0253_MeetingRoomII {

    // similar problem 1094(Car Pooling)

    // approach 1: greedy approach
    // Sort the intervals by their start time, so we always organize a meeting that starts earliest.
    // Then, when we organize the next meeting, we always check the earliest ending time of
    // currently ongoing meeting and see if the next meeting could reuse the earliest ending
    // meeting's conference room. Why is it optimized? Since if the next meeting could not share
    // with the earliest ending meeting's conference room, it cannot share with the rest, and
    // in that case, we have to add this meeting to our queue which means requiring another conference room.

    // time: O(nlogn)
    // space: O(n)

    public int minMeetingRooms(int[][] intervals) {

        // sort the intervals by their start time in ascending order
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // intervals in priority queue is sorted by their ending time in ascending order
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (!pq.isEmpty() && interval[0] >= pq.peek()[1]) {
                // previous meeting ends before the incoming one starts,
                // so we could reuse the previous conference room for this meeting.
                int[] prev = pq.poll();
                pq.offer(new int[]{prev[0], Math.max(prev[1], interval[1])});
                continue;
            }
            pq.offer(interval);
        }
        return pq.size();
    }

    // approach 2: treemap
    // sort by start time
    // The idea is that whenever we start a meeting, we increment room cnt, and
    // whenever we end a meeting, we decrement room cnt. Then, iterating through
    // entries in map. Since entries are sorted in terms of start time, we record
    // the maximum number of rooms(conflict) appeared from the beginning to the end

    // for example:
    // [[0, 30],[5, 10],[15, 20]]

    //time: 0  5  10  15  20  30
    //room: 1  1  -1  1   -1  -1
    //max:  1  2  1   2   1   0

    public int minMeetingRooms_2(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] t : intervals) {
            map.put(t[0], map.getOrDefault(t[0], 0) + 1);
            map.put(t[1], map.getOrDefault(t[1], 0) - 1);
        }
        int max = 0;
        int room = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            room += entry.getValue();
            max = Math.max(max, room);
        }
        return max;
    }
}
