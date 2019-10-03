package Leetcode;

public class P0012_IntegerToRoman {

    // initial approach: okay, this is kind of silly when I see the approach in the discussion board

    public String intToRoman_initial(int num) {

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            if (num >= 1000) {
                sb.append("M");
                num -= 1000;
            }
            else if (num >= 900) {
                sb.append("CM");
                num -= 900;
            }
            else if (num >= 500) {
                sb.append("D");
                num -= 500;
            }
            else if (num >= 400) {
                sb.append("CD");
                num -= 400;
            }
            else if (num >= 100) {
                sb.append("C");
                num -= 100;
            }
            else if (num >= 90) {
                sb.append("XC");
                num -= 90;
            }
            else if (num >= 50) {
                sb.append("L");
                num -= 50;
            }
            else if (num >= 40) {
                sb.append("XL");
                num -= 40;
            }
            else if (num >= 10) {
                sb.append("X");
                num -= 10;
            }
            else if (num >= 9) {
                sb.append("IX");
                num -= 9;
            }
            else if (num >= 5) {
                sb.append("V");
                num -= 5;
            }
            else if (num >= 4) {
                sb.append("IV");
                num -= 4;
            }
            else {
                sb.append("I");
                num -= 1;
            }
        }
        return sb.toString();
    }

    // approach 2:
    // predefine all possible combinations
    // and then subtract the greatest possible until the remaining number is smaller
    // enough to to subtracted by the next value

    // note that 8 is not IIIV, 8 is VIII instead, that's why we can keep subtracting the
    // bigger number until it cannot be subtracted any more.

    public static String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num != 0) {
            while (num >= values[i]) {
                sb.append(strs[i]);
                num -= values[i];
            }
            i++;
        }
        return sb.toString();
    }
}
