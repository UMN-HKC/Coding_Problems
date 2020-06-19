package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0368_LargestDivisibleSubset {

    // approach 1: similar to LIS
    
    // https://www.youtube.com/watch?v=Wv6DlL0Sawg&feature=emb_title

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 && 1 + dp[j] > dp[i]) {
                    dp[i] = 1 + dp[j];
                    if (dp[i] > max) {
                        max = dp[i];
                    }

                }
            }
        }
        int prev = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (dp[i] == max && (prev % nums[i] == 0 || prev == -1)) {
                res.add(nums[i]);
                max--;
                prev = nums[i];
            }
        }
        return res;
    }
}
