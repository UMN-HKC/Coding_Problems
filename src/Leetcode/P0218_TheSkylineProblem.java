package Leetcode;

import java.util.*;

public class P0218_TheSkylineProblem {

    // https://www.youtube.com/watch?v=GSBLe8cKu0s&t=801s

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> events = new ArrayList<>();
        for (int[] building : buildings) {
            // negate the height of left('coming in') egde point
            events.add(new int[]{building[0], -building[2]});
            events.add(new int[]{building[1], building[2]});
        }

        // when a[0] != b[0], we sort by increasing location order
        // when a[0] == b[0] which means location overlaps, we have 3 edge cases to consider:
        //  1. one is 'coming in' and the other one is 'coming out', we want to place 'comming in' before 'coming out'
        //  2. both are 'comming in', we want to put the higher building before lower building
        //  3. both are 'coming out', conversely, we will want to put the lower building before the higher building
        // This explains why we write our comparator in the following manner which satisfies all these edge cases.

        Collections.sort(events, (a, b) -> (a[0] == b[0]) ? (a[1] - b[1]) : a[0] - b[0]);
        TreeMap<Integer, Integer> queue = new TreeMap<>(Collections.reverseOrder());
        queue.put(0, 1);
        int prevMax = 0;
        for (int[] event : events) {
            // this is left edge
            if (event[1] < 0) {
                if (!queue.containsKey(-event[1])) {
                    queue.put(-event[1], 0);
                }
                queue.put(-event[1], queue.get(-event[1]) + 1);
            }
            else {
                Integer cnt = queue.get(event[1]);
                if (cnt == 1) {
                    queue.remove(event[1]);
                }
                else {
                    queue.put(event[1], cnt - 1);
                }
            }
            // if max has been changed, we will put a new critical point into our result
            int curHeight = queue.firstKey();
            if (curHeight!= prevMax) {
                List<Integer> list = Arrays.asList(new Integer[]{event[0], curHeight});
                res.add(list);
                prevMax = curHeight;
            }
        }
        return res;
    }
}
