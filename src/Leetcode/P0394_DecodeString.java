package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P0394_DecodeString {

    // approach 1: initial approach
    // The basic idea is that once we encounter a number, we will recurse on the substring
    // inside the bracket following the number. But we are doing repetitive work to find
    // closing bracket. We will improve on that in the next approach where we pre calculate
    // open and close brackets positions.

    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                String cntStr = "";
                while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    cntStr = cntStr + (s.charAt(i) - '0');
                    i++;
                }
                int cb = findClosing(s, i);
                String inside = decodeString(s.substring(i+1, cb));
                int cnt = Integer.valueOf(cntStr);
                for (int j = 0; j < cnt; j++) {
                    sb.append(inside);
                }
                i = cb;
            }
            else if (Character.isLetter(s.charAt(i))) {
                sb.append(s.charAt(i));
            }

            i++;
        }
        return sb.toString();
    }
    public int findClosing(String s, int start) {
        int cnt = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                cnt++;
            }
            if (s.charAt(i) == ']') {
                cnt--;
            }
            if (cnt == 0) {
                return i;
            }
        }
        return 0;
    }

    // improve time complexity
    // using stack, pre calculate positions of open and close brackets and store into a map

    public String decodeString_2(String s) {
        if (s == null || s.length() == 0) return s;
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                stack.push(i);
            }
            else if (!stack.empty() && s.charAt(i) == ']') {
                map.put(stack.pop(), i);
            }
        }
        return helper(map, s, 0, s.length() - 1);
    }
    public String helper(Map<Integer, Integer> map, String s, int l, int r) {
        StringBuilder sb = new StringBuilder();
        while (l <= r) {
            if (Character.isDigit(s.charAt(l))) {
                int num = 0;
                while (Character.isDigit(s.charAt(l))) {
                    num = num * 10 + s.charAt(l) - '0';
                    l++;
                }
                int close = map.get(l);
                String next = helper(map, s, l + 1, close - 1);
                for (int j = 0; j < num; j++) {
                    sb.append(next);
                }
                l = close + 1;
            }
            else {
                sb.append(s.charAt(l));
                l++;
            }
        }
        return sb.toString();
    }

    // most optimized using stack, but only one go

    public String decodeString_3(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
