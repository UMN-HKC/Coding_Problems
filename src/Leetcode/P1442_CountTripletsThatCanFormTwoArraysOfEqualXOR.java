package Leetcode;

public class P1442_CountTripletsThatCanFormTwoArraysOfEqualXOR {

    // approach 1: DP

    public int countTriplets(int[] arr) {
        int res = 0;
        int[] dp = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i + 1] = dp[i] ^ arr[i];
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {
                    int left = dp[j] ^ dp[i];
                    int right = dp[k + 1] ^ dp[j];
                    if (left == right) {
                        res++;
                    }
                }
            }
        }
        return res;
    }
}
