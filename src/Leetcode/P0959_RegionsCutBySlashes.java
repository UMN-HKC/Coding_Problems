package Leetcode;

public class P0959_RegionsCutBySlashes {

    // approach 1: union find
    // link: https://www.youtube.com/watch?v=n3s9Q7GtfB4&t=292s
    // The basic idea is to visualize the string array as a grid, and cut each character
    // into 4 even smaller sub cells. For more visualization, check out the youtube link.
    // Then, we can apply union find technique to solve the problem. Initialize the
    // UF data structure with size of (n * n * 4) and then start iterating the string
    // array character by character, for each character which actually contains 4 sub cells
    // we will merge sub cells accordingly. After that, we will merge sub cell 1 with its
    // right character's sub cell 3, and merge sub cell 2 with its bottom character's sub
    // cell 0.

    class UF {
        int[] id;
        int[] weight;
        int size;
        public UF(int n) {
            size = n;
            id = new int[n];
            weight = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
                weight[i] = 1;
            }
        }
        public void union(int p, int q) {
            int pp = find(p);
            int pq = find(q);
            if (pp == pq) return;
            if (weight[pp] < weight[pq]) {
                weight[pq] += weight[pp];
                id[pp] = pq;
            }
            else {
                weight[pq] += weight[pp];
                id[pq] = pp;
            }
            size--;
        }
        public int find(int p) {
            int pp = id[p];
            while (id[pp] != pp) {
                id[pp] = id[id[pp]];
                pp = id[pp];
            }
            return pp;
        }
    }
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        UF uf = new UF(len * len * 4);
        for (int i = 0; i < len; i++) {
            String row = grid[i];
            for (int j = 0; j < len; j++) {
                char c = row.charAt(j);
                if (c == ' ') {
                    uf.union(getIdx(i, j, len, 0), getIdx(i, j, len, 1));
                    uf.union(getIdx(i, j, len, 1), getIdx(i, j, len, 2));
                    uf.union(getIdx(i, j, len, 2), getIdx(i, j, len, 3));
                }
                else if (c == '/') {
                    uf.union(getIdx(i, j, len, 0), getIdx(i, j, len, 3));
                    uf.union(getIdx(i, j, len, 1), getIdx(i, j, len, 2));
                }
                else {
                    uf.union(getIdx(i, j, len, 0), getIdx(i, j, len, 1));
                    uf.union(getIdx(i, j, len, 2), getIdx(i, j, len, 3));
                }
                if (j < len - 1) uf.union(getIdx(i, j, len, 1), getIdx(i, j + 1, len, 3));
                if (i < len - 1) uf.union(getIdx(i, j, len, 2), getIdx(i + 1, j, len, 0));
            }
        }
        return uf.size;

    }
    public int getIdx(int i, int j, int col, int seq) {
        return i * 4 * col + j * 4 + seq;
    }
}
