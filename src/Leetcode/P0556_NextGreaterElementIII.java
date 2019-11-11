package Leetcode;

public class P0556_NextGreaterElementIII {

    // approach 1: string
    // similar to find the next permutation problem

    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int i = digits.length - 1;
        while (i > 0 && digits[i] <= digits[i - 1]) i--;
        if (i == 0) return -1;
        int swapPos = i;
        i--;
        while (swapPos + 1 < digits.length && digits[swapPos + 1] > digits[i]) {
            swapPos++;
        }
        swap(digits, i, swapPos);
        reverse(digits, i + 1, digits.length - 1);
        long res = Long.parseLong(new String(digits));
        if (res > Integer.MAX_VALUE) return -1;
        return (int)res;
    }
    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            swap(chars, i, j);
            i++;
            j--;
        }
    }
}
