package Leetcode;

public class P0009_PalindromeNumber {

    // approach 1:
    // build the whole reverse number and compare

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int num = x;
        int rev = 0;
        while (num != 0) {
            int digit = num % 10;
            rev = rev * 10 + digit;
            num /= 10;
        }
        return rev == x;
    }

    // approach 2:
    // This approach instead build the reverse integer with only half
    // digits which are enough for checking palindrome. Smart.

    public boolean isPalindrome_2(int x) {
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
