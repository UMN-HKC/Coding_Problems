package Leetcode;

import java.util.Arrays;

public class P0164_MaximumGap {

    // approach 1: Bucket sort + Pigeon hole principle
    // Obviously we can brute force and sort the array but that cost O(nlogn) time.
    // Since we do not want to sort, we can use bucket to categorize values to
    // their respective buckets and then we only compare between buckets.

    // refer to: https://leetcode.com/articles/maximum-gap/

    // time: O(n)
    // space: O(n)

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int m = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int gap = Math.max(1, (max - min) / (m - 1));
        int size = (max - min) / gap + 1;
        int[] minBucket = new int[size];
        int[] maxBucket = new int[size];
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        for (int num : nums) {
            int idx = (num - min) / gap;
            minBucket[idx] = Math.min(minBucket[idx], num);
            maxBucket[idx] = Math.max(maxBucket[idx], num);
        }

        int prev = min;
        int res = 0;
        for (int i = 0; i < size; i++) {
            if (minBucket[i] == Integer.MAX_VALUE && maxBucket[i] == Integer.MIN_VALUE)
                continue;
            res = Math.max(res, minBucket[i] - prev);
            prev = maxBucket[i];
        }
        return res;
    }
}
