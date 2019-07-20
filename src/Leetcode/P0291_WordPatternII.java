package Leetcode;
import java.util.*;

public class P0291_WordPatternII {

    // approach 1: brute force dfs with backtracking

    // the basic idea is that for each character in the pattern string, we will go into our
    // string and check for each different length of matching substring, whether using
    // this pattern matching will result in successful matching later

    // m: length of pattern / n: length of str
    // time: O(m^n)
    // space: O(m), because for each run, the length of all strings in the set should not exceed the length of m

    public boolean wordPatternMatch(String pattern, String str) {
        return dfs(pattern, 0, str, 0, new HashMap<>(), new HashSet<>());
    }
    public boolean dfs(String pattern, int pi, String str, int si, Map<Character, String> map, Set<String> visited) {
        // matches
        if (pi == pattern.length() && si == str.length()) return true;
        // do not match
        if (pi == pattern.length() || si == str.length()) return false;

        // try different length of pattern matching substring for the current pattern character
        for (int len = 1; si + len <= str.length(); len++) {
            Character p = pattern.charAt(pi);
            String s = str.substring(si, si + len);
            if (map.containsKey(p) && map.get(p).equals(s)) {
                if (dfs(pattern, pi+1, str, si + len, map, visited)) {
                    return true;
                }
            }
            else if (!map.containsKey(p)){
                if (!visited.contains(s)) {
                    map.put(p, s);
                    visited.add(s);
                    if (dfs(pattern, pi+1, str, si + len, map, visited)) {
                        return true;
                    }
                    map.remove(p);
                    visited.remove(s);
                }
            }
        }
        return false;
    }
}
