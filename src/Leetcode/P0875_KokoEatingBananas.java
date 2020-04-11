package Leetcode;

public class P0875_KokoEatingBananas {

    // approach 1: binary search

    // The key is to realize that the maximum speed to eat is the maximum number in
    // the array because each time Koko could only eat at most 1 pile, there's no need
    // to eat go faster than that. The minimum number of bananas Koko eat is 1. So the
    // answer must lie within this range. We could apply binary search to find the answer.
    // each time pick a middle speed within the range, if this speed could not make
    // Koko eat up all bananas, we will move our lower bound to mid + 1. Otherwise,
    // move upper bound to mid, because mid is a potential speed.

    // note that to calculate count each time, we need to do an arithmetic trick using
    // modulo to get number of hours spent on each pile.

    public int minEatingSpeed(int[] piles, int H) {
        int max = 0;
        for (int num : piles) max = Math.max(max, num);
        int l = 1, r = max;
        while (l < r) {
            int m = l + (r - l) / 2;
            int cnt = calculateCount(piles, m);
            if (cnt <= H) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }
    private int calculateCount(int[] piles, int k) {
        int cnt = 0;
        for (int i = 0; i < piles.length; i++) {
            cnt += piles[i] / k + (piles[i] % k == 0 ? 0 : 1);
        }
        return cnt;
    }
}
