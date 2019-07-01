package Leetcode;

public class P0042_TrappingRainWater {

    // couldn't figure out initially, idea borrowed from basketwangcoding:
    // https://www.youtube.com/watch?v=8BHqSdwyODs

    // use two pointers and as we shrink two pointers, we accumulate the trapped water into our result.
    // The key is to have leftMax and rightMax variables to keep track of max of two sides that could
    // capture water between, and the smaller between these two is the one that determines the actual water
    // can be captured.

    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int leftMax = 0, rightMax = 0, i = 0, j = height.length - 1, res = 0;

        while (i < j) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            }
            if (height[j] > rightMax) {
                rightMax = height[j];
            }
            if (leftMax <= rightMax) {
                res += leftMax - height[i];
                i++;
            }
            else {
                res += rightMax - height[j];
                j--;
            }
        }
        return res;
    }
}
