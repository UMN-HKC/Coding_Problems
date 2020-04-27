package Leetcode;

import java.util.*;

public class P1168_OptimizeWaterDistributionInAVillage {

    // approach 1: Minimum Spanning Tree (Kruskal's algorithm)

    // The basic idea is to model villages and pipes as vertices and edges in an undirected graph
    // Note that we will have to add a virtual node to extract the cost for building a well inside
    // a village to act as an edge to the virtual node. Thus, we will have a complete undirected graph.
    // Then, add all the edges to the heap and then sort the edge by its weight in increasing order.
    // Next, try to connect the two nodes of each edge, if they will not cause a cycle, union them
    // and add the cost

    // time: O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. After sorting,
    // we iterate through all edges and apply find-union algorithm. The find and union
    // operations can take atmost O(LogV) time. So overall complexity is O(ELogE + ELogV) time.
    // space: O(E + V)

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

        int[] ids = new int[n + 1];
        for (int i = 0; i < ids.length; i++) ids[i] = i;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{wells[i], 0, i + 1});
        }
        for (int[] pipe : pipes) {
            pq.offer(new int[]{pipe[2], pipe[0], pipe[1]});
        }
        int cost = 0;
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            if (find(ids, edge[1]) != find(ids, edge[2])) {
                cost += edge[0];
                union(ids, edge[1], edge[2]);
            }
        }
        return cost;

    }
    private void union(int[] ids, int p, int q) {
        int pp = find(ids, p);
        int pq = find(ids, q);
        if (pp != pq) {
            ids[pp] = pq;
        }
    }
    private int find(int[] ids, int p) {
        while (ids[p] != p) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }
}
