package Leetcode;

import java.util.*;

public class P0692_TopKFrequentWords {

    // approach 1: heap
    // similar to top k frequent elements (heap approach) but be careful of the
    // compare function. When adding to the heap, keep alphabetically greater strings
    // on the top so that we don't lose alphabetically smaller strings when discarding
    // less frequent strings

    class myComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
            return a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue();
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new myComparator());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // note that we use a linkedlist structure so that we could add to the front
        // to achieve alphabetically smaller strings being in front because strings
        // on the top of the heap are alphabetically greater(when we pop, we want to pop them).
        LinkedList<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.addFirst(pq.poll().getKey());
        }

        return res;
    }
}
