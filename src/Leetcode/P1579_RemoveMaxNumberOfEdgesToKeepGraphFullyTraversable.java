package Leetcode;

import java.util.Arrays;

public class P1579_RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

    // approach 1: UF

    // The idea here is to think that initially the graph is empty and now we want to add the edges into the graph such that graph is connected.
    // Union-Find is an easiest way to solve such problem where we start with all nodes in separate components and merge the nodes as we add edges into the graph.
    // As some edges are available to only Bob while some are available only to Alice, we will have two different union find objects to take care of their own traversability.
    // Key thing to remember is that we should prioritize type 3 edges over type 1 and 2 because they help both of them at the same time.

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] bob = new int[n + 1];
        int[] alice = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bob[i] = i;
            alice[i] = i;
        }
        int cnt = 0;
        Arrays.sort(edges, (a, b) -> b[0] - a[0]);
        for (int[] edge : edges) {
            int type = edge[0];
            int p = edge[1];
            int q = edge[2];
            // check connectivity
            int ppAlice = find(p, alice);
            int pqAlice = find(q, alice);
            int ppBob = find(p, bob);
            int pqBob = find(q, bob);
            if (ppAlice == pqAlice && ppBob == pqBob) {
                cnt++;
            }
            else {
                if (type == 3) {
                    bob[ppBob] = pqBob;
                    alice[ppAlice] = pqAlice;
                    n--;
                }
                else if (type == 2) {
                    if (ppBob == pqBob) {
                        cnt++;
                    }
                    else {
                        bob[ppBob] = pqBob;
                        n -= ppAlice == pqAlice ? 1 : 0;
                    }
                }
                else {
                    if (ppAlice == pqAlice){
                        cnt++;
                    }
                    else {
                        alice[ppAlice] = pqAlice;
                        n -= ppBob == pqBob ? 1 : 0;
                    }
                }

            }
        }
        return n == 1 ? cnt : -1;
    }
    private int find(int p, int[] ids) {
        while (ids[p] != p) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }
}
