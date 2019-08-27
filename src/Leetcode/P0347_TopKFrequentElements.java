package Leetcode;

import java.util.*;

public class P0347_TopKFrequentElements {

    // approach 1: priority queue

    // time: O(nlogk) worst case when k == n -> O(nlogn)

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new myComparator());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<Integer> list = new ArrayList<>();
        while (pq.size() != 0) list.add(pq.poll().getKey());
        return list;
    }
    class myComparator implements Comparator<Map.Entry<Integer, Integer>> {
        public int compare(Map.Entry<Integer, Integer> num1, Map.Entry<Integer, Integer> num2) {
            return num1.getValue() - num2.getValue();
        }
    }

    // approach 2: bucket sort
    // use hash map to get the frequency and use bucket to store the keys
    // to their respective frequency bucket.

    // time: O(n)
    
    public List<Integer> topKFrequent_2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        int m = nums.length;
        List<Integer>[] bucket = new ArrayList[m+1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = m; i >= 1 && res.size() < k; i--) {
            if (bucket[i] != null) {
                for (int j = 0; j < bucket[i].size() && res.size() < k; j++) {
                    res.add(bucket[i].get(j));
                }
            }
        }
        return res;
    }
}
