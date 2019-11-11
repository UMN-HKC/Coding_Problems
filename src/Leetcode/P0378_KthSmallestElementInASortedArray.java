package Leetcode;

public class P0378_KthSmallestElementInASortedArray {


    // approach 1: binary search
    // binary search on values. Because although matrix is sorted in both rows and columns,
    // they are not sorted consistently going from the end of element in a row to the start
    // of the elements of the next row. So, binary search cannot be applied to index, but we
    // can binary search on the value where lo is the minimum element and hi is the maximum element

    // time: O(row * col * log(max - min))

    public int kthSmallest(int[][] matrix, int k) {
        // binary search on values
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int cnt = 0;
            int j = n - 1;
            for (int i = 0; i < m; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                cnt += j + 1;
            }
            if (cnt < k) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        return lo;
    }

    // approach 2: priority queue
}
