package Leetcode;

public class P0099_RecoverBinarySearchTree {

    // approach 1: morris traversal
    // link: https://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html

    // based on morris traversal, added pre, first, and second these 3 variables to help
    // detect incorrect position of two nodes.

    // Basically, add the following code block to where we actually print nodes in our
    // inorder traversal:
    //    if (pre != null && pre.val > cur.val) {
    //        if (first == null) {
    //            first = pre;
    //            second = cur;
    //        }
    //        else {
    //            second = cur;
    //        }
    //    }

    // If first is not set, we set first to pre, and second to cur. This handles the case when
    // consecutive nodes are swapped. Otherwise, if first is already set, we will set second
    // to cur because cur is the node with smaller value that gets mal-positioned.

    // time: O(n), though it seems finding predecessor takes time which may result in O(nlgn),
    // it is not. 
    // space: O(1)

    public void recoverTree(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre, first, second;
        pre = first = second = null;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val > cur.val) {
                    if (first == null) {
                        first = pre;
                        second = cur;
                    }
                    else {
                        second = cur;
                    }
                }
                pre = cur;
                cur = cur.right;
            }
            else {
                TreeNode itr = cur.left;
                while (itr.right != null && itr.right != cur) {
                    itr = itr.right;
                }
                // if cur node's predecessor's successor is cur node,
                // reset cur node's redecessor's right pointer to null
                // visit current node and move cur to its right
                if (itr.right == cur) {
                    itr.right = null;
                    if (pre != null && pre.val > cur.val) {
                        if (first == null) {
                            first = pre;
                            second = cur;
                        }
                        else {
                            second = cur;
                        }
                    }
                    pre = cur;
                    cur = cur.right;
                }
                else {
                    // continue threading
                    itr.right = cur;
                    cur = cur.left;
                }
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
