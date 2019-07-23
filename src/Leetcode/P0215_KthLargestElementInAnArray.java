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

    public int findKthLargest_quick_select(int[] nums, int k) {
        k = nums.length - k;
        int l = 0, r = nums.length - 1;
        int pivot = -1;
        while (true) {
            pivot = partition(nums, l, r);
            if (pivot == k) {
                return nums[k];
            }
            else if (pivot < k) {
                l = pivot + 1;
                pivot = partition(nums, l, r);
            }
            else {
                r = pivot - 1;
                pivot = partition(nums, l, r);
            }
        }
    }
    public int partition(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }
        int pivot = nums[r];
        int i = -1, j = l, k = r;
        // i: tail of elements that are smaller than the pivot element
        // k: head of elements that are greater than the pivot element
        while (j < k) {
            if (nums[j] < pivot) {
                i++;
                j++;
            }
            else {
                k--;
                swap(nums, j, k);
            }
        }
        swap(nums, j, r);
        return j;

    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
