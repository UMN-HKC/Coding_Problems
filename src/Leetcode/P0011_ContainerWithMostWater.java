package Leetcode;

public class P0011_ContainerWithMostWater {

    // initial approach: brute force

    public int maxArea(int[] height) {
        int max = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, (r - l) * Math.min(height[l], height[r]));
            // same length does not influence most water that has been captured
            while (l < r && height[l] == height[r] && height[l] == height[l+1]) l++;
            while (l < r && height[l] == height[r] && height[r] == height[r-1]) r--;
            if (l < r) {
                if (height[l] < height[r]) l++;
                else r--;
            }
        }
        return max;
    }

    // approach 2: about same with the first approach, however it turns out that
    // when two pointers' heights are same, it does not matter which pointer we choose
    // to move, whichever we choose to move, the following situation(either left being higher
    // or right being higher) will be dealt with in next iteration.

    public int maxArea_simplified(int[] height) {
        int max = 0;
        int l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, (r - l) * Math.min(height[l], height[r]));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return max;
    }
}
