package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0986_IntervalListIntersections {

    // approach 1L two pointers

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int lenA = A.length, lenB = B.length;
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            // check for intersection
            if (A[i][0] >= B[j][0] && A[i][0] <= B[j][1]) {
                list.add(new int[]{A[i][0], Math.min(A[i][1], B[j][1])});
            }
            else if (B[j][0] >= A[i][0] && B[j][0] <= A[i][1]) {
                list.add(new int[]{B[j][0], Math.min(B[j][1], A[i][1])});
            }
            // increment pointers
            if (A[i][1] > B[j][1]) {
                j++;
            }
            else if (A[i][1] < B[j][1]){
                i++;
            }
            else {
                i++;
                j++;
            }
        }
        return list.toArray(new int[0][0]);
    }
}
