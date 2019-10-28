package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0205_IsomorphicStrings {

    // take me quite some time and couldn't figure out initially
    // idea borrowed from discussion board
    // Basically, we need to check if the last seen indices of the currently visiting character in
    // both strings are equal. This ensures that their pattern is same.

    // time: O(n)
    // space: O(1) constant space

    public boolean isIsomorphic(String s, String t) {
        int[] indices = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (indices[s.charAt(i)] != indices[t.charAt(i) + 128]) {
                return false;
            }
            indices[s.charAt(i)] = indices[t.charAt(i) + 128] = i + 1;
        }
        return true;
    }

    // approach 2: map
    // note that when using hashmap, the value stored inside map should be object
    // so we need to initialize i as Integer (although I still do not get why)

    public boolean isIsomorphic_2(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (Integer i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map1.put(c1, i) != map2.put(c2, i)) {
                return false;
            }
        }
        return true;
    }
}
