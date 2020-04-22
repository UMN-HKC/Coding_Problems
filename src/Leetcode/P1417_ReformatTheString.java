package Leetcode;

import java.util.*;

public class P1417_ReformatTheString {

    // approach 1:

    public String reformat(String s) {
        List<Character> digits = new ArrayList<>();
        List<Character> letters = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digits.add(c);
            }
            else {
                letters.add(c);
            }
        }
        if (Math.abs(digits.size() - letters.size()) > 1) {
            return "";
        }
        return buildString(digits, letters);
    }
    private String buildString(List<Character> l1, List<Character> l2) {
        if (l1.size() < l2.size()) return buildString(l2, l1);
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        if (l1.size() > l2.size()) {
            sb.append(l1.get(0));
            i++;
        }
        while (i < l1.size()) {
            sb.append(l2.get(j));
            sb.append(l1.get(i));
            i++;
            j++;
        }
        return sb.toString();
    }
}
