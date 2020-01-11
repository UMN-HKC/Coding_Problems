package Leetcode;

public class P1310_XORQueriesOfASubarray {

    // approach 1: bit manipulation + prefix sum technique
    // The basic idea is to realize that x ^ y ^ x == y and then it is not hard
    // to see that to find a range of XOR result, say [a, b]. The result can be computed
    // by the result of [0, a-1] ^ [0, b]. So we can apply prefix sum technique here.

    // time: O(n)
    // space: O(n)

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefixXOR = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixXOR[i] = prefixXOR[i - 1] ^ arr[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            res[i] = prefixXOR[query[0]] ^ prefixXOR[query[1] + 1];
        }
        return res;
    }
}
