package Leetcode;

public class P0006_ZigZagConversion {

    // couldn't figure out initially, idea borrowed from discussion board
    // basically, have numRows string array to store each row's result when
    // we traverse vertically down and then obliquely up each time.


    public String convert(String s, int numRows) {
        StringBuilder[] res = new StringBuilder[numRows];
        for (int i = 0; i < res.length; i++) {
            res[i] = new StringBuilder();
        }
        int i = 0;
        while (i < s.length()) {
            for (int index = 0; index < numRows && i < s.length(); index++) {
                res[index].append(s.charAt(i++));
            }
            // note that we go obliquely up to index == 1 because next iteration
            // starts with index == 0, so we don't want duplicates
            for (int index = numRows - 2; index >= 1 && i < s.length(); index--) {
                res[index].append(s.charAt(i++));
            }
        }
        for (int j = 1; j < res.length; j++) {
            res[0].append(new String(res[j]));
        }
        return res[0].toString();
    }
}
