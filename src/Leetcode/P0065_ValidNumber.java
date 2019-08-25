package Leetcode;

public class P0065_ValidNumber {

    // approach 1:
    // some rules to follow:
    // use 3 flags to verify 'e' is valid, dot is valid and a number is present

    public boolean isNumber(String s) {
        s = s.trim();
        boolean isEPresent = false;
        boolean isDotPresent = false;
        boolean isNumPresent = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) && c != 'e') return false;
            else if (Character.isDigit(c)) {
                isNumPresent = true;
            }
            else if (c == '.') {
                if (isEPresent || isDotPresent) return false;
                isDotPresent = true;
            }
            else if (c == 'e') {
                if (isEPresent || !isNumPresent) return false;
                // since e must follow a number so when we have e,
                // we set isNumPresent to false so we can expect
                // a number later. If no num found, isNumPresent
                // will be false and final result will be false
                isNumPresent = false;
                isEPresent = true;
            }
            else if (c == '+' || c == '-') {
                if (isNumPresent || (i - 1 >= 0 && s.charAt(i - 1) != 'e')) return false;
                // set isNumPresent to false, same reasoning as explanation for 'e'
                isNumPresent = false;
            }
            else {
                return false;
            }

        }
        return isNumPresent;
    }

}
