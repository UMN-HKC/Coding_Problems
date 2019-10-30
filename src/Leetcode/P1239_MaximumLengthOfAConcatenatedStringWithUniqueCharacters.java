package Leetcode;

import java.util.*;

public class P1239_MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    // approach 1: brute force

    int max = 0;
    public int maxLength(List<String> arr) {
        dfs(new HashSet<>(), arr, 0);
        return max;
    }
    public void dfs(Set<Character> visited, List<String> arr, int idx) {
        if (idx == arr.size()) return;

        for (int i = idx; i < arr.size(); i++) {
            char[] chars = arr.get(i).toCharArray();
            List<Character> cur = new ArrayList();
            for (int j = 0; j < chars.length; j++) {
                cur.add(chars[j]);
            }
            if (isUnique(visited, cur)) {
                visited.addAll(cur);
                max = Math.max(max, visited.size());
                dfs(visited, arr, i + 1);
                visited.removeAll(cur);
            }
        }
    }
    public boolean isUnique(Set<Character> visited, List<Character> cur) {
        Set<Character> curSet = new HashSet();
        for (int i = 0; i < cur.size(); i++) {
            char c = cur.get(i);
            if (!curSet.add(c) || visited.contains(c)) {
                return false;
            }
        }
        return true;
    }
}
