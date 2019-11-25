package Leetcode;
import java.util.*;

public class P0140_WordBreakII {


    /***
     *
     * need to revisit
     *
     * https://www.youtube.com/watch?v=JqOIRBC0_9c&t=701s
     *
     *  couldn't figure out, and took me quite some time to understand the solution
     *  basically dfs with memoization, top-down approach
     *  for a single string, we iterate through its index and for each index we divide the
     *  string to two parts: left and right. For right part, we check if it is in the dictionary.
     *  if not, we don't even bother check left part. If it is in the dictionary, we call our function
     *  on the left part and get list result, then we append the right string to each result in
     *  our list result
     *
     * take "catsanddog" for example
     *
     * left          right
     * ""         "catsanddog"
     * "c"         "atsanddog"
     * "ca"         "tsanddog"
     * "cat"         "sanddog"
     * "cats"         "anddog"
     * "catsa"         "nddog"
     * "catsan"         "ddog"
     * "catsand"         "dog"
     * "catsandd"         "og"
     * "catsanddo"         "g"
     * "catsanddog"         ""
     */

    // time: O(2^n) in worst case
    // since in the worst case, all combinations are valid
    // s = "aaaaaaaa", dict = ["a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa"]
    // space: O(2^n)

    Map<String, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, dict);
    }
    public List<String> dfs(String s, Set<String> dict) {
        if (map.containsKey(s)) return map.get(s);

        List<String> res = new ArrayList<>();
        if (dict.contains(s)) {
            res.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            String right = s.substring(i);
            if (!dict.contains(right)) {
                continue;
            }
            String left = s.substring(0, i);
            List<String> leftAns = append(dfs(left, dict), right);
            for (String leftStr : leftAns) {
                res.add(leftStr);
            }
        }
        map.put(s, res);
        return res;
    }
    public List<String> append(List<String> list, String s) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StringBuilder appendedAns = new StringBuilder(list.get(i));
            res.add(new String(appendedAns.append(" " + s)));
        }
        return res;
    }
}
