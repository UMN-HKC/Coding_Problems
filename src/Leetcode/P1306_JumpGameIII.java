package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P1306_JumpGameIII {

    // approach 1: BFS
    // BFS is faster than dfs in here. Starting with start index and put valid
    // indices it can jump from current index to the queue for next iteration.
    // once we find 0 at some index we are done.

    // time: O(n)
    // space: O(n)

    public boolean canReach(int[] arr, int start) {

        int n = arr.length;
        // build the graph
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int index = q.poll();
            if (arr[index] == 0) return true;
            visited[index] = true;
            int forward = index + arr[index];
            int backward = index - arr[index];
            if (forward < n && !visited[forward]) q.offer(forward);
            if (backward >= 0 && !visited[backward]) q.offer(backward);

        }
        return false;
    }

}
