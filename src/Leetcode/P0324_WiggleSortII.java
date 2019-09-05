package Leetcode;

import java.util.Arrays;
import java.util.Random;

public class P0324_WiggleSortII {

    // approach 1: time O(nlogn) space O(n)

    public void wiggleSort_1(int[] nums) {
        int n = nums.length, m = (n + 1) >> 1;
        int[] copy = Arrays.copyOf(nums, n);
        Arrays.sort(copy);

        for (int i = m - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
        for (int i = n - 1, j = 1; i >= m; i--, j += 2) nums[j] = copy[i];
    }

    // approach 2: time O(n) space O(n)
    // refer to: https://leetcode.com/problems/wiggle-sort-ii/discuss/77684/Summary-of-the-various-solutions-to-Wiggle-Sort-for-your-reference

    // The basic idea is to find the median, and then put smaller part elements
    // to even indices and greater part elements to odd indices.
    // - create a copy array
    // - use the original array to find the median
    // - partition the copy array in terms of the median again, and put all elements
    // smaller than median to left and all elements greater than median to right
    // Why do we need to partition again instead of copying from original array?
    // Because we want to put duplicate medians in a consecutive order,
    // while the previous partition does not guarantee this.

    // - put smaller part elements to even indices and put greater part elements to odd indices.

    public void wiggleSort_2(int[] nums) {

        int n = nums.length, m = (n + 1) >> 1;
        int[] copy = Arrays.copyOf(nums, n);
        int median = findMedian(nums, 0, n - 1, m);

        for (int i = 0, j = 0, k = n - 1; j <= k;) {
            if (copy[j] > median) {
                swap(copy, j,k--);
            }
            else if (copy[j] < median){
                swap(copy, i++, j++);
            }
            else {
                j++;
            }
        }
        // put smaller elements to even indices
        // put greater elements to odd indices
        // IMPORTANT: note that it is necessary to start from right to left so that
        // we can scatter overlapping elements to avoid case such as [4,5,5,6]

        for (int i = 0, j = m - 1; j >= 0; j--, i += 2) {
            nums[i] = copy[j];
        }

        for (int i = 1, j = n - 1; j >= m; j--, i += 2) {
            nums[i] = copy[j];
        }
    }
    public int findMedian(int[] nums, int l, int r, int m) {
        while (l < r) {
            int p = partition(nums, l, r);
            if (p == m) return nums[p];
            else if (p < m) {
                l = p + 1;
            }
            else {
                r = p - 1;
            }
        }
        return nums[l];
    }
    public int partition(int[] nums, int l, int r) {
        Random rd = new Random();
        int pivotIdx = l + rd.nextInt(r - l + 1);
        swap(nums, r, pivotIdx);
        int pivot = nums[r];
        for (int i = l; i < r; i++) {
            if (nums[i] <= pivot) {
                swap(nums, i, l++);
            }
        }
        swap(nums, l, r);
        return l;
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    // approach 3: "Virtual Indexing" time O(n) space O(1)
    // come back later
}
