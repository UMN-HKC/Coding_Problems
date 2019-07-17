package Leetcode;
import java.util.*;

public class P0095_UniqueBinarySearchTreesII {

    // approach 1: recursive approach (deep copy)
    // genTree(start, end) means we will generate trees which stores value from 'start' to 'end'
    // and the return value is the list with root('start' to 'end'), and their left and right
    // subtrees are recursively generated.

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return genTree(1, n);
    }
    public List<TreeNode> genTree(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++) {
            for (TreeNode lnode : genTree(start, i - 1)) {
                for (TreeNode rnode : genTree(i + 1, end)) {
                    // note that root should be created here instead of within the outer loop
                    // because we want a new root for each left and right combination
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }
}
