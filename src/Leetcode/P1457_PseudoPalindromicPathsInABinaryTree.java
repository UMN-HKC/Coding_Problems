package Leetcode;

public class P1457_PseudoPalindromicPathsInABinaryTree {

    // approach 1: dfs + backtrack
    // the key is to realize that a palindrome has at most 1 odd frequency number

    // time: O(n)
    // space: O(n)

    int cnt = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        int[] freq = new int[9];
        dfs(root, freq, 0);
        return cnt;
    }
    private void dfs(TreeNode root, int[] freq, int odd) {
        if (root == null) return;
        if (++freq[root.val - 1] % 2 == 1) {
            odd++;
        }
        else {
            odd--;
        }

        if (root.left == null && root.right == null) {
            if (odd <= 1) {
                cnt++;
            }
        }
        dfs(root.left, freq, odd);
        dfs(root.right, freq, odd);
        freq[root.val - 1]--;
    }
}
