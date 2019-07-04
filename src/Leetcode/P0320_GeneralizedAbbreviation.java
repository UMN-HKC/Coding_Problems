package Leetcode;
import java.util.*;

public class P0320_GeneralizedAbbreviation {

    // The basic idea is that for each index position in the word, we either
    // choose to omit it or abbreviate it, similar to subset problem where
    // for each element, we either choose to include it in the subset ot not

    // time: O(2^n)
    // space O(logn) stack space

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(res, 0, 0, new StringBuilder(), word);
        return res;
    }
    public void dfs(List<String> res, int index, int cnt, StringBuilder sb, String word) {

        // get the length of sb at the beginning to backtrack in the end
        // it is important that we record the length at the very beginning
        // because we will have to backtrack for both adding result and
        // intermediate steps.

        int len = sb.length();
        if (index == word.length()) {
            // see that we modify sb here, that's why we need to record
            // length of sb at the beginning and backtrack in the end of function
            if (cnt > 0) sb.append(cnt);
            res.add(new String(sb));
        }
        else {
            dfs(res, index+1, cnt+1, sb, word);
            if (cnt > 0) sb.append(cnt);
            dfs(res, index+1, 0, sb.append(word.charAt(index)), word);
        }
        // backtrack
        sb.setLength(len);
        return;
    }
}
