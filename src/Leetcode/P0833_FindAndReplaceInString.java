package Leetcode;

import java.util.*;

public class P0833_FindAndReplaceInString {

    // apparoach 1: pq + map
    // The basic idea is to build a new string as we iterate the indexes array from smaller to
    // greater indices(so we need a priority queue to sort indices), append the target if this
    // index starts with the source word, otherwise we do not do anything.
    // note that we also keep a pointer pointing to the original string and append any characters
    // that's not replaced to the result string.

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int len = indexes.length;
        PriorityQueue<Map.Entry<Integer, String[]>> pq = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        for (int i = 0; i < len; i++) {
            int index = indexes[i];
            String f = sources[i];
            String t = targets[i];
            Map.Entry<Integer, String[]> entry = new AbstractMap.SimpleEntry(index, new String[]{f, t});
            pq.offer(entry);
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < S.length() && !pq.isEmpty()) {
            Map.Entry<Integer, String[]> entry = pq.poll();
            int idx = entry.getKey();
            String[] pair = entry.getValue();
            if (S.substring(idx, idx + pair[0].length()).equals(pair[0])) {
                while (i < idx) {
                    sb.append(S.charAt(i++));
                }
                sb.append(pair[1]);
                i = idx + pair[0].length();
            }
        }
        while (i < S.length()) sb.append(S.charAt(i++));
        return sb.toString();
    }
}
