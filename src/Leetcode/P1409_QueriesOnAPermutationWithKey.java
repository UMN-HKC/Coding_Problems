package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P1409_QueriesOnAPermutationWithKey {

    // approach 1: brute force

    public int[] processQueries(int[] queries, int m) {

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int value = queries[i];
            int index = list.indexOf(value);
            res[i] = index;
            list.remove(index);
            list.add(0, value);
        }
        return res;
    }
}
