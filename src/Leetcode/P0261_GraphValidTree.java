package Leetcode;
import java.util.*;

public class P0261_GraphValidTree {

    // approach 1: dfs
    // the key idea is to build the graph and then check if the graph has cycle
    // remember to check if the graph is fully connected afterwards

    public boolean validTree_dfs(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        int[] visited = new int[n];
        buildGraph(graph, edges);
        if (hasCycle(graph, visited, 0)) {
            return false;
        }
        // check if fully connected
        for (int i = 0; i < n; i++) {
            if (visited[i] != 2) return false;
        }
        return true;
    }
    public boolean hasCycle(List<Integer>[] graph, int[] visited, int cur) {
        visited[cur] = 1;
        for (Integer child : graph[cur]) {
            if (visited[child] == 2) {
                return true;
            }
            else if (visited[child] == 1) continue;
            if (hasCycle(graph, visited, child)) return true;
        }
        visited[cur] = 2;
        return false;
    }
    public void buildGraph(List<Integer>[] graph, int[][] edges) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }

    // approach 2: bfs
    // similar to dfs approach in terms of building the graph
    // the key in bfs is to remove the back edge from child to parent
    // when visiting parent's child so we prevent revisiting the parent

    public boolean validTree_bfs(int n, int[][] edges) {
        Set<Integer>[] graph = new HashSet[n];
        boolean[] visited = new boolean[n];
        buildGraph(graph, edges);
        if (hasCycle(graph, visited)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
    public boolean hasCycle(Set<Integer>[] graph, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (visited[cur]) return true;
            visited[cur] = true;
            for (Integer child : graph[cur]) {
                graph[child].remove(cur);
                q.offer(child);
            }
        }
        return false;
    }
    public void buildGraph(Set<Integer>[] graph, int[][] edges) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }

    // approach 3: union find

    // time: O(n + e lg*n)
    class UF {
        int[] id, cnt;
        int size;
        public UF(int size) {
            this.size = size;
            id = new int[size];
            cnt = new int[size];
            for (int i = 0; i < size; i++) {
                id[i] = i;
                cnt[i] = 1;
            }
        }
        public void union(int p, int q) {
            int rp = root(p);
            int rq = root(q);
            if (cnt[rp] < cnt[rq]) {
                id[rp] = rq;
                cnt[rq] += cnt[rp];
            }
            else {
                id[rq] = rp;
                cnt[rp] += cnt[rq];
            }
            size--;
        }
        public boolean find(int p, int q) {
            return root(p) == root(q);
        }
        public int root(int p) {
            while(p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
    }
    public boolean validTree(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge : edges) {
            // cycle detected
            if (uf.find(edge[0], edge[1])) return false;
            uf.union(edge[0], edge[1]);
        }
        // check if it is a single tree
        return uf.size == 1;
    }
}
