package Leetcode;

public class P0250_CountUnivalueSubtree {

    // initial approach: post order traversal
    // use arr[1] to indicate if subtree is still univalue or not
    // but in fact we can avoid this trivial step, refer to approach 2

    public int countUnivalSubtrees(TreeNode root) {
        int[] cnt = new int[1];
        countHelper(root, cnt);
        return cnt[0];
    }
    public int[] countHelper(TreeNode node, int[] max) {
        if (node == null) {
            return new int[]{0, 1};
        }

        int[] left = countHelper(node.left, max);
        int[] right = countHelper(node.right, max);
        if (left[1] == -1 || right[1] == -1) return new int[]{node.val, -1};

        if ((node.left != null && node.right != null && left[0] == right[0] && node.val == left[0]) ||
                (node.left == null && node.right != null && right[0] == node.val) ||
                (node.right == null && node.left != null && left[0] == node.val) ||
                (node.left == null && node.right == null)) {
            max[0]++;
            return new int[]{node.val, 1};
        }
        return new int[]{node.val, -1};
    }

    // approach 2:
    public int countUnivalSubtrees_2(TreeNode root) {
        int[] cnt = new int[]{0};
        dfs(root, cnt);
        return cnt[0];
    }
    public boolean dfs(TreeNode node, int[] cnt) {
        if (node == null) {
            return true;
        }
        boolean left = dfs(node.left, cnt);
        boolean right = dfs(node.right, cnt);
        if (left && right) {
            if (node.left != null && node.left.val != node.val) {
                return false;
            }
            if (node.right != null && node.right.val != node.val) {
                return false;
            }
            cnt[0]++;
            return true;
        }
        return false;
    }
}
