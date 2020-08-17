package Leetcode;

import java.util.stream.IntStream;

public class P0484_FindPermutation {

    // approach 1: greedy
    // The idea is to start with the sorted array which is the lexi smallest
    // and then iterate through s and count continuous "D" and reverse accordingly

    public int[] findPermutation(String s) {
        int n = s.length();
        int[] sorted = IntStream.range(1, n + 2).toArray();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'D') {
                int j = i;
                while (j < n && s.charAt(j) == 'D') {
                    j++;
                }
                reverse(sorted, i, j);
                i = j - 1;
            }
        }
        return sorted;
    }
    private static void reverse(int[] arr, int s, int e) {
        while (s < e) {
            swap(arr, s, e);
            s++;
            e--;
        }
    }
    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
