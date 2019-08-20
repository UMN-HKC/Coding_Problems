package Leetcode;

import java.util.Stack;

public class P0227_BasicCalculatorII {


    // approach 1:
//    The idea is to use stack to store our numbers. When the previous sign is '+' or '-', we
//    simply push numbers into our stack. Note that if preSign is '-', we push -num into our
//    stack. When the preSign is '*' or '/', we pop a number from the stack and calculate
//    the result with the current number we have and push back the result to the stack.
//    After the first iteration, all the operation left in the stack is adding. We simply pop
//    out all numbers in the stack and add them to the result.

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            // if c is a sign or c is the last character in our string
            // we need to either treat the sign or deal with the last number
            // Our if condition guarantees these two cases and also skip space
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                }
                else if (sign == '-') {
                    stack.push(-num);
                }
                else if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                else {
                    stack.push(stack.pop() / num);
                }
                sign = c;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.empty()) {
            res += stack.pop();
        }
        return res;
    }

    // approach 2: similar approach but without stack
    public int calculate_no_stack(String s) {
        int num = 0, pre = 0, res = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            // if c is a sign or c is the last character in our string
            // we need to either treat the sign or deal with the last number
            // Our if condition guarantees these two cases and also skip space
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
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
                sign = c;
                num = 0;
            }
        }
        // remember to add pre to result 
        return res + pre;
    }
}
