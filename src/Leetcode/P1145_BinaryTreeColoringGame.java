package Leetcode;

public class P1145_BinaryTreeColoringGame {

    // approach 1:

    // link: https://leetcode.com/problems/binary-tree-coloring-game/discuss/350570/JavaC%2B%2BPython-Simple-recursion-and-Follow-Up
    // The basic idea is to realize that to guarantee we win, we need to make sure the maximum size of
    // the possible 3 subtrees adjacent to x is greater than n / 2 because that's what we are taking
    // Why is that that we are guarantee a win if we can take that subtree? Because after we take
    // that subtree, x cannot cross over us so we own the majority of nodes.

    int leftCount = 0;
    int rightCount = 0;
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        count(root, x);
        return Math.max(Math.max(leftCount, rightCount), n - 1 - leftCount - rightCount) > n / 2;
    }
    public int count(TreeNode node, int x) {
        if (node == null) return 0;
        if (node.val == x) {
            leftCount = count(node.left, x);
            rightCount = count(node.right, x);
        }
        return 1 + count(node.left, x) + count(node.right, x);
    }
}
