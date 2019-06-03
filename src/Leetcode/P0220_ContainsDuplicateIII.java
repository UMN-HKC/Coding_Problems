package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class P0220_ContainsDuplicateIII {

    // intial approach: brute force

    // time: O(n^2)
    // space: O(1)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i + 1; j < nums.length&&j<=i+k; j++) {
                if (Math.abs((long)nums[i] - (long)nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    // approach 2: We can improve runtime a little bit by using a binary search tree structure
    // where it is easier to check if we have a previously encountered element whose difference
    // with the current visited element is less or equal to t. In here, we take advantage of
    // the binary search tree structure, we can use treeset class provided by Java library

    public boolean containsNearbyAlmostDuplicate_treeset(int[] nums, int k, int t) {
        if (nums == null || k < 0 || t < 0) return false;

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // only check floor and ceiling
            Integer floor = set.floor(nums[i]);
            Integer ceiling = set.ceiling(nums[i]);
            if ((floor != null && (long)nums[i]-(long)floor <= t) ||
                    (ceiling != null && (long)ceiling - (long)nums[i] <= t)) {
                return true;
            }
            set.add(nums[i]);

            // W cannot use i > k because we always want to keep our tree size less or equal to k
            // If map size is of size k and we do not remove element at i - k in the array, we will
            // compare the next element with its previous k elements which exceeds the total k
            if (i >= k) {
                // only keep less or equal to k elements as our 'window' size
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    // approach 3: bucket sort
    // use map as our bucket and basically apply bucket sort
    // https://www.youtube.com/watch?v=yc4hCFzNNQc

    // time: O(n)
    // space: O(bucket_size)==O((max_num - min_num)/t) but with our optimization -> O(k)

    public boolean containsNearbyAlmostDuplicate_bucket_sort(int[] nums, int k, int t) {
        if (nums == null || k < 0 || t < 0) return false;

        Map<Long, Integer> map = new HashMap<>();
        Integer min = Integer.MAX_VALUE;
        // find the min number to do 'shifting', so min in the array will be mapped to bucket 1 and
        // all the other number will be mapped to bucket respective to the min element
        for (int num : nums) {
            min = Math.min(min, num);
        }
        for (int i = 0; i < nums.length; i++) {
            long bucket = ((long)nums[i] -min) / ((long)t + 1);
            // need to check neighbor buckets since nearby buckets may contain valid number
            if (map.containsKey(bucket) || (map.containsKey(bucket - 1) && Math.abs((long)map.get(bucket - 1)-nums[i]) <= t) ||
                    (map.containsKey(bucket + 1) && Math.abs((long)map.get(bucket + 1) - nums[i]) <= t)) {
                return true;
            }
            map.put(bucket, nums[i]);
            // optimize space usage
            if (i >= k) {
                map.remove(((long)nums[i-k] -min) / ((long)t + 1));
            }
        }
        return false;
    }
}
