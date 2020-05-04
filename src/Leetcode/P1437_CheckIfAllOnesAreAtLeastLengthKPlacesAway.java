package Leetcode;

public class P1437_CheckIfAllOnesAreAtLeastLengthKPlacesAway {

    // approach 1:

    public boolean kLengthApart(int[] nums, int k) {
        if (k == 0) return true;
        int lastPos = -k-1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i - lastPos < k + 1) {
                    return false;
                }
                lastPos = i;
            }
        }
        return true;
    }
}
