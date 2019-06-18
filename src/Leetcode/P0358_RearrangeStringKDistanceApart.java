package Leetcode;
import java.util.*;

public class P0358_RearrangeStringKDistanceApart {

    // couldn't figure out initially, kind of have the idea and related
    // to the similar question but was stuck at how to correctly append
    // characters. Idea borrowed from discussion board
    // basically, use a priorityqueue(max heap) to store

    public String rearrangeString(String s, int k) {
        if (s == null) return s;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        // use lambda expression instead of conventional way to build in comparator
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
//        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//            public int compare(int[] n1, int[] n2) {
//                return n2[1] - n1[1];
//            }
//        });

        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                pq.offer(new int[]{i, freq[i]});
            }
        }
        StringBuilder res = new StringBuilder();
        Queue<int[]> queue = new LinkedList<>();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            res.append((char)('a' + cur[0]));
            cur[1]--;
            queue.offer(cur);
            if (queue.size() >= k) {
                int[] temp = queue.poll();
                if (temp[1] != 0) {
                    pq.offer(temp);
                }
            }
        }
        return res.length() == s.length() ? res.toString() : "";
    }
}
