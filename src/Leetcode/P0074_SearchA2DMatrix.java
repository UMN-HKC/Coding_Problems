package Leetcode;

public class P0074_SearchA2DMatrix {

    // approach 1: binary search
    // The basic idea is to use binary search once to search row, and
    // use binary search the second time to search column


    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int s = 0, e = m - 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (matrix[mid][0] > target) {
                e = mid - 1;
            }
            else {
                if (matrix[mid][n - 1] >= target) {
                    s = mid;
                    break;
                }
                s = mid+ 1;
            }
        }
        int row = s;
        s = 0; e = n - 1;
        while (s <= e) {
            int mid = s + (e - s) / 2;
            if (matrix[row][mid] == target) return true;
            else if (matrix[row][mid] > target) {
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        return false;
    }

    // approach 2: binary search, only once, and use / and %
    // an alternative is to see the matrix as a list instead of a matrix
    // and we use '/' and '%' to denote row and column. With this approach
    // we only need to do one binary search, but it might cause overflow
    // when we do m*n.

    public boolean searchMatrix_2(int[][] matrix, int target) {

        int row_num = matrix.length;
        int col_num = matrix[0].length;

        int begin = 0, end = row_num * col_num - 1;

        while(begin <= end){
            int mid = (begin + end) / 2;
            int mid_value = matrix[mid/col_num][mid%col_num];

            if( mid_value == target){
                return true;

            }else if(mid_value < target){
                //Should move a bit further, otherwise dead loop.
                begin = mid+1;
            }else{
                end = mid-1;
            }
        }

        return false;
    }
}
