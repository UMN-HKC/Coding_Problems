package Leetcode;

public class P0167_TwoSumII_InputSorted {

    // approach 1: straight two pointers (fasttest)
    // tried to use binary search to locate the position of largest element that's smaller than
    // the target, and then apply two pointers. But this method failed when there's negative numbers.

    // time: O(n)

    public int[] twoSum_two_pointers(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        int[] res = new int[2];
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                res[0] = start + 1;
                res[1] = end + 1;
                return res;
            }
            else if (numbers[start] + numbers[end] < target) {
                start++;
            }
            else {
                end--;
            }
        }
        return null;
    }

    // approach 2: binary search (slower worst case O(nlogn))
    // basic idea is to fix nums[i] as left number, and binary search for i + 1 to the end and
    // try to find the right element that together with left fixed element summing to target

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int fixed = numbers[i];
            int start = i + 1;
            int end = numbers.length - 1;
            int gap = target - fixed;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (numbers[mid] == gap) {
                    return new int[]{i+1, mid + 1};
                }
                else if (numbers[mid] < gap) {
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
        }
        return null;
    }
}
