package Leetcode;

import java.util.*;

public class P0451_SortCharactersByFrequency {

    // approach 1: map + priority queue

    public String frequencySort(String s) {

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            char c = entry.getKey();
            int cnt = entry.getValue();
            for (int i = 0; i < cnt; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    // approach 2: bucket sort

    public String frequencySort_1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character>[] bucket = new ArrayList[s.length() + 1];
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int freq = entry.getValue();
            char c = entry.getKey();
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = bucket.length - 1; i >= 1; i--) {
            if (bucket[i] != null) {
                for (char c : bucket[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }

}
