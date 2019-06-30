package Leetcode;
import java.util.*;
public class P1104_PathInZigzagLabelledBinaryTree {

    // couldn't figure out initially
    // The basic idea is to get the height of the node in the tree and then traverse back
    // to root which is the tricky part (since it is zigzaged).
    // We know that the target node's actual parent should be the parent of its symmetric
    // node at current height. So we first get the current height which is the first for loop.

    // Then, to get the symmetric node, we first need to realize the fact:
    // (label of current node + label of symmetric node) ==
    // (label of minimum node at current height) + (label of maximum node at current height)
    // The minimum label can be calculated by 2^(level - 1) and maximum label can be
    // calculated by 2^(level) - 1, where level is the current height.

    // time: O(logn)
    // space O(logn)

    public List<Integer> pathInZigZagTree(int label) {
        int size = 1;
        int level = 1;
        LinkedList<Integer> res = new LinkedList<>();
        while ((size << 1) <= label) {
            size <<= 1;
            level++;
        }
        while (label != 0) {
            res.addFirst(label);
            label = ((1 << level) - 1 + (1 << (level - 1)) - label) / 2;
            level--;
        }
        return res;
    }
}
