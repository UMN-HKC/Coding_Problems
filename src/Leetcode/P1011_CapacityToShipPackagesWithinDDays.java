package Leetcode;

public class P1011_CapacityToShipPackagesWithinDDays {

    // approach 1: binary search
    // similar to lc.410
    // The basic idea is to binary search on the range of least capacity and greatest capacity
    // and adjust search range based on the number of days we needed. Note that when reducing
    // higher end range, we set r = m since in our implementation, m might be the answer

    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            max = Math.max(max, weights[i]);
        }
        int lo = max, hi = sum;

        while (lo < hi) {
            int m = lo + (hi - lo) / 2;
            if (satisfy(weights, m, D)) {
                hi = m;
            }
            else {
                lo = m + 1;
            }

        }
        return lo;
    }
    public boolean satisfy(int[] weights, int cap, int D) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > cap) {
                sum = weights[i];
                cnt++;
            }
        }
        return cnt <= D;
    }
}
