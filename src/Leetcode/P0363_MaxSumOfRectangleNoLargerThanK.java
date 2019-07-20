package Leetcode;
import java.util.*;

public class P0363_MaxSumOfRectangleNoLargerThanK {

    // ciouldn't figure out initially, idea borrowed from discussion board
    // link: https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/83599/Accepted-C%2B%2B-codes-with-explanation-and-references

    // approach 1: DP
    // step1: figure out the approach to calculate maximum sum rectangular submatrix
        // link: https://www.youtube.com/watch?v=yCQN096CwWM
        // for step1, we basically transform 2-d matrix submatrix sum to 1-d subarray sum
    // step2: use treeset to help find the maximum sum that is less than k

    // time: O(row * (col^2) * (row*log(row)))
    // spaceL O(row)

    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int left = 0; left < n; left++) {
            // each time we excludes a column, use a new array to start
            int[] line = new int[m];
            // by moving right pointer and adding value to line array, we are actually
            // transforming the 2-d problem into 1-d array (since we are doing the addition
            // for the rectangular in the matrix)
            for (int right = left; right < n; right++) {
                for (int i = 0; i < m; i++) {
                    line[i] += matrix[i][right];
                }
                TreeSet<Integer> ts = new TreeSet<>();
                // add 0 to tree set to help when sum - k == 0
                ts.add(0);
                int sum = 0;
                for (int num : line) {
                    sum += num;
                    // ceiling() operation is tricky in here
                    // the goal here is to find a subarray sum that is just less than or
                    // equal to k, so we see subtract k from current subarray sum and
                    // see if there's a subarray sum that is just above that subtracted result
                    // which is the max result that is no larger than k
                    Integer target = ts.ceiling(sum - k);
                    if (target != null && sum - target > max) {
                        max = sum - target;
                    }
                    ts.add(sum);
                }
            }
        }
        return max;
    }
}
