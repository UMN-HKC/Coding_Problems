package Leetcode;
import java.util.*;

public class P0084_LargestRectangleInHistogram {

    // approach 1: DP
    // iterate row by row and use two arrays lessFromLeft and lessFromRight to
    // record for the current height, the farthest distance this height can
    // extend to its left and right. So these two arrays store the left and right
    // indices respectively.
    // note that within each extending phase(see how far we can reach to the left and right),
    // we use a while loop(which is the essence of this solution, IMO) to calculate the
    // farthest the current height can reach to its left or right.

    // time: O(n)
    // for example in order to calculate left[i]; if height[i - 1] < height[i] then left[i] = i - 1;
    // otherwise we do not need to start scan from i - 1; we can start the scan from left[i - 1],
    // because since left[i - 1] is the first position to the left of i - 1 that have height less than
    // height[i - 1], and we know height[i - 1] >= height[i]; so left[i] must be at the left or at left[i - 1];
    // similar for the right array;

    // space: O(n)

    public int largestRectangleArea_dp(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int len = heights.length;
        int[] lessFromLeft = new int[len];
        int[] lessFromRight = new int[len];
        lessFromLeft[0] = -1;
        lessFromRight[len - 1] = len;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = len - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < len && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            // subtract 1 here to adjust to the correct length
            max = Math.max(max, (lessFromRight[i] - lessFromLeft[i] - 1) * heights[i]);
        }
        return max;
    }

    // approach 2: Stack

    // time: O(n)
    // space: O(n)

    public int largestRectangleArea_stack(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> s = new Stack<>();
        int max = 0;
        int i = 0;
        while (i <= heights.length) {
            int h = (i == heights.length ? 0 : heights[i]);
            if (s.empty() || h >= heights[s.peek()]) {
                s.push(i);
                i++;
            }
            else {
                int tp = s.pop();
                max = Math.max(max, heights[tp] * (i - 1 - (s.empty() ? -1 : s.peek())));
            }
        }
        return max;
    }
}
