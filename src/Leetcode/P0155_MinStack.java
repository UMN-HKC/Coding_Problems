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

    // approach 2: using only one stack and variable min

    // only push the old minimum value when the current
    // minimum value changes after pushing the new value x
    // So the worst case the performance will be same as using
    // two stacks.

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

    // approach 3: using one stack
    // stack stores the gap between the pushed value and min at that time
    // But in this approach we need to modify values and store them as long
    // type into our stack to prevent overflow or underflow

    int min;
    Stack<Long> values;
    public MinStack() {
        values = new Stack<>();
    }
    public void push(int x) {
        if (values.empty()) {
            values.push(0l);
            min = x;
        }
        else {
            values.push((long)x - min);
            if (x < min) {
                min = x;
            }
        }

    }

    public void pop() {
        long gap = values.pop();
        // when the gap element is negative, it means the top element
        // is the min value actually. And since we pop it, we need to
        // retrieve and update the current min value
        if (gap < 0) {
            min = (int)(min - gap);
        }
    }

    public int top() {
        long top = values.peek();
        // if top gap is negative, it means min value is the current top element
        if (top < 0) {
            return min;
        }
        else {
            return (int)(top + min);
        }
    }

    public int getMin() {
        return min;
    }
}
