package Leetcode;

import java.util.*;

public class P0472_ConcatenatedWords {

    // approach 1: dfs + memo
    // The idea is that for each word, we slice it at each index and see if
    // left word is in dictionary and if right word is in dictionary or right
    // word is concatenatable. 

    // time: O(n * l^3) where n is number of words and l is average length of a word
    // since substring will have O(l) time complexity.
    // space: O(n * l). We use set and map to store words

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        Map<String, Boolean> map = new HashMap<>();
        Set<String> dict = new HashSet<>();
        for (String word : words) {
            dict.add(word);
        }
        for (int i = 0; i < n; i++) {
            if (dfs(map, dict, words[i])) {
                res.add(words[i]);
            }
        }
        return res;
    }
    private boolean dfs(Map<String, Boolean> map, Set<String> dict, String word) {
        if (map.containsKey(word)) return map.get(word);
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            if (dict.contains(prefix)) {
                String suffix = word.substring(i);
                if (dict.contains(suffix) || dfs(map, dict, suffix)) {
                    map.put(word, true);
                    return true;
                }
            }
        }
        map.put(word, false);
        return false;
    }
}
