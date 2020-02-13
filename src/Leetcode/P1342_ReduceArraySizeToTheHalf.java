package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class P1342_ReduceArraySizeToTheHalf {

    // approach 1: sort
    // The basic idea is to count frequency of each number and store pair to a max heap.
    // Next step is to greedily reduce max frequency from max heap until the sum of the rest
    // frequency is less or equal to half of its original size.

    // time: O(nlogn)
    // space: O(n)

    public int minSetSize(int[] arr) {
        int n = arr.length;
        int rest = n;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        int cnt = 0;
        while (rest > (double)n/2) {
            cnt++;
            rest -= pq.poll().getValue();
        }
        return cnt;
    }

    // approach 2:
    // The idea is to store numbers to their respective frequency slot and then iterate from the back of
    // frequency slots and the idea is similar to the approach 1. The speed is faster with the tradeoff in space

    // time: O(n)
    // space: O(n)

    public int minSetSize(int[] arr) {
        int n = arr.length;
        int rest = n;
        List<Integer>[] list = new ArrayList[n + 1];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> pair : freqMap.entrySet()) {
            int num = pair.getKey();
            int freq = pair.getValue();
            if (list[freq] == null) {
                list[freq] = new ArrayList<>();
            }
            list[freq].add(num);
        }
        int cnt = 0;
        for (int i = list.length - 1; i > 0 && rest > (double)n / 2; i--) {
            if (list[i] != null) {
                for (int j = 0; j < list[i].size() && rest > (double)n / 2; j++) {
                    rest -= i;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
