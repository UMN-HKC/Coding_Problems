package Leetcode;
import java.util.*;

public class P0323_NumberOfConnectedComponentsInUndirectedGraph {

    // approach 1: dfs

    public int countComponents_dfs(int n, int[][] edges) {
        List<Integer>[] G = new ArrayList[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            G[from].add(to);
            G[to].add(from);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(G, visited, i);
            }
        }
        return cnt;
    }
    public void dfs(List<Integer>[] G, boolean[] visited, int cur) {
        visited[cur] = true;
        for (Integer neighbor : G[cur]) {
            if (!visited[neighbor]) {
                dfs(G, visited, neighbor);
            }
        }
        return;
    }

    // approach 2: bfs

    public int countComponents_bfs(int n, int[][] edges) {
        List<Integer>[] G = new ArrayList[n];
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            G[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            G[from].add(to);
            G[to].add(from);
        }

        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cnt++;
                q.offer(i);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    visited[cur] = true;
                    for (Integer neighbor : G[cur]) {
                        if (!visited[neighbor]) {
                            q.offer(neighbor);
                        }
                    }
                }
            }
        }
        return cnt;
    }

    // approach 3: union find without creating UF class
    // with path compression and weighed uf

    public int countComponents_uf(int n, int[][] edges) {
        int[] id = new int[n], weight = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            weight[i] = 1;
        }
        for (int[] edge : edges) {
            int p = edge[0], q = edge[1];
            int pi = find(id, p), qi = find(id, q);
            if (pi != qi) {
                n--;
                if (weight[pi] > weight[qi]) {
                    weight[pi] += weight[qi];
                    id[qi] = pi;
                }
                else {
                    weight[qi] += weight[pi];
                    id[pi] = qi;
                }
            }
        }
        return n;
    }
    public int find(int[] id, int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
}
