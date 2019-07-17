package Leetcode;

public class P0096_UniqueBinarySearchTree {

    // approach 1: DP
    // To get number of ways to construct unique BST of number n, we can iterate through 1 to n
    // and set i(1~n) as root and the answer is the summation of number of ways in terms of
    // different root's value. Each root's left and right subtrees are actually sub problems
    // of the original problem, which hints the DP approach.
    // G(i) means # ways to construct BST with number i
    // F(i, n) means # ways to construct BST with i as root and total number is n
    // G(i) = SUM(F(1, n), F(2, n)... F(i-1, n), F(i+1, n)... F(n, n))
    // F(i, n) = G[i-1] * G[n-i]

    public int numTrees(int n) {
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
