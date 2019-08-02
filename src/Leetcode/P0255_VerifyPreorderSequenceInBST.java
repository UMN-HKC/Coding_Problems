package Leetcode;
import java.util.*;

public class P0255_VerifyPreorderSequenceInBST {


    // approach 1: initial approach

    // time: O(nlogn) on average
    // space: O(logn) for stack space

    public boolean verifyPreorder_1(int[] preorder) {
        if (preorder == null || preorder.length < 2) return true;
        return helper(preorder, 0, preorder.length - 1, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    public boolean helper(int[] preorder, int l, int r, int max, int min) {
        if (l > r) {
            return true;
        }
        int root = preorder[l];
        int i = l + 1;
        for (; i <= r; i++) {
            if (preorder[i] < min || preorder[i] > max) return false;
            if (preorder[i] > root) {
                break;
            }
        }
        return helper(preorder, l+1, i - 1, root, min) && helper(preorder, i, r, max, root);
    }


    // approach 2: O(n) time with stack
    public boolean verifyPreorder_2(int[] preorder) {
        Stack<Integer> s = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num < min) return false;
            while (!s.empty() && s.peek() < num) {
                min = s.pop();
            }
            s.push(num);
        }
        return true;
    }

    // approach 3: O(n) time, O(1) space

    public boolean verifyPreorder_3(int[] preorder) {
        int low = Integer.MIN_VALUE, i = -1;
        for (int p : preorder) {
            if (p < low)
                return false;
            while (i >= 0 && p > preorder[i])
                low = preorder[i--];
            preorder[++i] = p;
        }
        return true;
    }
}
