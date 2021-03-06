package Leetcode;

import java.util.Stack;

public class P0020_ValidParentheses {

    // approach 1: stack

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            }
            else if (c == '{') {
                stack.push('}');
            }
            else if (c == '[') {
                stack.push(']');
            }
            else {
                if (stack.empty() || stack.pop() != c) return false;
            }
        }
        return stack.empty();
    }

}
