package Leetcode;

public class P1137_NthTribonacciNumber {

    // approach 1: DP

    public int tribonacci(int n) {
        if (n < 2) return n;
        if (n == 2) return 1;
        int first = 0, second = 1, third = 1;
        int res = 2;
        for (int i = 3; i < n; i++) {
            first = second;
            second = third;
            third = res;
            res = first + second + third;
        }
        return res;
    }
}
