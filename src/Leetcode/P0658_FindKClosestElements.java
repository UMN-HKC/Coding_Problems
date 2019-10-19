package Leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class P0658_FindKClosestElements {

    // approach 1: binary search + two pointers
    // The idea is to find the closest element using binary search and then expand
    // to both sides until we have included k elements.

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int closest = findClosest(arr, x);
        int l = closest - 1;
        int r = closest + 1;
        Deque<Integer> dq = new LinkedList<>();
        dq.offer(arr[closest]);
        k--;
        while (k > 0) {
            if (l < 0) {
                dq.offerLast(arr[r++]);
            }
            else if (r >= len) {
                dq.offerFirst(arr[l--]);
            }
            else {
                if (Math.abs(x - arr[l]) <= Math.abs(x - arr[r])) {
                    dq.offerFirst(arr[l--]);
                }
                else {
                    dq.offerLast(arr[r++]);
                }
            }
            k--;
        }
        return new ArrayList(dq);
    }
    public int findClosest(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x) {
                return m;
            }
            else if (arr[m] > x) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }
        return Math.abs(x - arr[l]) <= Math.abs(x - arr[r]) ? l : r;
    }

    // approach 2: binary search on starting index of the range
    // compare the possible starting value with the element on the right of the ending element
    // Note that, you SHOULD NOT compare the absolute value of abs(x - A[mid]) and abs(A[mid + k] - x).
    // It fails at cases like A = [1,1,2,2,2,2,2,3,3], x = 3, k = 3
    // Because the absolute value version does not deal with the case when x is not in between
    // A[mid] and A[mid+k]. But the one without the absolute value works.

    public List<Integer> findClosestElements_2(int[] arr, int k, int x) {
        int l = 0, r = arr.length - k;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (x - arr[m] <= arr[m + k] - x) {
                r = m;
            }
            else {
                l = m + 1;;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(arr[l + i]);
        }
        return res;
    }
}
