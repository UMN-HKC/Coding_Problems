package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0294_FlipGameII {

    // coudln't figure out
    // idea borrowed from discussion board

    // approach 1: backtracking
    // basically iterating through the whole string, and when we encounter a "++",
    // we flip it to "--" and then see if after flipping, the opponent can win.

    // time: factorial, in here we use map to store strings mapped to win/lose
    // space: factorial for map

    public boolean canWin(String s) {
        Map<String, Boolean> map = new HashMap<>();
        return canWin(map, s);
    }
    public boolean canWin(Map<String, Boolean> map, String s) {
        if (s == null || s.length() < 2) return false;
        if (map.containsKey(s)) {
            return map.get(s);
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] == '+' && chars[i+1] == '+') {
                chars[i] = '-';
                chars[i+1] = '-';
                String opponent = new String(chars);
                chars[i] = '+';
                chars[i+1] = '+';
                if (!canWin(map, opponent)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }
}
