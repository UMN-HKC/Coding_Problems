package Leetcode;

public class P0443_StringCompression {

    // approach 1: brute force

    public int compress(char[] chars) {
        int i = 0;
        int actual = 0;
        while (i < chars.length) {
            char c = chars[i];
            chars[actual++] = c;
            // start looking for continuous same characters
            int j = i;
            while (j + 1 < chars.length && chars[j + 1] == c) {
                j++;
            }
            // only include compressed result
            if (j - i >= 1) {
                int num = j - i + 1;
                int len = 0;
                while (num != 0) {
                    len++;
                    num /= 10;
                }
                num = j - i + 1;
                for (int k = actual + len - 1; k >= actual; k--) {
                    chars[k] = (char)(num % 10 + '0');
                    num /= 10;
                }
                actual = actual + len;
            }
            i = j + 1;
        }
        return actual;
    }
}
