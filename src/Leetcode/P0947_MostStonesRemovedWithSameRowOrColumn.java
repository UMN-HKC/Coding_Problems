package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P0947_MostStonesRemovedWithSameRowOrColumn {

    // approach 1: dfs
    // The basic idea is to realize the problem as finding the number of connected
    // components. The answer then would be the total number of stones minus the
    // number of connected components. We can use hashmap to represent the stones
    // and their relationships. Then simply traverse the graph and record the number
    // of connected components

    // time: O(n^2)

    public int removeStones(int[][] stones) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            int[] s1 = stones[i];
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < stones.length; j++) {
                if (i == j) continue;
                int[] s2 = stones[j];
                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    list.add(j);
                }
            }
            map.put(i, list);
        }
        int cnt = 0;
        boolean[] visited = new boolean[stones.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(map, visited, i);
            }
        }
        return stones.length - cnt;
    }
    public void dfs(Map<Integer, List<Integer>> map, boolean[] visited, int idx) {
        visited[idx] = true;
        for (Integer neighbor : map.get(idx)) {
            if (!visited[neighbor]) {
                dfs(map, visited, neighbor);
            }
        }
    }

    // approach 2: union find
    // same idea but applying union find. Note that in the union function, when
    // two nodes have same parent, we need directly return!

    // time: O(n^2)

    class UF {
        int n;
        int[] id;
        int[] weight;
        int size;
        public UF(int n) {
            this.n = n;
            id = new int[n];
            weight = new int[n];
            size = n;
            for (int i = 0; i < n; i++) {
                id[i] = i;
                weight[i] = 1;
            }
        }
        public int find(int p) {
            while (id[p] != p) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
        public void union(int p, int q) {
            int pp = find(p);
            int pq = find(q);
            if (pp == pq) return;
            if (weight[pp] >= weight[pq]) {
                weight[pp] += weight[pq];
                id[pq] = pp;
            }
            else {
                weight[pq] += weight[pp];
                id[pp] = pq;
            }
            size--;
        }
    }
    public int removeStones(int[][] stones) {
        int len = stones.length;
        UF uf = new UF(len);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    uf.union(i, j);
                }
            }
        }
        return len - uf.size;
    }
}
