package Leetcode;

import java.util.PriorityQueue;

public class P0703_KthLargestElementInAStream {

    // approach 1:
    // The basic idea is to keep a min heap of size K, and the only thing we need to do is to
    // maintain the heap. The answer is always the top element in the heap which is the Kth
    // largest. Note that the initial array might be empty.

    PriorityQueue<Integer> pq;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        }
        else {
            pq.offer(val);
            pq.poll();
        }
        return pq.peek();
    }
}
