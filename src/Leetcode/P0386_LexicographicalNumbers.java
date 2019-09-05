package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0386_LexicographicalNumbers {

    // approach 1: DFS
    // The key to solve this problem is to realize the pattern of the problem
//    The idea is pretty simple. If we look at the order we can find out we
//    just keep adding digit from 0 to 9 to every digit and make it a tree.
//    Then we visit every node in pre-order.
//       1        2        3    ...
//       /\       /\       /\
//    10 ...19  20...29  30...39   ....

    // time: O(n)
    // space: O(how many digits we have)

    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(res, i, n);
        }
        return res;
    }
    public void dfs(List<Integer> res, int cur, int n) {
        if (cur > n) return;
        res.add(cur);
        for (int i = 0; i <= 9; i++) {
            if (Integer.MAX_VALUE / 10 < cur) return;
            if (Integer.MAX_VALUE - i < cur * 10) return;
            if (cur * 10 + i > n) return;
            dfs(res, cur * 10 + i, n);
        }
    }
}
