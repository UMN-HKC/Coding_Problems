package Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P1577_NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {

    // initial approach:

    public int numTriplets(int[] nums1, int[] nums2) {
        return find(nums1, nums2) + find(nums2, nums1);
    }
    private int find(int[] nums1, int[] nums2) {
        int res = 0;
        Arrays.sort(nums2);
        for (int i = 0; i < nums1.length; i++) {
            long product = (long)nums1[i] * (long)nums1[i];
            int l = 0;
            int r = nums2.length - 1;
            while (l < r) {
                // skip too large or too small product
                while (l < r && ((long)nums2[l] * (long)nums2[r] > product || (long)nums2[l] * (long)nums2[r] < product)) {
                    if ((long)nums2[l] * (long)nums2[r] > product) {
                        r--;
                    }
                    else {
                        l++;
                    }
                }
                // if there's answer
                if (l < r) {
                    // if left and right are same and all values between are same
                    if (nums2[l] == nums2[r]) {
                        int len = r - l;
                        while (len >= 1) {
                            res += len;
                            len--;
                        }
                        // have included all answers between, so we can break
                        break;
                    }
                    else {
                        int ll = l;
                        int rr = r;
                        while (nums2[ll + 1] == nums2[ll]) {
                            ll++;
                        }
                        while (nums2[rr - 1] == nums2[rr]) {
                            rr--;
                        }
                        res += (ll - l + 1) * (r - rr + 1);
                        l = ll + 1;
                        r = rr - 1;
                    }

                }
            }
        }
        return res;
    }

    // approach 2: hashmap, way cleaner

    public int numTriplets_2(int[] nums1, int[] nums2) {
        int res = 0;
        for (long num : nums1) {
            res += findNumOfProductEqualToTarget(num * num, nums2);
        }
        for (long num : nums2) {
            res += findNumOfProductEqualToTarget(num * num, nums1);
        }
        return res;
    }
    private int findNumOfProductEqualToTarget(long target, int[] arr) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (target % num == 0) {
                if (map.containsKey((int)(target / num))) {
                    res += map.get((int)(target / num));
                }
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return res;
    }
}
