package Leetcode;

import java.util.*;

public class P0659_SplitArrayIntoConsecutiveSubsequences {

    // approach 1: hashmap (frequency map and hypothetical map
    // link: https://www.youtube.com/watch?v=uJ8BAQ8lASE
    // The basic idea is to use greedy thinking. We will use two maps: one map stores frequency
    // of each number and the other map(we call it hypothetical map) that stores the potential
    // next number of a chain of already seen contiguous numbers.
    // We will iterate through the num array once. Each time we see a number, there will be three
    // situations to consider:
    //  1. the frequency of this number is 0, we have used up this number, so skip it.
    //  2. the number exist in the hypothetical map, which means there's already a chain of already
    //     seen contiguous numbers. Thus, we update that chain of numbers to include this number
    //     and also put the next potential number to the hypothetical map
    //  3. the number does not exist in hypothetical map, so we need to create a new chain of numbers
    //     which starts with size of 3, given the following two contiguous numbers exist in frequency map.
    //  4. if none of above situations is met, return false;

    // time: O(n)
    // space: O(n)

    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> fMap = new HashMap<>();
        Map<Integer, Integer> hMap = new HashMap<>();
        for (int num : nums) {
            fMap.put(num, fMap.getOrDefault(num ,0) + 1);
        }
        for (int num : nums) {
            if (fMap.getOrDefault(num, 0) == 0) continue;
            else if (hMap.getOrDefault(num, 0) > 0) {
                hMap.put(num, hMap.get(num) - 1);
                hMap.put(num + 1, hMap.getOrDefault(num + 1, 0) + 1);
            }
            else if (fMap.getOrDefault(num + 1, 0) > 0 && fMap.getOrDefault(num + 2, 0) > 0) {
                fMap.put(num + 1, fMap.get(num + 1) - 1);
                fMap.put(num + 2, fMap.get(num + 2) - 1);
                hMap.put(num + 3, hMap.getOrDefault(num + 3, 0) + 1);
            }
            else {
                return false;
            }
            fMap.put(num, fMap.get(num) - 1);
        }
        return true;
    }
}
