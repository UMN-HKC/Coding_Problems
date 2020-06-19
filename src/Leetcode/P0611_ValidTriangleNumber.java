package Leetcode;

import java.util.Arrays;

public class P0611_ValidTriangleNumber {

    // approach 1: brute force O(n^3)

    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = nums.length - 1; j > i + 1; j--) {
                for (int k = j - 1; k > i; k--) {
                    if (nums[i] + nums[k] > nums[j]) {
                        cnt++;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return cnt;
    }

    // approach 3: binary search O(n^2 * (logn))

    public int triangleNumber_2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int sumOfTwoSides = nums[i] + nums[j];
                int l = j + 1, r = n - 1;
                while (l <= r) {
                    int m = l + (r - l) / 2;
                    if (sumOfTwoSides > nums[m]) {
                        l = m + 1;
                    }
                    else {
                        r = m - 1;
                    }
                }
                res += r - j;
            }
        }
        return res;
    }


    // approach 2: O(n^2)

    // sort the array, fix end and use two pointers
    // when nums[l] + nums[r] > nums[i], cnt += r - l, (directly plus the range)
    // because index l points to the smallest element so all elements within l and
    // r will be valid sides to take

    public int triangleNumber_3(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int cnt = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    cnt += r - l;
                    r--;
                }
                else {
                    l++;
                }
            }
        }
        return cnt;
    }
}
