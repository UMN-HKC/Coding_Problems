package Leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class P0224_BasicCalculator {

    // approach 1: brute force, initial approach(slow)
    public int calculate_brute(String s) {
        if (s == null || s.length() == 0) return 0;
        return helper(s, 0, s.length() - 1);
    }
    public int helper(String s, int l, int r) {
        Deque<Integer> operands = new LinkedList<>();
        Deque<Character> operators = new LinkedList<>();
        while (l <= r) {
            l = skipSpace(s, l, r);
            if (l > r) break;
            if (s.charAt(l) == '(') {
                int c = findClose(s, l);
                int result = helper(s, l+1, c-1);
                l = c + 1;
                operands.offerLast(result);
            }
            else if (s.charAt(l) >= '0' && s.charAt(l) <= '9') {
                int num = 0;
                while (l <= r && s.charAt(l) >= '0' && s.charAt(l) <= '9') {
                    num = num * 10 + (s.charAt(l) - '0');
                    l++;
                }
                operands.offerLast(num);
            }
            else {
                operators.offerLast(s.charAt(l));
                l++;
            }
        }
        while (!operators.isEmpty()) {
            char op = operators.pollFirst();
            int n1 = operands.pollFirst();
            int n2 = operands.pollFirst();
            operands.offerFirst(op == '+' ? n1 + n2 : n1 - n2);
        }
        return operands.poll();
    }
    public int skipSpace(String s, int l, int r) {
        while (l <= r && s.charAt(l) == ' ') {
            l++;
        }
        return l;
    }
    public int findClose(String s, int open) {
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

    // approach 2: stack (linear)

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0, sign = 1, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            else if (c == '+' || c == '-') {
                res += sign * num;
                num = 0;
                sign = c == '+' ? 1 : -1;
            }
            else if (c == '(') {
                // wrap up the last context and push to stack for later use
                // and remember to reset res = 0 for new context calculation
                stack.push(res);
                stack.push(sign);
                res = 0;
                num = 0;
                sign = 1;
            }
            else if (c == ')') {
                res += sign * num;
                res *= stack.pop();
                res += stack.pop();
                num = 0;
                sign = 1;
            }
        }
        // check
        if (num != 0) res += sign * num;
        return res;
    }
}
