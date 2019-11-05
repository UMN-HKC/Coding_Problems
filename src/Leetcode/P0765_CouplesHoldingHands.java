package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0765_CouplesHoldingHands {

    // approach 1: greedy

    public int minSwapsCouples(int[] row) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            map.put(row[i], i);
        }
        int cnt = 0;
        for (int i = 0; i < row.length - 1; i += 2) {
            int cur = row[i];
            int next = row[i + 1];
            if (cur % 2 == 0 && next != cur + 1) {
                swap(map, row, i + 1, map.get(cur + 1));
                cnt++;
            }
            else if (cur % 2 != 0 && next != cur - 1){
                swap(map, row, i + 1, map.get(cur - 1));
                cnt++;
            }
        }
        return cnt;
    }
    public void swap(Map<Integer, Integer> map, int[] row, int i, int j) {
        int temp = row[i];
        row[i] = row[j];
        row[j] = temp;
        map.put(row[j], j);
        map.put(row[i], i);
    }

    // approach 2: Union find
    // link: https://massivealgorithms.blogspot.com/2018/12/leetcode-765-couples-holding-hands.html

    class UF {
        int[] id;
        int size;
        public UF(int n) {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            size = n;
        }
        public void union(int p, int q) {
            int pp = find(p);
            int pq = find(q);
            if (pp == pq) return;
            id[pp] = pq;
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
    public int minSwapsCouples_2(int[] row) {
        int len = row.length / 2;
        UF uf = new UF(len);
        for (int i = 0; i < row.length - 1; i += 2) {
            uf.union(row[i] / 2, row[i + 1] / 2);
        }
        return row.length / 2 - uf.size;
    }

}
