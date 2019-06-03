package Leetcode;

public class P1064_FixedPoint {

    // initial approach: binary search

    // time: O(logn)
    // space: O(1)

    public int fixedPoint(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (A[mid] < 0 || A[mid] > mid) {
                r = mid - 1;
            }
            else if (A[mid] < mid) {
                l = mid + 1;
            }
            else if (A[mid] == mid) {
                return mid;
            }
        }
        return -1;
    }
}
