package Leetcode;

public class P0009_PalindromeNumber {

    // approach 1:
    // idea borrowed from discussion board
    // initially tried to check digits from left and right, but could not figure out
    // a way to cover all edge cases. This approach instead build the reverse integer
    // with only half digits which are enough for checking palindrome. Smart.

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x >= 10 && x % 10 == 0) return false;
        int rev = 0;
        while (rev < x) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (x == rev || rev / 10 == x);
    }
}
