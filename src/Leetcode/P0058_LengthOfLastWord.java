package Leetcode;

public class P0058_LengthOfLastWord {

    public int lengthOfLastWord_initial(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] ss = s.split(" ");
        if (ss.length == 0) {
            return 0;
        }
        return ss[ss.length - 1].length();
    }

    public int lengthOfLastWord_withoutLibraryFunction(String s) {
        int count = -1, i = s.length();
        while (--i >= 0 && s.charAt(i) == ' ');
        while (i - ++count >= 0 && s.charAt(i - count) != ' ');
        return count;
    }
}
