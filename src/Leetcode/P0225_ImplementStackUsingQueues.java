package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0225_ImplementStackUsingQueues {

    // Initial approach: idea inspired by "Implementing queue using stacks"
    // Basicaslly, we have two queues: input and output queues. Input queue is only
    // for pushing elements in, and output queue is where we top() and pop()

    // time: O(n) for worst case
    // space: O(n)

    Queue<Integer> input;
    Queue<Integer> output;
    public MyStack() {
        input = new LinkedList<>();
        output = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        input.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        top();
        int res = input.poll();
        input = output;
        output = new LinkedList<>();
        return res;
    }

    /** Get the top element. */
    public int top() {
        while (input.size() != 1) {
            output.offer(input.poll());
        }
        return input.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    // approach 2: using only one queue
    // using 1 queue is possible because when you poll from the front you can offer them
    // them to the end of the queue, which is different from stack structure. That is why
    // we need 2 stacks when implementing queue using stack.

    Queue<Integer> queue;
    public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
