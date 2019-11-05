package Leetcode;

import java.util.Arrays;

public class P0943_FindTheShortestSuperstring {

    // approach 1: dfs + backtracking  (passed OJ but very slow.
    // The basic idea is to try all possible permutations of the strings, and we will preprocess
    // the cost array cost[i][j] which is the minimum cost(extra length) of appending string j to
    // string i, so that during searching phase we do not need to calculate it every time.

    // time: O(n!)
    // space: O(n)

    int min = Integer.MAX_VALUE;
    int[] path;
    int[] bestPath;
    int[][] cost;
    public String shortestSuperstring(String[] A) {
        int len = A.length;
        cost = new int[len][len];
        bestPath = new int[len];
        path = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                cost[i][j] = calc(A[i], A[j]);
            }
        }
        boolean[] visited = new boolean[len];
        dfs(A, 0, 0, visited);
        return buildPath(A);
    }
    public void dfs(String[] A, int d, int curLen, boolean[] visited) {
        if (curLen >= min) return;
        if (d == A.length) {
            min = curLen;
            bestPath = path.clone();
            return;
        }
        for (int i = 0; i < A.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            path[d] = i;
            dfs(A, d + 1, d == 0 ? A[i].length() : curLen + cost[path[d - 1]][i], visited);
            visited[i] = false;
        }
    }
    public String buildPath(String[] A) {
        StringBuilder sb = new StringBuilder();
        sb.append(A[bestPath[0]]);
        for (int i = 1; i < bestPath.length; i++) {
            int prev = bestPath[i - 1];
            int cur = bestPath[i];
            sb.append(A[cur].substring(A[cur].length() - cost[prev][cur]));
        }
        return sb.toString();
    }
    public int calc(String a, String b) {
        int cost = b.length();
        for (int i = 1; i <= Math.min(a.length() - 1, b.length() - 1); i++) {
            if (a.substring(a.length() - i).equals(b.substring(0, i))) {
                cost = b.length() - i;
            }
        }
        return cost;
    }

    // approach 2: DP (NP complete)
    // link: https://www.youtube.com/watch?v=u_Wc4jwrp3Q

    // cost[i][j] means the length of string to append when A[i] followed by A[j].
    // eg. A[i] = abcd, A[j] = bcde, then graph[i][j] = 1
    // Then the problem becomes to: find the shortest path in this graph which visits
    // every node exactly once. This is a Travelling Salesman Problem.
    // Apply TSP DP solution. Remember to record the path.

    // time: O((2^n) * (n ^ 2))

    public String shortestSuperstring(String[] A) {
        int len = A.length;
        int[][] dp = new int[1 << len][len];
        int[][] path = new int[1 << len][len];
        int[][] cost = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                cost[i][j] = calc(A[i], A[j]);
            }
        }
        for (int s = 1; s < (1 << len); s++) {
            Arrays.fill(dp[s], Integer.MAX_VALUE);
            for (int cur = 0; cur < len; cur++) {
                if ((s & (1 << cur)) > 0) {
                    int ps = s - (1 << cur);
                    if (ps == 0) {
                        dp[s][cur] = A[cur].length();
                    }
                    else {
                        for (int pre = 0; pre < len; pre++) {
                            if (dp[ps][pre] != Integer.MAX_VALUE && dp[ps][pre] + cost[pre][cur] < dp[s][cur]) {
                                dp[s][cur] = dp[ps][pre] + cost[pre][cur];
                                path[s][cur] = pre;
                            }
                        }
                    }
                }
            }
        }
        // find the last string and then use the path array to help us build result
        int min = Integer.MAX_VALUE;
        int last = -1;
        for (int i = 0; i < len; i++) {
            if (dp[(1 << len) - 1][i] < min) {
                min = dp[(1 << len) - 1][i];
                last = i;
            }
        }
        String res = "";
        int finalState = (1 << len) - 1;
        while (finalState > 0) {
            int temp = path[finalState][last];
            if (finalState - (1 << last) == 0) {
                res = A[last] + res;
            }
            else {
                res = A[last].substring(A[last].length() - cost[temp][last]) + res;
            }
            finalState -= (1 << last);
            last = temp;
        }
        return res;
    }
    public int calc(String a, String b) {
        int cost = b.length();
        for (int i = 1; i <= Math.min(a.length() - 1, b.length() - 1); i++) {
            if (a.substring(a.length() - i).equals(b.substring(0, i))) {
                cost = b.length() - i;
            }
        }
        return cost;
    }

}
