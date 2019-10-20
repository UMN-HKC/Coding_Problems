package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0151_ReverseWordsInAString {

    // approach 1: reg expression and lib functions

    public String reverseWords_lib_func(String s) {
        String[] parts = s.trim().split("\\s+");
        String out = "";
        for (int i = parts.length - 1; i > 0; i--) {
            out += parts[i] + " ";
        }
        return out + parts[0];
    }

    // approach 2: without using lib functions and regex

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();

        int i = 0;
        while (i < s.length()) {
            // skip white spaces
            while (i < s.length() && s.charAt(i) == ' ') i++;
            // start building one word
            StringBuilder word = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                i++;
            }
            if (word.length() != 0) {
                list.add(word.toString());
            }
        }
        for (int j = list.size() - 1; j >= 0; j--) {
            sb.append(list.get(j));
            if (j > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
