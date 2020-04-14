package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1408_StringMatchingInAnArray {


    // approach 1: brute force

    public List<String> stringMatching(String[] words) {

        Set<String> res = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j || words[i].length() > words[j].length())  continue;
                if (words[j].contains(words[i])) {
                    res.add(words[i]);
                }
            }
        }
        return new ArrayList(res);
    }
}
