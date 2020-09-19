package Leetcode;

public class P0685_RedundantConnectionII {

    // approach 1: UF
    // really good explanation in Chinese:
    // https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic

    // 1. union find过程中有发现入度为2的node
    //  1.1: 如果在过程中跳过合并之后还有遇到环（lastEdgeCauseCycle != null），返回edge1
    //  1.2: 如果在过程中跳过合并之后没有环， 返回edge2
    // 2. union find过程中没有发现入度为2的node，说明我们的root也在环内，这时候返回环内的任何一条edge
    //    都行，所以我们就返回lastEdgeCauseCycle。

    private int[] parent;
    private int[] ids;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        ids = new int[n + 1];
        int[] firstEdge = null;
        int[] secondEdge = null;
        int[] lastEdgeCausingCycle = null;

        for (int i = 0; i < n; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            if (ids[from] == 0) {
                ids[from] = from;
            }
            if (ids[to] == 0) {
                ids[to] = to;
            }
            // this node has 2 parents
            if (parent[to] != 0) {
                firstEdge = new int[2];
                firstEdge[0] = parent[to];
                firstEdge[1] = to;
                secondEdge = edges[i];
            }
            else {
                parent[to] = from;
                int pFrom = find(from);
                int pTo = find(to);
                if (pFrom == pTo) {
                    lastEdgeCausingCycle = edges[i];
                }
                else {
                    ids[pFrom] = pTo;
                }
            }
        }
        if (firstEdge != null) {
            return lastEdgeCausingCycle == null ? secondEdge : firstEdge;
        }
        else {
            return lastEdgeCausingCycle;
        }
    }
    private int find(int p) {
        while (ids[p] != p) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }

}
