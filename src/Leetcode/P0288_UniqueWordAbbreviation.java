package Leetcode;
import java.util.*;

public class P0288_UniqueWordAbbreviation {


    // approach 1: hashmap

    // The idea is to map all possible abbreviations in the dictionary to their
    // respective words. Then, when we check if a given word is unique, we check if
    // map contains its abbreviation or not. If it does not contain, it is unique.
    // If it contains, then we check if the contained abbreviation's dictionary words
    // has only the given word, if so, it is unique; otherwise, it is not.

    Map<String, Set<String>> map;
    public ValidWordAbbr(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            if (s == null || s.length() < 3) continue;
            String abrev = getAbrev(s);
            if (!map.containsKey(abrev)) {
                map.put(abrev, new HashSet<>());
            }
            map.get(abrev).add(s);
        }
    }
    public String getAbrev(String s) {
        return s.charAt(0) + String.valueOf(s.length() - 2) + s.charAt(s.length() - 1);
    }
    public boolean isUnique(String word) {
        if (word == null || word.length() < 3) return true;
        String abrev = getAbrev(word);
        if (map.containsKey(abrev)) {
            Set<String> set = map.get(abrev);
            return set.size() == 1 && set.contains(word);
        }
        return true;
    }
}
