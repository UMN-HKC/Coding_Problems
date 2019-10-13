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

    // approach 2: Stack (monotonic stack)
    // The idea is to maintain a monotonic stack. The stack will store the indices which corresponds
    // to their heights and should be in increasing order in terms of their heights as well. Each time
    // we encounter a rectangle that breaks the monotonic increasing height rule, we start pop
    // indices from the stack and meanwhile calculate and update max, until the height referenced by
    // the index at the top of stack is smaller than the current height, then we push the current
    // height's index to the stack. Thus, stack still maintains its monotonic property.

    // time: O(n)
    // space: O(n)

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        // push -1 to the stack so that we always have something in the stack
        // for us to calculate. But note that when popping, we need to check we do not pop -1
        stack.push(-1);
        int max = 0;
        int len = heights.length;
        for (int i = 0; i <= len; i++) {
            int cur = i == len ? 0 : heights[i];
            while (stack.peek() != -1 && heights[stack.peek()] >= cur) {
                max = Math.max(max, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return max;
    }
}
