package Leetcode;

public class P1055_ShortestWayToFormString {

    // approach 1: two pointers + greedy
    // one pointers iterate target string and one pointer iterates source string.
    // Try to match more target string using source string during each iteration
    // if within one iteration, target string pointer does not move, it means the
    // current character in the target string does not exist in the source string.

    // time: O(m * n)

    public int shortestWay(String source, String target) {
        int t = 0;
        int res = 0;
        while (t < target.length()) {
            int pre = t;
            for (int i = 0; t < target.length() && i < source.length(); i++) {
                if (source.charAt(i) == target.charAt(t)) {
                    t++;
                }
            }
            if (t == pre) return -1;
            res++;
        }
        return res;
    }

    // approach 2: TO-DO
}
