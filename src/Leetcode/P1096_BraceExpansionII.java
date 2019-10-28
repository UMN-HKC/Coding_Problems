package Leetcode;

import java.util.*;

public class P1096_BraceExpansionII {

    // approach 1: recursion + helper function to split ","

    // The difficult part is to realize that "," should be treated first before we do any
    // concatenation. In my first attempt, I did not separate the functionality of spliting
    // string that has "," in the same level, which resulted in wrong solution.
    // So to solve that part, the basic idea is to that, first we will create a helper
    // function that split the original string to a list of string that are delimited
    // by "," at the same level, so we do not have to deal with union operation in our
    // core functionality.

    public List<String> braceExpansionII(String e) {
        if (e == null || e.length() == 0) return new ArrayList<>();
        // first deal with ',' in the current level
        List<String> subList = helper(e);
        if (subList.size() > 1) {
            Set<String> unique = new HashSet<>();
            for (int i = 0; i < subList.size(); i++) {
                unique.addAll(braceExpansionII(subList.get(i)));
            }
            return new ArrayList<>(unique);
        }
        // if there's no ',' in current level, we proceed with the expectation
        // that there's no ',' in the current level which makes our life easier
        if (e.indexOf("{") == -1) {
            return new ArrayList(Arrays.asList(e.split(",")));
        }
        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        char c = e.charAt(0);
        if (c == '{') {
            int close = findClose(e, 0);
            left = braceExpansionII(e.substring(1, close));
            right = braceExpansionII(e.substring(close + 1));
        }
        else {
            int i = 0;
            while (i < e.length() && e.charAt(i) >= 'a' && e.charAt(i) <= 'z') {
                i++;
            }
            left.add(e.substring(0, i));
            right = braceExpansionII(e.substring(i));
        }
        return permutation(left, right);
    }
    public List<String> helper(String e) {
        int curLevel = 0;
        int start = 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < e.length(); i++) {
            if (e.charAt(i) == ',') {
                if (curLevel == 0) {
                    res.add(e.substring(start, i));
                    start = i + 1;
                }
            }
            else if (e.charAt(i) == '{') curLevel++;
            else if (e.charAt(i) == '}') curLevel--;
        }
        res.add(e.substring(start));
        return res;
    }
    public List<String> permutation(List<String> l1, List<String> l2) {
        if (l1.size() == 0) {
            Collections.sort(l2);
            return l2;
        }
        if (l2.size() == 0) {
            Collections.sort(l1);
            return l1;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l2.size(); j++) {
                res.add(l1.get(i) + l2.get(j));
            }
        }
        Collections.sort(res);
        return res;
    }
    public int findClose(String e, int i) {
        int cnt = 1;
        i++;
        for (; i < e.length(); i++) {
            if (e.charAt(i) == '{') cnt++;
            if (e.charAt(i) == '}') cnt--;
            if (cnt == 0) return i;
        }
        return -1;
    }
}
