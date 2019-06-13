package Leetcode;

import java.util.Stack;

public class P0150_EvaluateReversePolishNotation {


    // initial approach: stack
    // Basically, push integers all to the stack and when we encounter an operator, we pop
    // two elements from the stack and push the result back to the stack when done calculation
    // remember that the final result is stored in the stack after the loop

    // time: O(n)
    // space: O(n)

    public int evalRPN(String[] tokens) {
        String operators = "+-*/";
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (operators.indexOf(tokens[i]) != -1) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                if (tokens[i].equals("+")) stack.push(operand1 + operand2);
                if (tokens[i].equals("-")) stack.push(operand1 - operand2);
                if (tokens[i].equals("*")) stack.push(operand1 * operand2);
                if (tokens[i].equals("/")) stack.push(operand1 / operand2);
            }
            else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }
}
