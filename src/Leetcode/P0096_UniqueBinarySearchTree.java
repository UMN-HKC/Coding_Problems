package Leetcode;

public class P0096_UniqueBinarySearchTree {

    // approach 1: DP
    // The basic idea is to use a 2-d dp array to record intermediate value
    // dp[i][j] represents number of ways to construct bst with number
    // from i to j

    // time: O(n^2)
    // space: O(n^2)

    public int numTrees_1(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
        }
        return dfs(1, n, dp);
    }
    private int dfs(int l, int r, int[][] dp) {
        if (l > r) return 1;
        if (dp[l][r] != 0) return dp[l][r];
        int cnt = 0;
        for (int i = l; i <= r; i++) {
            cnt += dfs(l, i - 1, dp) * dfs(i + 1, r, dp);
        }
        dp[l][r] = cnt;
        return cnt;
    }

    // approach 2: DP (saves more save)
    // This approach saves more space

    // To get number of ways to construct unique BST of number n, we can iterate through 1 to n
    // and set i(1~n) as root and the answer is the summation of number of ways in terms of
    // different root's value. Each root's left and right subtrees are actually sub problems
    // of the original problem, which hints the DP approach.
    // G(i) means # ways to construct BST with number i
    // F(i, n) means # ways to construct BST with i as root and total number is n
    // G(i) = SUM(F(1, n), F(2, n)... F(i-1, n), F(i+1, n)... F(n, n))
    // F(i, n) = G[i-1] * G[n-i]

    // time: O(n^2)
    // space: O(n)

    public int numTrees_2(int n) {
        int[] G = new int[n+1];
        G[0] = G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1]*G[i-j];
            }
        }
        return G[n];
    }
}
