package Leetcode;
import java.util.*;

public class P0056_MergeIntervals {

    // sort the intervals by their start time first and then use priority queue to
    // sort added intervals in terms of their finish time but in decending order
    // so that we are able to check if next incoming interval will conflict with
    // the last interval whose ending time is lattest. Greedy approach and it works

    // time: O(nlogn)
    // space: O(n)

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (!pq.isEmpty() && interval[0] <= pq.peek()[1]) {
                int[] prev = pq.poll();
                pq.offer(new int[]{prev[0], Math.max(prev[1], interval[1])});
                continue;
            }
            pq.offer(interval);
        }
        return pq.toArray(new int[pq.size()][2]);
    }
}
