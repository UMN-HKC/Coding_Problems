package Leetcode;

public class P0685_RedundantConnectionII {

    // approach 1: UF
    // really good explanation in Chinese:
    // https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] root = new int[n + 1];
        int[] uf = new int[n + 1];
        int[] edge1 = new int[2];
        int[] edge2 = new int[2];
        int[] edgeCauseCycle = new int[2];

        for (int i = 1; i < uf.length; i++) {
            uf[i] = i;
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (root[to] == 0) {
                root[to] = from;
                int pFrom = find(uf, from);
                int pTo = find(uf, to);
                if (pFrom == pTo) {
                    edgeCauseCycle = edge;
                }
                else {
                    uf[pFrom] = pTo;
                }
            }
            else {
                edge1[0] = root[to];
                edge1[1] = to;
                edge2 = edge;
            }
        }
        if (edge1[0] != 0 && edge2[0] != 0) return edgeCauseCycle[0] == 0 ? edge2 : edge1;
        return edgeCauseCycle;
    }
    public static int find(int[] uf, int p) {
        while (uf[p] != p) {
            uf[p] = uf[uf[p]];
            p = uf[p];
        }
        return p;
    }

}
