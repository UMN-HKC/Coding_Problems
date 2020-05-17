package Leetcode;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P1451_RearrangeWordsInASentence {

    // approach 1: sort

    // time: O(nlogn)

    public String arrangeWords(String text) {
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>((a, b)
                -> a.getKey().length() == b.getKey().length()
                ? a.getValue() - b.getValue()
                : a.getKey().length() - b.getKey().length());
        String[] words = text.toLowerCase().split(" ");
        for (int i = 0; i < words.length; i++) {
            pq.offer(new Pair(words[i], i));
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            sb.append(p.getKey()).append(" ");
        }
        sb.setLength(sb.length() - 1);
        String s = sb.toString();
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    // approach 2: sort, but more concise
    // since java's Arrays.sort() is a stable sort which means if two elements are equal
    // their original order in the array will not be modified.

    // time: O(nlogn)

    public String arrangeWords_2(String text) {
        String []s = text.toLowerCase().split(" ");
        Arrays.sort(s, (a, b) ->  a.length() - b.length());
        String ans = String.join(" ", s);
        return Character.toUpperCase(ans.charAt(0)) + ans.substring(1);
    }
}
