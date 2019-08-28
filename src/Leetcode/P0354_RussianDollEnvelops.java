package Leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class P0354_RussianDollEnvelops {

    // approach 1: DP
    // dp[i]: represents the maximum number of envelopes from envelopes[0:i-1]
    // that can be russian dolled into envelop[i].
    // So, we must first sort the envelopes based on either their width or height, in
    // our case, we sort the envelopes by both but it does not matter. The reason that
    // we want to sort the envelopes first is because dp[i] depends on its previous
    // result, if we dont sort the envelopes first, some valid envelopes may
    // be behind envelops[i].

    // time: O(nlogn)

    public int maxEnvelopes_dp(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            }
        });
        int m = envelopes.length;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max == Integer.MIN_VALUE ? 1 : max;
    }

    // approach 2: Binary search
    // The key point is to realize that this problem is similar to lc300
    // finding the longest increasing subsequence.

    // The idea is to sort the envelopes based on their width first in increasing
    // order and based on their height in decreasing order when widths are same.
    // Now, the question is transformed to find the longest increasing sequence
    // based on their height.
    // for example, [3, 4] cannot contains [3, 3], so we need to put [3, 4]
    // before [3, 3] when sorting otherwise it will be counted as an increasing
    // number if the order is [3, 3], [3, 4]

    // time: O(nlogn)

    public int maxEnvelopes_binarysearch(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) return b[1] - a[1];
                return a[0] - b[0];
            }
        });
        int m = envelopes.length;
        int[] tail = new int[m];
        int len = -1;
        for (int[] envelop : envelopes) {
            if (len == -1 || envelop[1] > tail[len]) {
                tail[++len] = envelop[1];
            }
            else if (envelop[1] < tail[0]) {
                tail[0] = envelop[1];
            }
            else {
                int s = 0, e = len - 1;
                while (s <= e) {
                    int mid = s + (e - s) / 2;
                    if (tail[mid] == envelop[1]) {
                        s = mid;
                        break;
                    }
                    else if (tail[mid] > envelop[1]) {
                        e = mid - 1;
                    }
                    else {
                        s = mid + 1;
                    }
                }
                tail[s] = envelop[1];
            }
        }
        return len + 1;
    }
}
