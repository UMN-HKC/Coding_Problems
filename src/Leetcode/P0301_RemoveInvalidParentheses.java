package Leetcode;
import java.util.*;

public class P0301_RemoveInvalidParentheses {

    // approach 1: BFS

    // Brute force

    // time: O(n * (2^n))
    // space: O(n * (2^n))  supposing n is the average length of each
    // substring and we have 2^n substring

    //  Regarding the time complexity, I think one way we can think about the search space is as
    //  a power subset of the original string. So it includes all possible substrings from 0
    //  character to N(number of chars in the input string) characters. So the possibilities
    //  are 2^n. (we either pick a character or don't pick it) For each subset we check if it
    //  is a valid string so it becomes n*(2^n)

    public List<String> removeInvalidParentheses_1(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || isValid(s)) {
            res.add(s);
            return res;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        boolean found = false;
        while (!found && !q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                for (int j = 0; j < cur.length(); j++) {
                    if (!(cur.charAt(j) >= 'a' && cur.charAt(j) <= 'z')) {
                        String newStr = cur.substring(0,j) + cur.substring(j+1);
                        if (!visited.contains(newStr)) {
                            if (isValid(newStr)) {
                                found = true;
                                res.add(newStr);
                            }
                            else {
                                q.offer(newStr);
                            }
                            visited.add(newStr);
                        }

                    }
                }
            }
        }

        return res;
    }
    public boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cnt++;
            }
            if (c == ')') {
                cnt--;
            }
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }

    // approach 2: DFS

    // huahua's explanation: https://www.youtube.com/watch?v=2k_rS_u6EBk&t=1149s
    // time: O(n* (2^(l+r)))
    // space: O((l+r) * n) since we have (l+r) depth and at each level we have substring of
    // average length of n

    public List<String> removeInvalidParentheses_2(String s) {
        List<String> res = new ArrayList<>();
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            left += c == '(' ? 1 : 0;
            if (left == 0) {
                if (c == ')') {
                    right++;
                }
            }
            else {
                left -= c == ')' ? 1 : 0;
            }
        }
        remove(res, s, 0, left, right);
        return res;
    }
    public void remove(List<String> res, String s, int lastRemovePos, int left, int right) {
        if (left == 0 && right == 0 && isValid(s)) {
            res.add(s);
            return;
        }
        for (int i = lastRemovePos; i < s.length(); i++) {
            if (i != lastRemovePos && s.charAt(i) == s.charAt(i - 1)) continue;
            if ((s.charAt(i) == '(' && left > 0) || (s.charAt(i) == ')' && right > 0)) {
                if (s.charAt(i) == '(' && left > 0) {
                    remove(res, s.substring(0, i) + s.substring(i + 1), i, left - 1, right);
                }
                else {
                    remove(res, s.substring(0, i) + s.substring(i + 1), i, left, right - 1);
                }
            }

        }
    }
    public boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }
}
