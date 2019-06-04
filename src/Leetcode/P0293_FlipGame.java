package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0293_FlipGame {

    // for each consecutive '++', flip it to '--' and after adding to res, flip it back

    // time: O(n)
    // space: O(res)

    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] == '+' && chars[i+1] == '+') {
                chars[i] = '-';
                chars[i+1] = '-';
                res.add(new String(chars));
                chars[i] = '+';
                chars[i+1] = '+';
            }
        }
        return res;
    }
}
