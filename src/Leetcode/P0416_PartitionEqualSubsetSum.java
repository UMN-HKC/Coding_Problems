package Leetcode;

import java.util.Arrays;

public class P0416_PartitionEqualSubsetSum {

    // approach 1: DP(0/1 knapsack)
    // credit to: https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation

    // The basic idea is that for each number, we can pick it or not. Let us assume
    // dp[i][j] means whether the specific sum j can be gotten from the first i numbers.
    // If we can pick such a series of numbers from 0-i whose sum is j, dp[i][j] is true,
    // otherwise it is false.

    // Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j],
    // which means if the first i-1 elements has made it to j, dp[i][j] would also make
    // it to j (we can just ignore nums[i]). If we pick nums[i]. dp[i][j] = dp[i-1][j-nums[i]],
    // which represents that j is composed of the current value nums[i] and the remaining
    // composed of other previous numbers. Thus, the transition function is
    // dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]

    // time: O(m * sum)
    // space: O(m * sum)

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[dp.length - 1][target];
    }

    // optimize space
    // time: O(m * sum)
    // space: O(sum)

    public boolean canPartition_2(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] pre = new boolean[target + 1];
        boolean[] cur = new boolean[target + 1];
        pre[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < cur.length; j++) {
                cur[j] = pre[j];
                if (j - nums[i] >= 0) {
                    cur[j] = cur[j] || pre[j - nums[i]];
                }
            }
            boolean[] temp = cur;
            cur = pre;
            pre = temp;
        }
        return pre[target];
    }

    // even more optimized space
    // inner loop goes backwards so we can do it without rolling dp arrays

    public boolean canPartition_3(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

}
