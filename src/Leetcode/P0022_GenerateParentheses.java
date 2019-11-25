package Leetcode;
import java.util.*;

public class P0022_GenerateParentheses {

    // approach 1: dfs + memo

    // time: factorial

    public List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<>();
        Map<Integer, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add("()");
        map.put(1, set);
        dfs(map, 1, n);
        return new ArrayList(map.get(n));
    }
    private void dfs(Map<Integer, Set<String>> map, int cur, int N) {
        if (cur == N) return;
        Set<String> set = map.get(cur);
        Set<String> next = new HashSet<>();
        for (String s : set) {
            for (int i = 0; i < s.length(); i++) {
                String par = s.substring(0, i) + "()" + s.substring(i);
                if (!next.contains(par)) {
                    next.add(par);
                }
            }
        }
        map.put(cur + 1, next);
        dfs(map, cur + 1, N);
    }

    // approach 2: idea borrowed from discussion board

    // only add open parentheses when we have not use up all open parentheses
    // only add close parentheses when we have more open parentheses
    // The above two conditions will result in valid parentheses for our final result

    // time: O(2^(2 * n))
    // space: O(n) both call stack and string builder(output list is not included)

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, 0, 0, n);
        return res;
    }
    public void dfs(List<String> res, StringBuilder sb, int open, int close, int num) {
        if (sb.length() == 2 * num) {
            res.add(new String(sb));
            return;
        }
        int len = sb.length();

        // only add open parentheses when we have not use up all open parentheses
        if (open < num) {
            sb.append("(");
            dfs(res, sb, open + 1, close, num);
            sb.setLength(len);
        }
        // only add close parentheses when we have more open parentheses
        if (close < open) {
            sb.append(")");
            dfs(res, sb, open, close + 1, num);
            sb.setLength(len);
        }
    }
}
