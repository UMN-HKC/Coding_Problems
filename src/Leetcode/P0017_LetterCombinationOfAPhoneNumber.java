package Leetcode;
import java.util.*;

public class P0017_LetterCombinationOfAPhoneNumber {

    // approach 1: dfs + backtracking

    // time: O(4^n)
    // space: O(n) stack space + result space

    private static final String[] MAPPINGS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        StringBuilder sb = new StringBuilder();
        dfs(res, digits, 0, sb);
        return res;
    }
    private void dfs(List<String> res, String digits, int idx, StringBuilder sb) {
        if (idx == digits.length()) {
            res.add(sb.toString());
        }
        else {
            int digit = digits.charAt(idx) - '0';
            String abs = MAPPINGS[digit];
            for (int i = 0; i < abs.length(); i++) {
                sb.append(abs.charAt(i));
                dfs(res, digits, idx + 1, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    // approach 2: bfs

    // time: O(4^n)
    // space: no stack space used, only space for result

    public List<String> letterCombinations_2(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) return res;

        res.add("");
        for (char c : digits.toCharArray()) {
            int size = res.size();
            int digit = c - '0';
            String str = MAPPINGS[digit];
            for (int i = 0; i < size; i++) {
                String pre = res.removeFirst();
                for (int j = 0; j < str.length(); j++) {
                    res.add(pre + str.charAt(j));
                }
            }
        }
        return res;
    }
}
