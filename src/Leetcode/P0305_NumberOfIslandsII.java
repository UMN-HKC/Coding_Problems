package Leetcode;
import java.util.*;

public class P0305_NumberOfIslandsII {

    // initial approach: UF with hashmap (accessing map is costly)
    // the basic idea is to union lands when new land's position is
    // connected to nearby lands. Optimize the weighted UF structure
    // with path compression

    // time: O(klog(m*n))

    int[][] dirs = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        Map<Integer, Map<Integer, int[]>> id = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> weight = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            // if this row not exist, init it
            if (!id.containsKey(x)) {
                id.put(x, new HashMap<>());
                weight.put(x, new HashMap<>());
            }
            // if this column of this row not exist, init it
            if (!id.get(x).containsKey(y)) {
                id.get(x).put(y, new int[]{x, y});
                weight.get(x).put(y, 1);
                cnt++;
            }
            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                if (newX >= 0 && newY >= 0 && newX < m && newY < n) {
                    if (id.containsKey(newX) && id.get(newX).containsKey(newY)) {
                        int[] root1 = find(id, newX, newY), root2 = find(id, x, y);
                        if (root1[0] == root2[0] && root1[1] == root2[1]) {
                            continue;
                        }
                        cnt--;
                        int weight1 = weight.get(newX).get(newY), weight2 = weight.get(x).get(y);
                        if (weight1 < weight2) {
                            id.get(root1[0]).put(root1[1], root2);
                            weight.get(root2[0]).put(root2[1], weight1 + weight2);
                        }
                        else {
                            id.get(root2[0]).put(root2[1], root1);
                            weight.get(root1[0]).put(root1[1], weight1 + weight2);
                        }
                    }
                }
            }
            res.add(cnt);
        }
        return res;
    }
    public int[] find(Map<Integer, Map<Integer, int[]>> id, int x, int y) {

        while ((id.get(x).get(y)[0] != x) || (id.get(x).get(y)[1] != y)) {
            int[] p = id.get(x).get(y);
            int[] pp = id.get(p[0]).get(p[1]);
            id.get(x).put(y, pp);
            x = p[0];
            y = p[1];
        }
        return new int[]{x, y};
    }

    // approach 2: UF, but use a linear array to replace map to reduce cost

    // time: O(klog(m*n))

    int[][] dirs = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] id = new int[m * n];
        int[] weight = new int[m * n];
        Arrays.fill(id, -1);
        Arrays.fill(weight, 0);
        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            int seq = x * n + y;
            if (id[seq] == -1) {
                cnt++;
                id[seq] = seq;
                weight[seq] = 1;
            }
            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                int newSeq = newX * n + newY;
                if (newX >= 0 && newY >= 0 && newX < m && newY < n && newSeq >= 0 && id[newSeq] != -1) {
                    int root1 = find(id, newSeq), root2 = find(id, seq);
                    if (root1 == root2) {
                        continue;
                    }
                    cnt--;
                    int weight1 = weight[root1], weight2 = weight[root2];
                    if (weight1 < weight2) {
                        id[root1] = id[root2];
                        weight[root2] += weight[root1];
                    }
                    else {
                        id[root2] = id[root1];
                        weight[root1] += weight[root2];
                    }
                }
            }
            res.add(cnt);
        }
        return res;
    }
    public int find(int[] id, int seq) {

        while (id[seq] != seq) {
            int p = id[id[seq]];
            id[seq] = p;
            seq = p;
        }
        return seq;
    }

    // uf with class definition

    class UF {
        int[] id, weight;
        int size;
        public UF(int n) {
            size = 0;
            id = new int[n];
            weight = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = -1;
                weight[i] = 0;
            }
        }
        public int find(int p) {
            while (id[p] != id[id[p]]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return id[p];
        }
        public void union(int p, int q) {
            int pp = id[p];
            int pq = id[q];
            if (weight[pp] < weight[pq]) {
                id[p] = pq;
                weight[pq] += weight[pp];
            }
            else {
                id[q] = pp;
                weight[pp] += weight[pq];
            }
            size--;
        }
    }
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        UF uf = new UF(m * n);
        for (int[] pos : positions) {
            int cur = pos[0] * n + pos[1];
            if (uf.id[cur] == -1) {
                uf.id[cur] = cur;
                uf.size++;
                for (int[] dir : dirs) {
                    int r = pos[0] + dir[0];
                    int c = pos[1] + dir[1];
                    if (r < 0 || c < 0 || r >= m || c >= n) continue;
                    int neighbor = r * n + c;
                    if (uf.id[neighbor] == -1) continue;
                    int p1 = uf.find(cur);
                    int p2 = uf.find(neighbor);
                    if (p1 != p2)
                        uf.union(uf.id[cur], p2);

                }
            }
            res.add(uf.size);
        }
        return res;
    }
}
