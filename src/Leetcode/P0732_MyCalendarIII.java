package Leetcode;
import java.util.*;

public class P0732_MyCalendarIII {

    // same approach as I

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        // use event to calculate current 'ongoing' event and k to record the maximum
        int event = 0;
        int k = 0;
        for (int val : map.values()) {
            event += val;
            k = Math.max(k, event);
        }
        return k;
    }
}
