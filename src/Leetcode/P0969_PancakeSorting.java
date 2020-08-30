package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0969_PancakeSorting {

    // approach 1: brute force
    // loop from the end and check if the number is at its correct position. If not,
    // find the number that is supposed to be at this position and flip it to the first
    // position and then flip that to the current position we are at.

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int i = A.length - 1; i >= 0; i--) {
            if (A[i] != i + 1) {
                for (int j = i - 1; j >= 0; j--) {
                    if (A[j] == i + 1) {
                        res.add(j + 1);
                        res.add(i + 1);
                        reverse(A, 0, j);
                        reverse(A, 0, i);
                    }
                }
            }
        }
        return res;
    }
    private void reverse(int[] A, int i, int j) {
        while (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j--;
        }
    }
}
