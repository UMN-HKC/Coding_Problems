package Leetcode;
import java.util.*;

public class P0384_ShuffleAnArray {

    // approach 1: brute force (TLE)
    // init the class with all permutations, and use random to generate a random
    // index and return the permutation of that index, when we call shuffle().

    List<List<Integer>> permutations;
    int[] nums;
    Random rd;
    public Solution(int[] nums) {
        this.nums = nums;
        rd = new Random();
        permutations = permute(nums);
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteHelper(res, new ArrayList<>(), used, nums);
        return res;
    }
    public void permuteHelper(List<List<Integer>> res, List<Integer> list, boolean[] visited, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                permuteHelper(res, list, visited, nums);
                list.remove(list.size() - 1);
                visited[i] = false;
            }

        }
    }
    public int[] reset() {
        return nums;
    }
    public int[] shuffle() {
        int idx = rd.nextInt(permutations.size());
        List<Integer> list = permutations.get(idx);
        int[] option = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            option[i] = list.get(i);
        }
        return option;
    }

    // approach 2: reservoir sampling

    int[] nums;
    Random rd;
    public Solution(int[] nums) {
        this.nums = nums;
        rd = new Random();
    }
    public int[] reset() {
        return nums;
    }
    public int[] shuffle() {
        int[] copy = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < copy.length; i++) {
            int j = rd.nextInt(i + 1);
            swap(copy, i, j);
        }
        return copy;
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
