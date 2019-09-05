package Leetcode;

import java.util.*;

public class P0187_RepeatedDNASequence {

    // approach 1:
    // The idea is to move your 'window' of size 10 to capture the string and
    // use a set to check if a string has been previously visited

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null) return new ArrayList<>();
        Set<String> visited = new HashSet<>(), res = new HashSet<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String str = s.substring(i, i+10);
            if (!visited.add(str)) res.add(str);
        }
        return new ArrayList(res);
    }
}
