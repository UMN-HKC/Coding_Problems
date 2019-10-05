package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0229_MajorityElementII {

    // approach 1: Boyer-Moore voting

    // The basic idea is that we start with two random candidate numbers and two counters set to 0
    // During the first iteration, if the current number is one of the candidate numbers, we increment
    // the respective counter, if neither, we decrement both counters. Once the counter reaches 0,
    // the next number we encounter would be the next candidate.
    // For the second iteration, we will check if these two candidates are real majority number.

    // time: O(n)
    // space: O(1)

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
