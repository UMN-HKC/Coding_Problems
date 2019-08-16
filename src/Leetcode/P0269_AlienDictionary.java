package Leetcode;
import java.util.*;

public class P0269_AlienDictionary {

    // approach 1: topological sort

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        Map<Character, Set<Character>> map = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree[c - 'a'] = 0;
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                if (word1.charAt(j) != word2.charAt(j)) {
                    Set<Character> set = new HashSet<>();
                    if (map.containsKey(word1.charAt(j))) set = map.get(word1.charAt(j));
                    if (!set.contains(word2.charAt(j))) {
                        set.add(word2.charAt(j));
                        map.put(word1.charAt(j), set);
                        indegree[word2.charAt(j) - 'a']++;
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer((char)('a' + i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            if (map.containsKey(c)) {
                for (char child : map.get(c)) {
                    if (--indegree[child - 'a'] == 0) {
                        queue.offer(child);
                    }
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (indegree[i] > 0) {
                return "";
            }
        }
        return sb.toString();
    }
}
