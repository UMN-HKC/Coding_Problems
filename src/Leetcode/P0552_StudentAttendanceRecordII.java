package Leetcode;

public class P0552_StudentAttendanceRecordII {

    // approach 1: DP  (this approach is more interview friendly to me)

    // The basic idea is to first calculate how many combinations we have when only
    // using "P" and "L". Then, we insert "A" to our result and add all possible
    // number of combinations to our previous result.
    // Note that when inserting "A" to our previous result, we need to decrement
    // the length by 1, because we are inserting "A" which cost 1 position from n.

    // note that we initialize endingP[0] = 1 and not initialize endingL[0] = 1
    // because when inserting "A", we do not want inserting to first position will
    // result in two options as its left string. So we only need to initialize
    // one and we choose endingP[0] = 1 because it is used when we are building
    // two ending arrays.

    // also note that we need to use long type instead of integer type to prevent overflow

    public static final int mod = (int)Math.pow(10, 9) + 7;
    public int checkRecord(int n) {
        if (n == 0) return 0;
        // we first calculate all possible combinations without “A”
        long[] endingP = new long[n + 1];
        long[] endingL = new long[n + 1];

        endingP[0] = endingP[1] = endingL[1] = 1;
        for (int i = 2; i <= n; i++) {
            endingP[i] = (endingP[i - 1] + endingL[i - 1]) % mod;
            endingL[i] = (endingP[i - 1] + endingP[i - 2]) % mod;
        }
        long res = (endingP[n] + endingL[n]) % mod;
        for (int i = 0; i < n; i++) {
            long numFromInsertion = (((endingP[i] + endingL[i]) % mod) * ((endingP[n - i - 1] + endingL[n - i - 1]) % mod)) % mod;
            res = (res + numFromInsertion) % mod;
        }
        return (int)res;
    }
}
