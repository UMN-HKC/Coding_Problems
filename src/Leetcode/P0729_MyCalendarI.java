package Leetcode;
import java.util.*;

public class P0729_MyCalendarI {

    // idea borrowed from discussion board
    // we use tree map to order our intervals which will sort the intervals by
    // the key, which is the start time. When we want to add a event to our
    // calendar, we look into our tree map and see if there's an event right
    // before the start of the prospective event that would conflict, and if
    // there's an event right after the start of the prospective event that
    // would conflict

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public boolean book(int start, int end) {
        Integer floor = map.floorKey(start);
        if (floor != null && map.get(floor) > start) return false;
        Integer ceiling = map.ceilingKey(start);
        if (ceiling != null && ceiling < end) return false;
        map.put(start, end);
        return true;
    }
}
