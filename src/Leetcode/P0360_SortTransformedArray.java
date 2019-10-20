package Leetcode;

public class P0360_SortTransformedArray {

    // approach 1: two pointers
    // The basic idea is to visualize the problem as a parabola and divide the
    // problem to two cases: a >= 0 or a < 0 and move two pointers accordingly

    // time: O(n)

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int start = 0;
        int end = res.length - 1;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int right = calculate(nums[r], a, b, c);
            int left = calculate(nums[l], a, b, c);
            // handles a == 0 as well
            if (a >= 0) {
                if (left <= right) {
                    res[end--] = right;
                    r--;
                }
                else {
                    res[end--] = left;
                    l++;
                }
            }
            else {
                if (left <= right) {
                    res[start++] = left;
                    l++;
                }
                else {
                    res[start++] = right;
                    r--;
                }
            }
        }

        return res;
    }
    public int calculate(int num, int a, int b, int c) {
        return a * num * num + b * num + c;
    }
}
