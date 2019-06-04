package Leetcode;

public class P0345_ReverseVowelsOfString {

    // intial approach: two pointers

    // time: O(n * t) where t is the time of library function

    public String reverseVowels(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String vowels = "AEIOUaeiou";
        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (vowels.indexOf(chars[l]) != -1 && vowels.indexOf(chars[r]) != -1) {
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
                l++;
                r--;
            }
            else if (vowels.indexOf(chars[l]) != -1) {
                r--;
            }
            else if (vowels.indexOf(chars[r]) != -1) {
                l++;
            }
            else {
                l++;
                r--;
            }

        }
        return new String(chars);
    }

    // after modification, more concise
    public String reverseVowels_concise(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        String vowels = "AEIOUaeiou";
        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            // two while loops to get both pointers pointing to vowels
            while (l < r && vowels.indexOf(chars[l]) == -1) {
                l++;
            }
            while (l < r && vowels.indexOf(chars[r]) == -1) {
                r--;
            }
            if (l < r) {
                char temp = chars[l];
                chars[l] = chars[r];
                chars[r] = temp;
            }
            l++;
            r--;
        }
        return new String(chars);
    }

}
