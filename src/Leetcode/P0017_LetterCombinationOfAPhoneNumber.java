package Leetcode;
import java.util.*;

public class P0017_LetterCombinationOfAPhoneNumber {

    // initial approach: backtracking

    public static String[] rep = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        dfs(res, 0, digits, new StringBuilder());
        return res;
    }
    public void dfs(List<String> res, int index, String digits, StringBuilder sb) {
        // goal
        if (sb.length() == digits.length()) {
            res.add(new String(sb));
            return;
        }
        for (int i = index; i < digits.length(); i++) {
            int pos = digits.charAt(i) - '0';
            String seq = rep[pos];
            for (int j = 0; j < seq.length(); j++) {
                // choice
                sb.append(seq.charAt(j));
                dfs(res, i + 1, digits, sb);
                // backtrack
                sb.setLength(sb.length() - 1);
            }
        }
        return;
    }
}
