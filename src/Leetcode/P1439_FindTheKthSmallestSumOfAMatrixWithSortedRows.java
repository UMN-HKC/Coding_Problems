package Leetcode;

import java.util.PriorityQueue;

public class P1439_FindTheKthSmallestSumOfAMatrixWithSortedRows {

    // approach 1: bfs + heap
    
    // The basic idea is that we will do bfs on each level to get the all summation options to this
    // level so far. We will only include the first k smaller options storing in a min heap, because
    // we know that the rest of bigger summation options will never be used since we only care about the
    // first k summation options.

    // time: O(m * n * k * log(k))

    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        maxQ.offer(0);
        for (int i = 0; i < m; i++) {
            PriorityQueue<Integer> nextMaxQ = new PriorityQueue<>((a, b) -> b - a);
            while (!maxQ.isEmpty()) {
                int cur = maxQ.poll();
                for (int num : mat[i]) {
                    nextMaxQ.offer(cur + num);
                    if (nextMaxQ.size() > k) {
                        nextMaxQ.poll();
                    }
                }
            }
            maxQ = nextMaxQ;
        }
        return maxQ.poll();
    }
}
