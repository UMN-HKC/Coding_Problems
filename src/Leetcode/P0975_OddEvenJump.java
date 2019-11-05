package Leetcode;

import java.util.Map;
import java.util.TreeMap;

public class P0975_OddEvenJump {

    // approach 1: DP + Tree map

    // The basic idea is to use two dp array to record two states: jump to a greater value
    // and jump to a lower value.
    // up[i]: can reach the end of the array when jumping up with start at i index
    // down[i]: can reach the end of the array when jumping down with start at i index
    // To satisfy the requirement of jumping to a smaller index when values are same, and
    // also the ability to reach the next greater or smaller value, we will use tree map
    // to store and update values and index encountered so far. When two same values encountered,
    // the one with smaller index will be stored since we are iterating from the back of the array
    // Note that, when checking whether we can increment result, we check if up[i] is true because
    // the first jump is always jumping up.

    // time: O(nlogn)

    public int oddEvenJumps(int[] A) {
        int res = 1;
        int len = A.length;
        boolean[] up = new boolean[len];
        boolean[] down = new boolean[len];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        up[len - 1] = down[len - 1] = true;
        map.put(A[len - 1], len - 1);
        for (int i = len - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]);
            Map.Entry<Integer, Integer> lo = map.floorEntry(A[i]);
            if (hi != null) up[i] = down[hi.getValue()];
            if (lo != null) down[i] = up[lo.getValue()];
            if (up[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }
}
