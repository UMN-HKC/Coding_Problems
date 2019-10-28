package Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P1087_BraceExpansion {

    // approach 1: DFS

    // The basic idea is to check the first letter and see if we have option here or
    // only a letter. Then, convert the first part to a list of string, recurse on the
    // second part and then combining them together.

    public String[] expand(String S) {
        if (S == null || S.length() == 0) return new String[0];

        String[] left, right;
        char c = S.charAt(0);
        if (c == '{') {
            int closeIdx = S.indexOf("}", 0);
            left = S.substring(1, closeIdx).split(",");
            right = expand(S.substring(closeIdx + 1));
        }
        else {
            int i = 0;
            while (i < S.length() && S.charAt(i) != '{') {
                i++;
            }
            left = new String[1];
            left[0] = S.substring(0, i);
            right = expand(S.substring(i));
        }
        // need to check if empty
        if (left == null || left.length == 0) return right;
        if (right == null || right.length == 0) return left;

        List<String> res = new ArrayList<>();
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < right.length; j++) {
                res.add(left[i] + right[j]);
            }
        }
        Collections.sort(res);
        return res.toArray(new String[0]);
    }
}
