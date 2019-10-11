package Leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P0554_BrickWall {

    // approach 1: hashmap
    // The key is to realize the answer is (the number of walls - max number of same edge)

    public int leastBricks(List<List<Integer>> wall) {
        int m = wall.size();
        int min = m;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = wall.get(i);
            int sum = 0;
            for (int j = 0; j < row.size() - 1; j++) {
                sum += row.get(j);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            min = Math.min(min, m - entry.getValue());
        }
        return min;
    }
}
