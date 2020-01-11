package Leetcode;

import java.util.*;

public class P0049_GroupAnagrams {

    // initial approach: brute force by checking each pair

    // time: O(n^2)
    // space: O(n)

    public List<List<String>> groupAnagrams_initial(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        boolean[] grouped = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (grouped[i]) continue;
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            grouped[i] = true;
            for (int j = i + 1; j < strs.length; j++) {
                if (grouped[j]) continue;
                if (isAnagram(strs[i], strs[j])) {
                    list.add(strs[j]);
                    grouped[j] = true;
                }
            }
            res.add(list);
        }
        return res;
    }

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            chars[sc - 'a']++;
            chars[tc - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // approach 2: use map to map each sorted order string to its anagram list

    // time: O(n * (m * log(m))) where m is the average length of a string
    // space: O(n)

    public List<List<String>> groupAnagrams_map(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String ns = new String(chars);
            if (map.containsKey(ns)) {
                map.get(ns).add(s);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(ns, list);
            }
        }
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }

    // approach 3: counting sort

    // time: O(n)
    // space: O(n)

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String key = countingSort(s);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
    private String countingSort(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : freq) {
            sb.append(i);
        }
        return sb.toString();
    }

}
