package Leetcode;
import java.util.*;

public class P0249_GroupShiftedStrings {

    /***
     *
     * @param strings
     * @return
     *
     * The basic idea is to have a general key for each sequence pattern,
     * say "bcd" maps to "abc", and "yza" maps to "abc"
     * The special case is like "az" and "ba" where the second letter does not
     * match to key ('z'- 0 != 'a' - 1 where 0 and 1 are offsets for each string)
     * so once we have c < 'a', we add 26.
     *
     */

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            int offset = s.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < s.length(); i++) {
                char c = (char)(s.charAt(i) - offset);
                if (c < 'a') c += 26;
                key += c;
            }
            if (map.containsKey(key)) {
                List<String> list = map.get(key);
                list.add(s);
                map.put(key, list);
            }
            else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}
