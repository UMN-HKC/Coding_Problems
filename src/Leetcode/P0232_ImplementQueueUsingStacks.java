package Leetcode;

public class P0232_ImplementQueueUsingStacks {

    // initial approach: brute force, whenever we need to push, we push to stack1(so if stack2
    // contains elements, we pop them all to stack 1 and then push the new element to stack1)
    // Same thing for pop() and peek(). This implementation costs more time than the second approach
    // because each element might be moved between two stacksextra times compared to second approach
    // thus, amortized time is not O(1)

    // time: O(n) worst
    // space: O(n)

    /** Initialize your data structure here. */
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
        // int res = stack2.pop();
        // while (!stack2.empty()) {
        //     stack1.push(stack2.pop());
        // }
        // return stack2.
    }

    /** Get the front element. */
    public int peek() {
        if (!stack2.empty()) {
            return stack2.peek();
        }
        else {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    //--------------------------------------------------------------------------

    // approach 2: still two stacks but stack1 only stores input elements(front elements), and
    // stack2 only stores the back elements. Moving all elements from stack1 to stack2 only happens
    // when stack2 is empty. This implementation is a little more efficient.

    // Quoted from Stefan, "The loop in peek does the moving from input to output stack. Each element
    // only ever gets moved like that once, though, and only after we already spent time pushing it,
    // so the overall amortized cost for each operation is O(1)."

    // time: O(n) worst case, and O(1) amortized
    // space: O(n)

    /** Initialize your data structure here. */
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return stack2.pop();
        // int res = stack2.pop();
        // while (!stack2.empty()) {
        //     stack1.push(stack2.pop());
        // }
        // return stack2.
    }

    /** Get the front element. */
    public int peek() {
        if (!stack2.empty()) {
            return stack2.peek();
        }
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }
}
