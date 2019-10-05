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
    // The idea is that we first calculate number of invalid left and right parentheses.
    // The sum of those invalid left and right parentheses is the depth of our recursion
    // Then we dfs to remove those parentheses and add valid string when left == 0 and right == 0
    // to the result.
    // Note that, when removing parentheses, we always first remove extra invalid
    // right parentheses. If right == 0, we remove invalid left parentheses. Also,
    // to prevent duplicate result, we use lastRemovePos variable to indicate the start
    // position of the string for each recursion.


    // time: O(n ^ (l+r))) recursion depth is number of invalid parentheses
    // space: O((l+r) * n) since we have (l+r) depth and at each level we have substring of
    // average length of n

    public List<String> removeInvalidParentheses_2(String s) {
        List<String> res = new ArrayList<>();
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                left++;
            }
            else if (c == ')') {
                if (left == 0) right++;
                else {
                    left--;
                }
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
            // if the character is not the first and same as last character
            // we will skip it, since we might have already recursed on the
            // last character if that character is an invalid parentheses
            if (i != lastRemovePos && s.charAt(i) == s.charAt(i - 1)) continue;
            // delete invalid right parentheses first to make prefix valid
            if (right > 0) {
                if (s.charAt(i) == ')') {
                    remove(res, s.substring(0, i) + s.substring(i + 1), i, left, right - 1);
                }
            }
            else {
                if (s.charAt(i) == '(') {
                    remove(res, s.substring(0, i) + s.substring(i + 1), i, left - 1, right);
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
