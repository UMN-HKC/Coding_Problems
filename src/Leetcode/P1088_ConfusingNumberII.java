package Leetcode;

public class P1088_ConfusingNumberII {

    // approach 1: dfs
    // The basic idea is to exhaust all possible numbers so we can apply dfs and backtrack
    // note that we need to handle overflow when we are building the number.
    // Meanwhile we do not need to worry about overflow when we are building its rotated number
    // since the result can be greater than original number, so we use long type.

    public static int[] valid = {0, 1, 6, 8, 9};
    public static int[] reverse = {0, 1, 2, 3, 4, 5, 9, 7, 8, 6};
    int res = 0;
    public int confusingNumberII(int N) {
        dfs(0, N);
        return res;
    }
    public void dfs(int num, int N) {
        if (isValid(num)) res++;
        for (int i = 0; i < valid.length; i++) {
            if (i == 0 && num == 0) continue;
            // check overflow
            int digit = valid[i];
            if ((Integer.MAX_VALUE - digit) / 10 < num || num * 10 + digit > N) continue;
            dfs(num * 10 + digit, N);
        }
    }
    public boolean isValid(int num) {
        long rev = 0;
        int copy = num;
        while (num != 0) {
            int digit = num % 10;
            rev = rev * 10 + reverse[digit];
            num /= 10;
        }
        return rev != copy;
    }
}
