package Leetcode;

public class P0389_FindTheDifference {

    public char findTheDifference_bit(String s, String t) {

        int dif = 0;
        for (int i = 0; i < t.length(); i++) {
            char tc = t.charAt(i);
            if (i < t.length() - 1) {
                char sc = s.charAt(i);
                dif ^= (tc - 'a');
                dif ^= (sc - 'a');
            }
            else {
                dif ^= (tc - 'a');
            }
        }

        return (char)(dif + 'a');
    }

    public char findTheDifference_auxiliary_array(String s, String t) {
        int[] chars = new int[26];
        for (int i = 0; i < t.length(); i++) {
            char tc = t.charAt(i);
            if (i < t.length() - 1) {
                char sc = s.charAt(i);
                chars[sc - 'a']++;
                chars[tc - 'a']--;
            }
            else {
                chars[tc - 'a']--;
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 0) {
                return (char)(i + 'a');
            }
        }
        return 'a';
    }

}
