package Leetcode;

import java.util.Random;

public class P0528_RandomPickWithWeight {

    // approach 1: binary search

    // the basic idea is to use accumulated weight to simulate a distribution of numbers,
    // the greater the weight of a number, the greater a range it will cover.
    // Then, use binary search to find the position of randomly generated number -> find the
    // first number that is greater than the generated number -> meaning our generated number
    // lies in the range of this number's weight.

    int[] arr;
    Random rd;
    public Solution(int[] w) {
        arr = w;
        rd = new Random();
        for (int i = 1; i < arr.length; i++) {
            arr[i] += arr[i - 1];
        }
    }

    public int pickIndex() {
        int target = rd.nextInt(arr[arr.length - 1]) + 1;
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = l + (r - l ) / 2;
            if (arr[m] < target) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return r;
    }

}

