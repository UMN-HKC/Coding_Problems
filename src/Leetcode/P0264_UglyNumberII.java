package Leetcode;

public class P0264_UglyNumberII {

    // approach 1: DP

//    def: Ugly numbers are numbers whose only prime factors are 2, 3 or 5

//    because every number can only be divided by 2, 3, 5, one way to look at
//    the sequence is to split the sequence to three groups as below:
//            (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
//            (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
//            (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
//    We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …)
//    multiply 2, 3, 5. Then we use similar merge method as merge sort, to get every ugly
//    number from the three subsequence. Every step we choose the smallest one, and move one step after.

    public int nthUglyNumber(int n) {
        int[] ugly = new int[n + 1];
        ugly[1] = 1;
        int idx2 = 1, idx3 = 1, idx5 = 1;
        int fac2 = 2, fac3 = 3, fac5 = 5;
        for (int i = 2; i <= n; i++) {
            ugly[i] = Math.min(fac2, Math.min(fac3, fac5));
            if (ugly[i] == fac2) fac2 = 2 * ugly[++idx2];
            if (ugly[i] == fac3) fac3 = 3 * ugly[++idx3];
            if (ugly[i] == fac5) fac5 = 5 * ugly[++idx5];
        }
        return ugly[n];
    }
}
