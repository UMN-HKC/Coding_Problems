package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class P0496_NextGreaterElementI {

    // approach 1: stack

    // The basic idea is to maintain a monotonic decreasing stack (decreasing nums1 elements)
    // note that our stack only stores elements in the nums1 array which is smaller than nums2 size
    // We keep a map to both store elements in nums1 array as well as mapping them to indices
    // Next, we iterate through nums2 array. Once we find a greater element then the element
    // at the top of the stack, we can start popping them and update result array accordingly.
    // Also, once we're done updating, we remove the respective elements in the map as well

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> s = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }
        Arrays.fill(nums1, -1);
        for (int i = 0; i < nums2.length; i++) {
            while (!s.empty() && s.peek() < nums2[i]) {
                int pre = s.pop();
                nums1[map.get(pre)] = nums2[i];
                map.remove(pre);
            }
            if (map.containsKey(nums2[i])) {
                s.push(nums2[i]);
            }
        }
        return nums1;
    }
}
