package Leetcode;

public class P0367_ValidPerfectSquare {


    // initial approach: idea inspired by P0069(sqrtX)

    public boolean isPerfectSquare(int num) {
        long start = 1, end = num;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            if (mid * mid == num) return true;
            else if (mid * mid > num) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        // checked each number between 1 and num, so return false if not found
        return false;
    }
}
