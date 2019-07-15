package Leetcode;
import java.util.*;

public class P1124_LongestWellPerformingInterval {


    public int longestWPI(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        int max = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                cnt++;
            }
            else {
                cnt--;
            }
            if (cnt > 0) {
                max = i + 1;
            }
            else {
                map.putIfAbsent(cnt, i);
                if (map.containsKey(cnt - 1)) {
                    max = Math.max(max, i - map.get(cnt - 1));
                }
            }
        }
        return max;
    }
}
