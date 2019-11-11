package Leetcode;

import java.util.*;

public class P0737_SentenceSimilarityII {

    // approach 1: dfs
    // build graph and then for each pair, check if the pair is connected

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();

        for (List<String> pair : pairs) {
            String w1 = pair.get(0);
            String w2 = pair.get(1);
            if (!map.containsKey(w1)) map.put(w1, new HashSet<>());
            if (!map.containsKey(w2)) map.put(w2, new HashSet<>());
            map.get(w1).add(w2);
            map.get(w2).add(w1);
        }
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (!check(map, w1, w2, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }
    private boolean check(Map<String, Set<String>> map, String from, String to, Set<String> visited) {
        if (from.equals(to)) return true;
        if (!map.containsKey(from) || !map.containsKey(to)) return false;
        visited.add(from);
        for (String neighbor : map.get(from)) {
            if (!visited.contains(neighbor)) {
                if (check(map, neighbor, to, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // approach 2: union find
    // The basic idea is to use map to simulate union find where we map child to parent.
    // We first iterate through pairs and for each pair we know they are similar so we
    // find their parents. If their parents are not the same, we union them together.

    public boolean areSentencesSimilarTwo_2(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : pairs) {
            String w1 = pair.get(0);
            String w2 = pair.get(1);
            if (!map.containsKey(w1)) map.put(w1, w1);
            if (!map.containsKey(w2)) map.put(w2, w2);
            String p1 = find(map, w1);
            String p2 = find(map, w2);
            if (!p1.equals(p2)) {
                map.put(p1, p2);
            }
        }
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) continue;
            if (!map.containsKey(w1) || !map.containsKey(w2)) return false;
            if (!find(map, w1).equals(find(map, w2))) return false;
        }
        return true;
    }
    private String find(Map<String, String> map, String w) {
        while (!map.get(w).equals(w)) {
            w = map.get(w);
        }
        return w;
    }
}
