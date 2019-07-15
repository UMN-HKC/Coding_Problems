package Leetcode;
import java.util.*;

public class P0239_SlidingWindowMaximum {

    // approach 1:

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // remove elements outside window from front(farthest)
            while (dq.size() > 0 && i - dq.peekFirst() + 1 > k) {
                dq.pollFirst();
            }
            // remove previous elements that are smaller than the coming in element
            // from back(closest), because they will not be potential max in the future
            while (dq.size() > 0 && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.addLast(i);
            if (i >= k - 1) {
                res[i - k + 1] = nums[dq.peekFirst()];
            }
        }
        return res;
    }
}
