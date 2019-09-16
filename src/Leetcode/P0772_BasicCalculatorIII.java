package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0772_BasicCalculatorIII {

    // approach 1:

    // based on BasicCalculatorII, use recursion to deal with brackets

    // time: O(n^2)
    // space: O(n)

    public int calculate(String s) {
        s = "(" + s + ")";
        return helper(s, 0, s.length() - 1);
    }
    public int helper(String s, int l, int r) {
        l++;r--;
        int tempSum = 0, sum = 0, num = 0;
        char sign = '+';
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (c == '(') {
                int rp = findClosing(s, i);
                num = helper(s, i, rp);
                i = rp;
                if (i != r)
                    continue;
            }
            if ((!Character.isDigit(c) && c != ' ') || i == r) {
                if (sign == '+') {
                    sum += tempSum;
                    tempSum = num;
                }
                else if (sign == '-') {
                    sum += tempSum;
                    tempSum = -num;
                }
                else if (sign == '*') {
                    tempSum *= num;
                }
                else {
                    tempSum /= num;
                }
                sign = c;
                num = 0;
            }
        }
        return sum + tempSum;
    }
    public int findClosing(String s, int open) {
        int cnt = 0;
        for (int i = open; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            if (cnt == 0) {
                return i;
            }
        }
        return -1;
    }

    // approach 2: recursion + queue
    // The key is to still apply Basic Calculator II's approach but use queue
    // to handle brackets. Normally we would spend linear time to find the
    // closing bracket and recursively solve Basic Calculator II which ends up
    // with O(n^2) time complexity as approach 1. Here, we parse the string to
    // a char array and whenever we encounter an open bracket, we pass the queue
    // to the function again and recursively solve the value inside and store
    // that value to num which is in our current recursion stack. Evrything else
    // is same as Basic Calculator II.

    // time: O(n)
    // space: O(n)

    public int calculate_2(String s) {
        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // filter out white space
            if (c != ' ') {
                q.offer(c);
            }
        }
        // placeholder so that we know it is end of string
        q.offer(' ');
        return cal(q);
    }
    public int cal(Queue<Character> q) {
        int res = 0, pre = 0, num = 0;
        char sign = '+';
        while (!q.isEmpty()) {
            char c = q.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (c == '(') {
                num = cal(q);
            }
            else {
                if (sign == '+') {
                    res += pre;
                    pre = num;
                }
                else if (sign == '-') {
                    res += pre;
                    pre = -num;
                }
                else if (sign == '*') {
                    pre *= num;
                }
                else {
                    pre /= num;
                }
                // we only break out of the loop after we've done calculation above

                if (c == ')') break;
                sign = c;
                num = 0;
            }
        }
        return res + pre;
    }
}
