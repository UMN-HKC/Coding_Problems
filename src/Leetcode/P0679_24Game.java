package Leetcode;

import java.util.*;

public class P0679_24Game {

    // approach 1: backtracking

    // Each time, we can pick two numbers and apply certain operation to them and we will
    // get a list of results. Then, remove the two numbers we used from the original list.
    // Now, for each result produced in the result list, we can throw it to the original
    // list and then recurse on that list. The base case is when the list size becomes
    // one. If the only element in the list is 24, we set the global flag as true.
    // Remember that to backtrack, we will have to add the removed two elements to the
    // original list.

    final double precision = 0.001;
    boolean canWin = false;
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double)num);
        }
        judge(list);
        return canWin;
    }
    public void judge(List<Double> list) {
        if (canWin) return;
        if (list.size() == 1) {
            canWin = Math.abs(list.get(0) - 24) < precision;
            return;
        }
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                double n1 = list.get(i);
                double n2 = list.get(j);
                List<Double> nextVals = new ArrayList(Arrays.asList(n1 * n2, n1 + n2, n1 - n2, n2 - n1));
                if (n1 != 0) nextVals.add(n2 / n1);
                if (n2 != 0) nextVals.add(n1 / n2);
                list.remove(i);
                list.remove(j);
                for (double next : nextVals) {
                    list.add(next);
                    judge(list);
                    list.remove(list.size() - 1);
                }
                list.add(j, n2);
                list.add(i, n1);
            }
        }
    }
}
