package Leetcode;
import java.util.*;

public class P0093_RestoreIPAddress {

    // The basic idea is to

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, 0, 4, new StringBuilder(), s);
        return res;
    }
    public void dfs(List<String> res, int index, int dotLeft, StringBuilder sb, String s) {
        // goal (before achieving our goal, exclude some overshots)
        if ((dotLeft != 0 && index >= s.length()) || (dotLeft == 0 && index < s.length())) return;
        if (dotLeft == 0) {
            res.add(new String(sb));
            return;
        }
        int len = sb.length();
        for (int i = 1; i <= 3 && i + index <= s.length(); i++) {
            String num = s.substring(index, index + i);
            // constrains
            if (Integer.valueOf(num) > 255 || (i >= 2 && num.charAt(0) == '0')) break;
            // our choice
            sb.append(num);
            if (dotLeft > 1) {
                sb.append(".");
            }
            dfs(res, index + i, dotLeft - 1, sb, s);
            // backtrack
            sb.setLength(len);
        }
        return;
    }
}
