package Leetcode;
import java.util.*;

public class P0269_AlienDictionary {

    // approach 1: topological sort

    // compare two words, after first different characters, we need to stop comparing
    // further and break the loop immediately. After constructing the frequency array, we add all
    // 0 indegree characters which also exist in our map to a queue and start topological sort.
    // Finally, we check if the final string we build contains all the character we need.

    // O(V + E). Space: O(V). V最大26. Edge最大为words.length.

    public String alienOrder(String[] words) {
        int[] freq = new int[26];
        Map<Character, Set<Character>> charToListMap = new HashMap<>();

        // build graph
        buildGraph(words, freq, charToListMap);

        // topological sort
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        topologicalSort(sb, q, freq, charToListMap);
        return sb.length() == charToListMap.size() ? sb.toString() : "";
    }
    private void topologicalSort(StringBuilder sb, Queue<Character> q, int[] freq, Map<Character, Set<Character>> charToListMap) {
        for (char c = 'a'; c <= 'z'; c++) {
            if (charToListMap.containsKey(c) && freq[c - 'a'] == 0) q.offer(c);
        }
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                char c = q.poll();
                sb.append(c);
                for (char next : charToListMap.get(c)) {
                    if (--freq[next - 'a'] == 0) {
                        q.offer(next);
                    }
                }
            }
        }
    }
    private void buildGraph(String[] words, int[] freq, Map<Character, Set<Character>> charToListMap) {
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!charToListMap.containsKey(c)) {
                    charToListMap.put(c, new HashSet<>());
                }
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    if (!charToListMap.get(c1).contains(c2)) {
                        freq[c2 - 'a']++;
                        charToListMap.get(c1).add(c2);
                    }
                    // need to break as soon as we find a different pair of characters
                    break;
                }
            }
        }
    }
}
