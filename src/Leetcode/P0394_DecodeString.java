package Leetcode;

public class P0394_DecodeString {

    // approach 1: 


    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                String cntStr = "";
                while (i < s.length() && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    cntStr = cntStr + (s.charAt(i) - '0');
                    i++;
                }
                int cb = findClosing(s, i);
                String inside = decodeString(s.substring(i+1, cb));
                int cnt = Integer.valueOf(cntStr);
                for (int j = 0; j < cnt; j++) {
                    sb.append(inside);
                }
                i = cb;
            }
            else if (Character.isLetter(s.charAt(i))) {
                sb.append(s.charAt(i));
            }

            i++;
        }
        return sb.toString();
    }
    public int findClosing(String s, int start) {
        int cnt = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                cnt++;
            }
            if (s.charAt(i) == ']') {
                cnt--;
            }
            if (cnt == 0) {
                return i;
            }
        }
        return 0;
    }
}
