package Leetcode;

import java.util.LinkedList;

public class P0057_InsertInterval {

    // approach 1:
    // keep a boolean flag to indicate whether the new interval has been added
    // note that after the traversal, if the flag is still false, we need to add
    // the new interval into result

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval == null) return intervals;
        LinkedList<int[]> list = new LinkedList<>();
        boolean added = false;
        for (int[] interval : intervals) {
            // if already added or new interval comes after
            // current one, we will add current one
            if (added || (!added && interval[1] < newInterval[0])) {
                list.add(interval);
            }
            else {
                // if the new interval comes before the current one
                // we add it to the list and set add flag to true
                // so later we do not to worry about it
                if (interval[0] > newInterval[1]) {
                    list.add(newInterval);
                    list.add(interval);
                    added = true;
                }
                // otherwise, there's overlap between current one
                // and the new interval. We just update the new
                // interval's start and end time
                else {
                    int newStart = Math.min(interval[0], newInterval[0]);
                    int newEnd = Math.max(interval[1], newInterval[1]);
                    newInterval[0] = newStart;
                    newInterval[1] = newEnd;
                }
            }
        }
        // if the new interval has not been added, we add it now
        if (!added) {
            list.add(newInterval);
        }
        // convert to array
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
