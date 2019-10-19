package Leetcode;

import java.util.Stack;

public class P0739_DailyTemperature {

    // approach 1: monotonic stack
    // The basic idea is to realize that the problem could be interpreted as
    // maintaining a monotonic stack with input read from back of the input array
    // note that we store indices into the stack so that we can both know the position
    // and get its temperature

    // time: O(n)
    // space: O(n)

    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return T;
        Stack<Integer> s = new Stack<>();
        int[] res = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--) {
            while (!s.empty() && T[s.peek()] <= T[i]) {
                s.pop();
            }
            if (!s.empty()) {
                res[i] = s.peek() - i;
            }
            s.push(i);
        }
        return res;
    }
}
