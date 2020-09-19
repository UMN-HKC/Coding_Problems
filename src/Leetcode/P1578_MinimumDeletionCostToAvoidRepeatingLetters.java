package Leetcode;

import java.util.PriorityQueue;

public class P1578_MinimumDeletionCostToAvoidRepeatingLetters {

    // initial approach
    // The idea is to maintain a running sum and use priority queue to sort and get the maximum cost

    public int minCost(String s, int[] cost) {
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            int j = i;
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int curCost = 0;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                pq.offer(cost[j]);
                curCost += cost[j];
                j++;
            }
            if (pq.size() > 1) {
                res += (curCost - pq.peek());
            }
            i = j;
        }
        return res;
    }

    // approach 2: linear
    // The idea is to maintain the running sum and max value for repeated letters.
    // We don't need a priority queue to do it.

    public int minCost_2(String s, int[] cost) {
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            int j = i;
            int curCost = 0;
            int max = 0;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                curCost += cost[j];
                max = Math.max(max, cost[j]);
                j++;
            }
            if (j > i + 1) {
                res += curCost - max;
            }
            i = j;
        }
        return res;
    }
}
