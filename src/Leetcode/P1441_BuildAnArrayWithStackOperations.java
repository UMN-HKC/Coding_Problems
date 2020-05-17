package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P1441_BuildAnArrayWithStackOperations {

    // approach 1:

    // time: O(n)

    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        if (target == null || target.length == 0) return res;
        int cnt = 1;
        int i = 0;
        while (i < target.length) {
            if (cnt != target[i]) {
                res.add("Push");
                res.add("Pop");
            }
            else {
                res.add("Push");
                i++;
            }
            cnt++;
        }
        return res;
    }
}
