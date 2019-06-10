package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P1078_OccurrencesAfterBigram {


    public String[] findOcurrences(String text, String first, String second) {
        List<String> res = new ArrayList<>();
        String[] textArray = text.split(" ");
        for (int i = 0; i < textArray.length - 2; i++) {
            if (textArray[i].equals(first) && textArray[i+1].equals(second)) {
                res.add(textArray[i+2]);
            }
        }
        return res.toArray(new String[0]);
    }
}
