package Leetcode;

import java.util.Arrays;

public class P1319_NumberOfOperationsToMakeNetworkConnected {

    // approach 1: union find
    // The key point is to realize that we do not need to figure out how exactly
    // the network is wired because of the following two constraints:
    // - There are no repeated connections.
    // - No two computers are connected by more than one cable
    // Thus, as long as we have at least n - 1 cables, we are able to connect all
    // computers in the network. The rest problem is to find out how many disconnected
    // networks are there, so we can use union find.

    // time: O(n)
    // space: O(n)

    class UF {
        int[] id;
        int[] weight;
        int size;
        public UF(int n) {
            id = new int[n];
            weight = new int[n];
            size = n;
            Arrays.fill(weight, 1);
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
        }
        private int find(int p) {
            while (p != id[p]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return p;
        }
        private void union(int p, int q) {
            int pp = find(p);
            int pq = find(q);
            if (pp == pq) return;
            if (weight[pp] >= weight[pq]) {
                id[pq] = pp;
                weight[pp] += weight[pq];
            }
            else {
                id[pp] = pq;
                weight[pq] += weight[pp];
            }
            size--;
        }
    }
    public int makeConnected(int n, int[][] connections) {
        UF uf = new UF(n);
        for (int[] connection : connections) {
            int p = connection[0];
            int q = connection[1];
            uf.union(p, q);
        }
        // System.out.println(connections.length);
        if (connections.length < n - 1) return -1;
        return uf.size - 1;
    }

    // more concise, without creating UF class and without weight compression

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        int[] ids = new int[n];
        for (int i = 0; i < ids.length; i++) ids[i] = i;
        for (int[] connection : connections) {
            int p1 = find(ids, connection[0]);
            int p2 = find(ids, connection[1]);
            if (p1 != p2) {
                ids[p1] = p2;
                n--;
            }
        }
        return n - 1;
    }
    private int find(int[] ids, int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }


}
