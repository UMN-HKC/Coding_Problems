package Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P1365_HowManyNumbersAreSmallerThantheCurrentNumber {

    // approach 1: sort
    // The basic idea is to have a list of index and value pairs, sort them in terms
    // of its value. Then linear scan the list and use lastIdx and lastVal
    // variables to help record how many values are smaller than the current one.

    // time: O(nlogn)
    // space: O(n)

    public int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new int[]{i, nums[i]});
        }
        Collections.sort(list, (a, b) -> a[1] - b[1]);
        int lastIdx = 0;
        int lastVal = list.get(0)[1];
        for (int i = 0; i < list.size(); i++) {
            int[] pair = list.get(i);
            if (pair[1] != lastVal) {
                lastIdx = i;
                lastVal = pair[1];
            }
            nums[pair[0]] = lastIdx;

        }
        return nums;
    }

    // approach 2: counting sort

    // Store the count in a bucket and take the running sum.

    // time: O(n)
    // space: O(n)

    public int[] smallerNumbersThanCurrent_2(int[] nums) {
        int[] count = new int[101];
        int[] res = new int[nums.length];

        for (int i =0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        for (int i = 1 ; i <= 100; i++) {
            count[i] += count[i-1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                res[i] = 0;
            else
                res[i] = count[nums[i] - 1];
        }

        return res;
    }

}
