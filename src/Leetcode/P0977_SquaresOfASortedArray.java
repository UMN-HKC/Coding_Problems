package Leetcode;

public class P0977_SquaresOfASortedArray {

    // approach 1: two pointers
    // The idea is to use two pointers and add number with greater absolute value to the
    // result array.

    public int[] sortedSquares(int[] A) {
        int[] res = new int[A.length];
        int l = 0, r = A.length - 1;
        int p = res.length - 1;
        while (l <= r) {
            if (Math.abs(A[l]) >= Math.abs(A[r])) {
                res[p] = A[l] * A[l];
                l++;
            }
            else {
                res[p] = A[r] * A[r];
                r--;
            }
            p--;
        }
        return res;
    }
}
