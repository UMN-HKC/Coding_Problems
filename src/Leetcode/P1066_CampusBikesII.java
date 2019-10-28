package Leetcode;

import java.util.Arrays;

public class P1066_CampusBikesII {


    // approach 1: dfs
    // basically, workers are the vertices that we dfs on and for each worker we will explore the
    // bike availability and update the global min distance each time the last worker finished pairing
    // essentially, it is a combination problem: 1st worker: n bikes, 2nd worker: (n - 1) bikes,...

    // time: factorial
    // space: O(bikes)

    int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0) return 0;
        boolean[] used = new boolean[bikes.length];
        dfs(workers, 0, bikes, used, 0);
        return min;
    }
    public void dfs(int[][] workers, int idx, int[][] bikes, boolean[] used, int dist) {
        if (dist > min) return;
        if (idx == workers.length) {
            min = Math.min(dist, min);
            return;
        }
        for (int i = 0; i < bikes.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            dfs(workers, idx + 1, bikes, used, dist + calculate(bikes[i], workers[idx]));
            used[i] = false;
        }
    }

    public int calculate(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    // approach 2: DP (top-down) easier for me to understand
    // basically it is dfs + memoization

    // The idea is to convert the combination problem into a permutation problem by applying
    // bit manipulation where we use bits to represent states of bikes availability
    // In the above DFS solution, we have caculated [(b2,w2),(b1,w1),(b3,w3)], [(b1,w1),(b3,w3),
    // (b2,w2)] and so on. The distance of them are exactly same, However we only need one.
    // In another word, we only need to know the distance of set, not list :{(b1,w1),(b2,w2),(b3,w3)},
    // now the problem change from permutation problem to combination.
    //
    // For ith worker, the min distance = ith worker uses jth bike + min
    // distance all i - 1 workers to use i -1 others bike from m. so this is dp problem.

    public int assignBikes_2(int[][] workers, int[][] bikes) {
        int w = workers.length, b = bikes.length;
        int[][] memo = new int[w][1 << b];
        for (int[] m : memo) {
            Arrays.fill(m, Integer.MAX_VALUE);
        }
        return dfs(workers, 0, 0, memo, bikes);
    }
    public int dfs(int[][] workers, int idx, int state, int[][] memo, int[][] bikes) {
        if (idx == workers.length) return 0;
        if (memo[idx][state] != Integer.MAX_VALUE) {
            return memo[idx][state];
        }
        int dist = Integer.MAX_VALUE;
        for (int i = 0; i < bikes.length; i++) {
            if (((1 << i) & state) != 0) continue;
            int curDist = calculate(workers[idx], bikes[i]);
            dist = Math.min(dist, curDist + dfs(workers, idx + 1, state | (1 << i), memo, bikes));
        }
        memo[idx][state] = dist;
        return dist;
    }
}
