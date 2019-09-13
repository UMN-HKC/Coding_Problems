package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P0030_SubstringWithConcatenationOfAllWords {

    // approach 1: map  (kind of brute force)

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words == null || words.length == 0 || s == null || s.length() == 0) return res;
        Map<String, Integer> map = new HashMap<>();
        int len = words[0].length();
        int cnt = words.length;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int i = 0;
        while (i < s.length() - cnt * len + 1) {
            Map<String, Integer> temp = new HashMap<>();
            int j = i;
            int num = 0;
            while (j < i + len * cnt && num < cnt) {
                String single = s.substring(j, j + len);
                if (!map.containsKey(single) || (map.containsKey(single) && temp.get(single) == map.get(single))) {
                    break;
                }
                temp.put(single, temp.getOrDefault(single, 0) + 1);
                num++;
                j += len;
            }
            if (num == cnt) res.add(i);
            i++;
        }
        return res;
    }
}
