package Leetcode;

public class P0214_ShortestPalindrome {

    // approach 1: brute force

    public String shortestPalindrome_1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int add = 0; add < s.length(); add++){
            if (check(s, add)) {
                return sb.append(s.substring(s.length() - add)).reverse().append(s).toString();
            }
        }
        return sb.append(s).reverse().append(s).toString();
    }
    public boolean check(String s, int add) {
        int l = 0, r = s.length() - add - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    // approach 2: KMP algo

}
