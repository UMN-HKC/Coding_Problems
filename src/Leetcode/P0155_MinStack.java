package Leetcode;

import java.util.Stack;

public class P0155_MinStack {

    // initial approach: using two stack
    // one stack for the actual elements and the other for the minimum element so far in the stack

    Stack<Integer> stack;
    Stack<Integer> minSofar;
    public MinStack_two_stack() {
        stack = new Stack<>();
        minSofar = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minSofar.empty() && minSofar.peek() < x) {
            x = minSofar.peek();
        }
        minSofar.push(x);
    }

    public void pop() {
        stack.pop();
        minSofar.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minSofar.peek();
    }

    // approach 2: using only one stack

    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        if(x <= min){
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
