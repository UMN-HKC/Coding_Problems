package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0229_MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int num1 = 0;
        int num2 = 1;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int num : nums) {
            if (num1 == num) {
                cnt1++;
            }
            else if (num2 == num) {
                cnt2++;
            }
            else if (cnt1 == 0) {
                num1 = num;
                cnt1++;
            }
            else if (cnt2 == 0) {
                num2 = num;
                cnt2++;
            }
            else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == num1) {
                cnt1++;
            }
            if (num == num2) {
                cnt2++;
            }
        }
        if (cnt1 > nums.length / 3) res.add(num1);
        if (cnt2 > nums.length / 3) res.add(num2);
        return res;
    }
}
