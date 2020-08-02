package Leetcode;

public class P0520_DetectCapital {

    // approach 1

    public boolean detectCapitalUse(String word) {
        if (word.length() < 2) {
            return true;
        }
        char c = word.charAt(0);
        if (c >= 'A' && c <= 'Z') {
            return isAllUppercase(word, 1, word.length() - 1) ||
                    isAllLowercase(word, 1, word.length() - 1);
        }
        else {
            return isAllLowercase(word, 1, word.length() - 1);
        }
    }
    private boolean isAllUppercase(String word, int l, int r) {
        for (int i = l; i <= r; i++) {
            char c = word.charAt(i);
            if (!(c >= 'A' && c <= 'Z')) {
                return false;
            }
        }
        return true;
    }
    private boolean isAllLowercase(String word, int l, int r) {
        for (int i = l; i <= r; i++) {
            char c = word.charAt(i);
            if (!(c >= 'a' && c <= 'z')) {
                return false;
            }
        }
        return true;
    }

    // approach 2:

    public boolean detectCapitalUse_2(String word) {
        int cnt = 0;
        for(char c: word.toCharArray()) if('Z' - c >= 0) cnt++;
        return ((cnt==0 || cnt==word.length()) || (cnt==1 && 'Z' - word.charAt(0)>=0));
    }
}
