package Leetcode;

import java.util.*;

public class P0139_WordBreak {

    // approach 1: dfs with memoization

    // used dfs without memoization initially and of course TLE => O(2^n)
    // Instead, the following approach used memoization while doing recursion
    // The basic idea is we use map to record if some substring is in dictionary or not
    // if a string is not in our map or dictionary, we will split the string into
    // two parts at each index and check if left is in the dictionary and right is breakable

    // time: O(n^2)
    // space: O(n^2) stack depth

    Map<String, Boolean> map = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, set);

    }
    public boolean dfs(String s, Set<String> set) {
        if (map.containsKey(s)) return map.get(s);
        if (set.contains(s)) return true;
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            if (set.contains(left) && dfs(right, set)) {
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }

    // approach 2: DP
    // iterative version of approach 1

    // time: O(n^2)
    // space: O(n);

    public boolean wordBreak_dp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>(wordDict);
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[dp.length - 1];
    }
}
