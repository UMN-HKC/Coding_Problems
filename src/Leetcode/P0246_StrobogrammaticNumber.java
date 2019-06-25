package Leetcode;

public class P0246_StrobogrammaticNumber {

    // initial approach

    public boolean isStrobogrammatic(String num) {
        String s = "00 11 88 69 96";
        String single = "018";
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (l == r) {
                return single.contains(num.substring(l, l+1));
            }
            if (!s.contains((num.substring(l,l+1) + num.substring(r,r+1)))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
