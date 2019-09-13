package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0090_SubsetsII {

    // Initallly stuck at the condition to skip the index
    // idea borrowed from discussion board: (illustrated below)
    // To solve this problem, it is helpful to first think how many subsets are there.
    // If there is no duplicate element, the answer is simply 2^n, where n is the number of elements.
    // This is because you have two choices for each element, either putting it into the subset or not.

    // time: O(2^n)
    // space: O(n)

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, list, 0, nums);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int index, int[] nums) {
        res.add(new ArrayList<>(list));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            // when i > index, it means we have explored all possible subsets with start of
            // index. Thus, if i > index and nums[i] == nums[i - 1], it means we have encountered
            // a duplicate element as a starting element for the new subset, so we skip it.
            if (i > index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(res, list, i+1, nums);
            list.remove(list.size() - 1);
        }
        return;
    }

    // approach 2:
    // The idea is that for each position, we either choose it or not
    // There are only 2 branches from this stack for our next level
    // recursion call.
    // Note that different from the first question, this one contains
    // duplicates, so we need to sort the initial array first and
    // then inside the recursion call, we can skip duplicates where
    // its previous element is the same but hasn't marked as added

    public List<List<Integer>> subsetsWithDup_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, 0, false);
        return res;
    }
    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int idx, boolean prevAdded) {
        if (idx >= nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        backtrack(res, list, nums, idx + 1, false);
        if (idx != 0 && !prevAdded && nums[idx] == nums[idx - 1]) {
            return;
        }
        list.add(nums[idx]);
        backtrack(res, list, nums, idx+1, true);
        list.remove(list.size() - 1);
    }
}
