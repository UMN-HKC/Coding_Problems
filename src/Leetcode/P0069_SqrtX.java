package Leetcode;

public class P0069_SqrtX {

    // Initially stuck by the conventional binary search pattern, should've thought outside the box
    // idea borrowed from discussion board

    // time: O(logn)

    public int mySqrt(int x) {
        if (x < 1) return x;
        int start = 1, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // use 'x / mid' instead of 'x * x' to prevent integer overflow
            if (mid == x / mid) {
                return mid;
            }
            else if (mid > x / mid) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        // we want to get the lower bound value, so we return end
        return end;
    }
}
