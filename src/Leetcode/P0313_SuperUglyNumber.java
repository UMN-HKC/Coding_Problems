package Leetcode;

public class P0313_SuperUglyNumber {

    // approach 1: DP

    public int nthSuperUglyNumber(int n, int[] primes) {
        // ugly[i] represents ith ugly number given prime list primes
        int[] ugly = new int[n];
        // idx array can be visualized as representing the positions in k
        // sorted list when merging them. Each time, we will
        int[] idx = new int[primes.length];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < idx.length; j++) {
                ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);
            }
            // skip duplicates
            for (int j = 0; j < idx.length; j++) {
                while (primes[j] * ugly[idx[j]] == ugly[i]) idx[j]++;
            }
        }
        return ugly[n - 1];
    }
}
