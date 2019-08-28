package Leetcode;

import java.util.*;

public class P0373_FindKPairsWithSmallestSum {

    // this problem is very similar to lc378 where we are searching in
    // a matrix where its rows and columns are sorted respectively

    // approach 1: brute force + priority queue
    // check all possible pairs and maintain the priority queue of size k
    // could be improved by approach 2 with only selecting k

    // time: O((m * n) * (logk))

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[0] + b[1] - a[0] - a[1];
            }
        });
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pq.offer(new int[]{nums1[i], nums2[j]});
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (pq.size() != 0) {
            int[] pair = pq.poll();
            res.add(Arrays.asList(new Integer[]{pair[0], pair[1]}));
        }
        return res;
    }

    // approach 2: priority queue

    // The basic idea is to use priority queue to store array of sum and its
    // respective indices of its components in nums1 and nums2 arrays.
    // First, we will push all sums of pairs from the first element in nums2
    // array and all elements in nums1 array to priority queue
    // Then, every time we pop a sum from pq, we push one new sum to pq
    // which comes from popped sum's bottom element. See the example below:
    
    //        1   7   11  nums1

    //   2   [3   9   13]   -> init pq with all these sums first

    //   4    5  11   15

    //   6    7  13   17
    //  num2

    // time: O(klogk)

    public List<List<Integer>> kSmallestPairs_2(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        // init priority queue with
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }
        while (k-- > 0 && pq.size() > 0) {
            int[] pair = pq.poll();
            res.add(Arrays.asList(new Integer[]{nums1[pair[0]], nums2[pair[1]]}));
            if (pair[1] + 1 >= nums2.length) {
                continue;
            }
            pq.offer(new int[]{pair[0], pair[1] + 1, nums1[pair[0]] + nums2[pair[1] + 1]});
        }
        return res;
    }
}
