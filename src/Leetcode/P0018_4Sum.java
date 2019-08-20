package Leetcode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0018_4Sum {

    // approach 1:

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                int curTarget = target - (nums[i] + nums[j]);
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] == curTarget) {
                        Integer[] list = {nums[i], nums[j], nums[l], nums[r]};
                        res.add(Arrays.asList(list));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    }
                    else if (nums[l] + nums[r] < curTarget) {
                        l++;
                    }
                    else {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}
