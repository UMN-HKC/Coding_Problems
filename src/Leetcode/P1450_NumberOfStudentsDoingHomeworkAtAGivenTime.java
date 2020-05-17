package Leetcode;

import java.util.PriorityQueue;

public class P1450_NumberOfStudentsDoingHomeworkAtAGivenTime {

    // approach 1: sort
    // time: O(nlogn)

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        for (int i = 0; i < startTime.length; i++) {
            pq.offer(new int[]{startTime[i], 1});
            pq.offer(new int[]{endTime[i], -1});
        }
        int cur = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            if (pair[0] > queryTime || (pair[0] >= queryTime && pair[1] == -1)) {
                break;
            }
            cur += pair[1];
        }
        return cur;
    }


    // approach 2: one pass
    // time: O(n)

    public int busyStudent_2(int[] startTime, int[] endTime, int queryTime) {
        int cnt = 0;
        for (int i = 0; i < startTime.length; i++) {
            cnt += startTime[i] <= queryTime && endTime[i] >= queryTime ? 1 : 0;
        }
        return cnt;
    }
}
