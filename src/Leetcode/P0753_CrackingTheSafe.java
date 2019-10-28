package Leetcode;

import java.util.HashSet;
import java.util.Set;

public class P0753_CrackingTheSafe {

    // approach 1: dfs (brute force)

    // De Bruijn sequence
    // a de Bruijn sequence of order n on a size-k alphabet A is a cyclic sequence in
    // which every possible length-n string on A occurs exactly once as a substring
    // (i.e., as a contiguous subsequence). Such a sequence is denoted by B(k, n) and
    // has length kn, which is also the number of distinct substrings of length n on A;
    // de Bruijn sequences are therefore optimally short.

    // The basic idea

    public String crackSafe(int n, int k) {
        int target = (int)Math.pow(k, n);
        Set<String> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("0");
        visited.add(sb.toString());
        dfs(visited, sb, target, n, k);
        return sb.toString();
    }
    public boolean dfs(Set<String> visited, StringBuilder sb, int target, int n, int k) {
        if (visited.size() == target) return true;
        String previous = sb.substring(sb.length() - n  + 1);
        for (int i = 0; i < k; i++) {
            String newStr = previous + i;
            if (visited.contains(newStr)) continue;
            visited.add(newStr);
            sb.append(i);
            if (dfs(visited, sb, target, n, k)) {
                return true;
            }
            visited.remove(newStr);
            sb.setLength(sb.length() - 1);
        }
        return false;
    }
}
