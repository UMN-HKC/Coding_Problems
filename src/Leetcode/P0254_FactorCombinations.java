package Leetcode;
import java.util.*;

public class P0254_FactorCombinations {

    // sort of have similar idea but failed to backtrack correctly and produced duoplicates
    // idea borrowed from discussion board
    // basically, we have a start index to prevent duplicates. Each time we find a number that
    // could mod n to 0, we make recursive calls on the number (n / num)

    // time: O(nlogn)
    // space: O(logn) we always visit sqrt(n)

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 1) return res;
        dfs(res, new ArrayList<>(), 2, n);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int start, int n) {
        for (int i = start; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                list.add(i);
                list.add(n / i);
                res.add(new ArrayList<>(list));
                // remove the previously added element and prepare for next recursive call
                list.remove(list.size() - 1);
                // set next start to i instead of 2 to prevent duplicates
                // because if we set next start to 2 again, it will include
                // some combinations that have already been explored for smaller i
                dfs(res, list, i, n / i);
                // backtrack
                list.remove(list.size() - 1);
            }
        }
        return;
    }
}
