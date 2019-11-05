package Leetcode;

import java.util.*;

public class P1197_MinimumKnightMoves {

    // approach 1: bi-directional bfs (slow but pass OJ) (normal bfs cannot pass)
    // I cannot persuade myself or find any explanation that proves why symmetric property
    // works. So maybe during an interview I will just give the bi-directional bfs which
    // sounds more reasonable to me. It passes OJ but it's just kinda slow.

    private static int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) return 0;
        Set<String> visited = new HashSet<>();
        String target = x + ":" + y;
        String source = 0 + ":" + 0;
        visited.add(source);
        visited.add(target);
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add(source);
        set2.add(target);
        int step = 0;
        while (set1.size() != 0 && set2.size() != 0) {
            step++;
            // start bfs on the smaller size set
            Set<String> set3 = new HashSet<>();
            if (set2.size() < set1.size()) {
                Set<String> temp = set1;
                set1 = set2;
                set2 = temp;
            }
            for (String pos : set1) {
                String[] cur = pos.split(":");
                int i = Integer.parseInt(cur[0]);
                int j = Integer.parseInt(cur[1]);
                for (int[] dir : dirs) {
                    int a = i + dir[0];
                    int b = j + dir[1];
                    if (Math.abs(a) + Math.abs(b) > 300) continue;
                    String next = a + ":" + b;
                    if (set2.contains(next)) return step;
                    if (visited.contains(next)) continue;
                    set3.add(next);
                    visited.add(next);
                }
            }
            set1 = set3;
        }
        return -1;
    }
}
