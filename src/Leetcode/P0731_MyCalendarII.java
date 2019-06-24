package Leetcode;
import java.util.*;

public class P0731_MyCalendarII {

    // same approach as I

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int event = 0, cnt = 0;
        for (int v : map.values()) {
            event += v;
            cnt = Math.max(cnt, event);
            if (cnt >= 3) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}
