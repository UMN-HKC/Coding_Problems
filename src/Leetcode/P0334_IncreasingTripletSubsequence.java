package Leetcode;

public class P0334_IncreasingTripletSubsequence {

    // approach 1:
    // its generalization is p0300(longest increasing subsequence), but here
    // we only want the length of the sequence to be 3

    // the idea for this problem is that, we will keep two smallest elements, as we iterate through
    // the array, we first check if this element is smaller than our smallest element, if it is, we
    // update it to be even smaller; if it is smaller than our second smallest element which is on
    // the right of the smallest element, we update the second smallest element to be even smaller.
    // But once we find an element that is greater than these two elements, we have found a triplet.

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= left) {
                left = num;
            }
            else if (num <= right) {
                right = num;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
