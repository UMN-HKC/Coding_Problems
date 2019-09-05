package Leetcode;
import java.util.*;

public class P0215_KthLargestElementInAnArray {

    // approach 1: min heap

    // time: O(nlogk) since we keep the heap of size k
    // space: O(k)

    public int findKthLargest_min_heap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(((a, b) -> a - b));
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.poll();
    }

    // approach 2: quick select

    // time: best O(n) worst O(n^2)
    // space: O(1)

    // The basic idea is to utilize the partition process to help us
    // eliminate some extra computation. Basically, within each partition
    // process, we pick a pivot and then partition the array around the
    // pivot and return the ending index where all elements on the left are
    // smaller than the element at this index, and all elements on the right
    // are greater than the element at this index. Then, in our main function,
    // we can determine which part to search for our kth largest element.

    public int findKthLargest(int[] nums, int k) {
        int m = nums.length;
        k = m - k;
        int l = 0, r = m - 1;
        while (l < r) {
            int p = partition(nums, l, r);
            if (p == k) {
                return nums[p];
            }
            else if (p < k) {
                l = p + 1;
                p = partition(nums, l, r);
            }
            else {
                r = p - 1;
                p = partition(nums, l, r);
            }
        }
        return nums[l];
    }
    public int partition(int[] nums, int l, int r) {
        // randomize pivot pick process which significantly
        // improves the actual runtime
        Random rd = new Random();
        int pivotIdx = l + rd.nextInt(r-l+1);
        swap(nums, r, pivotIdx);

        int pivot = nums[r];
        for (int i = l; i < r; i++) {
            if (nums[i] <= pivot) {
                swap(nums,i,l);
                l++;
            }
        }
        swap(nums, l, r);
        return l;
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
