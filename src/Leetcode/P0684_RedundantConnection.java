package Leetcode;

public class P0684_RedundantConnection {

    // approach 1: UF
    // Time: O(N * α(N)) ≈ O (N), because O(α(N)) 􏶂􏶎≈ O(1)
    // Space: O(N)

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] id = new int[n + 1];
        for (int i = 1; i < id.length; i++) {
            id[i] = i;
        }
        for (int[] edge : edges) {
            int p = edge[0];
            int q = edge[1];
            int pp = find(id, p);
            int pq = find(id, q);
            if (pp == pq) {
                return edge;
            }
            else {
                id[pp] = pq;
            }

        }
        return new int[2];
    }
    public static int find(int[] id, int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
}
