package Leetcode;

public class P0327_CountOfRangeSum {

    // approach 1: divide and conquer (merge sort)
    // https://leetcode.com/problems/count-of-range-sum/discuss/77990/Share-my-solution

    // The merge sort based solution counts the answer while doing the merge. During the
    // merge stage, we have already sorted the left half [start, mid] and right half [mid+1, end].
    // We then iterate through the left half with index i. For each i, we need to find two
    // indices k and j in the right half where:
    //  - j is the first index satisfy sum[j] - sum[i] >= lower
    //  - k is the first index satisfy sum[j] - sum[i] > upper
    // then k - j is the number of ranges that satisfy our requirement

    // time: O(nlogn)

    public int countRangeSum(int[] nums, int lower, int upper) {
        int m = nums.length;
        long[] sum = new long[m + 1];
        for (int i = 0; i < m; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        return mergeAndCount(sum, 0, sum.length - 1, lower, upper);
    }
    public int mergeAndCount(long[] sum, int s, int e, int lower, int upper) {
        if (s >= e) return 0;
        int mid = s + (e - s) / 2;
        int cnt = mergeAndCount(sum, s, mid, lower, upper) + mergeAndCount(sum, mid + 1, e, lower, upper);
        // now, the two halves of the array are sorted,
        // though we only care about the second half
        int j = mid+1, k = mid+1;
        for (int i = s; i <= mid; i++) {
            while (j <= e && sum[j] - sum[i] < lower) j++;
            while (k <= e && sum[k] - sum[i] <= upper) k++;
            cnt += k - j;
        }
        long[] sorted = merge(sum, s, mid, mid + 1, e);
        for (int i = s; i <= e; i++) {
            sum[i] = sorted[i - s];
        }
        return cnt;
    }
    public long[] merge(long[] arr, int s1, int e1, int s2, int e2) {
        long[] sorted = new long[e2 - s1 + 1];
        int i = 0;
        while (s1 <= e1 || s2 <= e2) {
            if (s1 <= e1 && s2 <= e2) {
                if (arr[s1] < arr[s2]) {
                    sorted[i++] = arr[s1++];
                }
                else {
                    sorted[i++] = arr[s2++];
                }
            }
            else if (s1 <= e1) {
                sorted[i++] = arr[s1++];
            }
            else {
                sorted[i++] = arr[s2++];
            }
        }
        return sorted;
    }
}
