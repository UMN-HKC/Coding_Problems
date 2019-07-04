package Leetcode;
import java.util.*;

public class P0282_ExpressionAddOperators {

    // approach 1: backtracking
    // Use backtracking to enumerate all possibilities. There are some tricky parts to note.
    // 1. The first number will not have an operator before it, so we will need to deal with it
    // 2. If we are to do multiplication, we will have to subtract the last operation's value
    //    from the accumulated value and then proceed. So we will need to keep track of last
    //    operation's value.
    // 3. Overflow. Since we are parsing string to number, it is possible the respective number
    //    exceed the maximum or minimum integer values.

    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(res, 0, 0, 0, target, num, new StringBuilder());
        return res;
    }
    public void dfs(List<String> res, int index, long accum, long lastVal, int target, String num, StringBuilder sb) {

        if (index == num.length()) {
            if (accum == target) {
                res.add(new String(sb));
            }
        }
        else {
            for (int i = index; i < num.length(); i++) {
                // pay attention to constrains
                if (i != index && num.charAt(index) == '0') break;
                int len = sb.length();
                long digits = Long.parseLong(num.substring(index, i+1));
                // deal with the first number without operator appended to the string
                if (index == 0) {
                    dfs(res, i+1, digits, digits, target, num, sb.append(digits));
                    sb.setLength(len);
                }
                else {
                    dfs(res, i+1, accum + digits, digits, target, num, sb.append("+" + digits));
                    sb.setLength(len);
                    dfs(res, i+1, accum - digits, -digits, target, num, sb.append("-" + digits));
                    sb.setLength(len);
                    dfs(res, i+1, (accum - lastVal) + lastVal * digits, lastVal * digits, target, num, sb.append("*" + digits));
                    sb.setLength(len);
                }
            }
        }
    }
}
