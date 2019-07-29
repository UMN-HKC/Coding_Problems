package Leetcode;
import java.util.*;

public class P0022_GenerateParentheses {

    // idea borrowed from discussion
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
