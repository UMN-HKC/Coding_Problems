package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class P1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    // approach 1: sliding window + pq
    // The basic idea is to use a min heap and a max heap to maintain the minimum
    // and the maximum elements that have appeared so far. When their absolute
    // difference is within the limit, we can continue span the right pointer
    // and update result. But if it exceeds the limit, we need to move left
    // pointer to exclude numbers and update min and max heaps.

    // time: O(nlogn)
    // space: O(n)

    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
        int l = 0, r = 0, res = 0;
        while (r < n) {
            min.offer(nums[r]);
            max.offer(nums[r]);
            r++;
            while (Math.abs(max.peek() - min.peek()) > limit) {
                max.remove(nums[l]);
                min.remove(nums[l]);
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    // approach 2: sliding window + deque

    // Similar to sliding window + pq approach, but instead of maintaining min and
    // max values using heap. We use two double-ended queue. For a max deque, it will
    // maintain a non-increasing sequence of values, while for a min deque, it will
    // maintain a non-decreasing sequence of values. Each time we add a new value,
    // we also need to update max and min deques, and before we add this number
    // to the deque, we remove all the numbers on the tail that are smaller than this number
    // in the max deque, and all the numbers on the tail that are greater than this number
    // in the min deque because they will not be useful since we already have a greater value
    // in max deque, and a smaller value in min deque.

    // time: O(n)
    // space: O(n)

    public int longestSubarray_2(int[] nums, int limit) {
        int n = nums.length;
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int res = 0;
        int l = 0, r = 0;
        while (r < n) {
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[r]) maxDeque.pollLast();
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[r]) minDeque.pollLast();
            maxDeque.offer(nums[r]);
            minDeque.offer(nums[r]);
            r++;
            while (!maxDeque.isEmpty() && !minDeque.isEmpty() && maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (nums[l] == maxDeque.peekFirst()) maxDeque.pollFirst();
                if (nums[l] == minDeque.peekFirst()) minDeque.pollFirst();
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
