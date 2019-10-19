package Leetcode;

import java.util.Arrays;
import java.util.Stack;

public class P0503_NextGreaterElementII {

    // approach 1: brute force
    // for each element, traverse its next (len - 1) elements to find next greater element

    // time: O(n^2)
    // space: O(n)

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        int pos = 0;
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            for (int j = 1; j < len; j++) {
                if (nums[(i + j) % len] > cur) {
                    res[i] = nums[(i + j) % len];
                    break;
                }
            }
        }
        return res;
    }


    // approach 1: stack

    // The basic idea is to maintain a monotonic decreasing stack, but stack stores the
    // indices of the values. For example, in [6,4,3,2,1,5], [6,4,3,2,1] is decreasing and
    // 5 is the first next greater element for this decreasing sequence, so we are able to
    // update the result for all smaller previous elements, and stack enables us to retrieve
    // previous elements in a LIFO fashion. Note that, we only need to push the indices in the
    // first round (i < len) because those are all indices we want to find the next greater element for

    // time: O(n)
    // space: O(n)
    public int[] nextGreaterElements_2(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < 2 * len - 1; i++) {
            while (!s.empty() && nums[s.peek()] < nums[i % len]) {
                res[s.pop()] = nums[i % len];
            }
            if (i < len) {
                s.push(i);
            }
        }
        return res;
    }
}
