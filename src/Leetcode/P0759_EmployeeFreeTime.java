package Leetcode;

import java.util.*;

public class P0759_EmployeeFreeTime {

    // approach 1: pq
    // The basic idea is that we don't actually care about whose interval it is, we put all
    // intervals into priority queue so that they are all sorted in order. The answer is all
    // intervals that are between two non-overlapping intervals. To achieve that, we will
    // keep a variable called last which is the latest ending time among all previous occured
    // intervals, so that each time we pop a new interval from priority queue, we can compare
    // its start time with the variable to see if there's a free time.

    // time: O(nlogn)

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<Interval> pq = new PriorityQueue<>(
                (a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);
        for (int i = 0; i < schedule.size(); i++) {
            List<Interval> list = schedule.get(i);
            for (int j = 0; j < list.size(); j++) {
                pq.offer(list.get(j));
            }
        }
        List<Interval> res = new ArrayList<>();
        if (pq.size() == 0) return res;

        int last = pq.poll().end;
        while (!pq.isEmpty()) {
            Interval cur = pq.poll();
            if (last < cur.start) {
                res.add(new Interval(last, cur.start));
            }
            last = Math.max(cur.end, last);
        }
        return res;
    }

    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start,int _end) {
            start = _start;
            end = _end;
        }
    };
}
