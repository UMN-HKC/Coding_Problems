package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class P0901_OnlineStockSpan {

    // approach 1: monotonic stack
    // The basic idea is to keep a monotonic non-increasing stack. For prices smaller than
    // the current day, we could keep a counter for today which denotes how many days' prices
    // are smaller than today.

    // Time: amortized O(1) for each next() call

    Deque<int[]> stack;
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int cur = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            cur += stack.removeFirst()[1];
        }
        stack.addFirst(new int[]{price, cur});
        return cur;
    }

    private static void swap(List<String> aList, int from, int to) {
        String temp = aList.get(from);
        aList.set(from, aList.get(to));
        aList.set(to, temp);
    }
}
