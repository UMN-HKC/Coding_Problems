package Leetcode;
import java.util.*;

public class P0352_DataStreamAsDisjointIntervals {

    // initial approach: TreeMap
    // the basic idea is to store val as key and its interval as its value.
    // whenever we add a new val, we check if we can do a merge (merge its lower and upper
    // or merge with its lower or merge with its upper bound).
    // Note that when merging with its lower, we need to pay attention that the lower bound's
    // upper bound may be less than or equal to or larger than the val we want to add, so we
    // update its lower's upper bound to the max among them.

    // time: O(logn) for addNum, O(n) for getInterval

    /** Initialize your data structure here. */
    TreeMap<Integer, int[]> map;
    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        Integer ceilKey = map.ceilingKey(val);
        Integer floorKey = map.floorKey(val);

        if (ceilKey != null && floorKey != null &&
                map.get(ceilKey)[0] == val + 1 && map.get(floorKey)[1] == val - 1) {
            map.get(floorKey)[1] = map.get(ceilKey)[1];
            map.remove(ceilKey);
        }
        else if (ceilKey != null && map.get(ceilKey)[0] == val + 1) {
            map.put(val, new int[]{val, map.get(ceilKey)[1]});
            map.remove(ceilKey);
        }
        // note here, if we have a lower bound, and this lower bound's upper bound is within
        // merge area, we update its' upper bound to the max of these two
        else if (floorKey != null && map.get(floorKey)[1] >= val - 1) {
            map.get(floorKey)[1] = Math.max(val, map.get(floorKey)[1]);
        }
        else {
            map.put(val, new int[]{val, val});
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[map.size()][2];
        int i = 0;
        for(int[] a:map.values()){
            res[i++] = a;
        }
        return res;
    }
}
