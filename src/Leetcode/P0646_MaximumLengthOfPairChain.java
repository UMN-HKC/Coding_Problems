package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0646_MaximumLengthOfPairChain {

    // approach 1: greedy, O(n) space
    // The basic idea is that we sort the pairs in increasing order with numbers in pair
    // Then, we iterate them one by one with a starting pair as base. If the next pair
    // does not conflict with current pair, we add it to the list. Otherwise, we update
    // the current pair's right number to be the smaller value between itself and next pair's
    // right number.

    public int findLongestChain(int[][] pairs) {
        int len = pairs.length;
        if (len == 1) return 1;
        Arrays.sort(pairs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(pairs[0]);
        for (int i = 1; i < len; i++) {
            int[] last = res.get(res.size() - 1);
            int[] cur = pairs[i];
            if (cur[0] > last[1]) {
                res.add(cur);
            }
            else {
                last[1] = Math.min(cur[1], last[1]);
            }
        }
        return res.size();
    }

    // approach 2: greedy, O(1) space
    // Since we are only dealing with one pair before the next pair, we can use 1 variable
    // instead of a list to keep track of our last value.

    public int findLongestChain_2(int[][] pairs) {
        int len = pairs.length;
        if (len == 1) return 1;
        Arrays.sort(pairs, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int last = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int[] cur = pairs[i];
            if (cur[0] > last) {
                last = cur[1];
                res++;
            }
            else {
                last = Math.min(last, cur[1]);
            }
        }
        return res;
    }
}
