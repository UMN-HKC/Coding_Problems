package Leetcode;
import java.util.*;

public class P0973_KClosestPointsToOrigin {

    // approach 1: PriorityQueue (39 ms)

    // time: O(nlogk)
    // space: O(logk)

    class myComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            double alen = getLen(a);
            double blen = getLen(b);
            if (alen > blen) return -1;
            else if (alen == blen) return 0;
            else return 1;
        }
    }
    public int[][] kClosest_1(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new myComparator());
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        int[][] res = new int[pq.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
    public double getLen(int[] a) {
        return Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
    }


    // approach 2: quick sort (23 ms)

    // time: O(nlogn) average and O(n^2) worst
    // space: O(1)

    public int[][] kClosest_2(int[][] points, int K) {
        quickSort(points, 0, points.length - 1);
        return Arrays.copyOf(points, K);
    }
    public void quickSort(int[][] points, int l, int r) {

        if (l < r) {
            int m = partition(points, l, r);
            quickSort(points, l, m - 1);
            quickSort(points, m+1, r);
        }
    }
    public int partition(int[][] points, int l, int r) {
        int pivot = r;
        int i = l-1, j = l;
        while (j < r) {
            if (isCloser(points[j], points[pivot])) {
                i++;
                swap(points, i, j);
            }
            j++;
        }
        i++;
        swap(points, i, pivot);
        return i;
    }
    public void swap(int[][] points, int l, int r) {
        int[] temp = points[l];
        points[l] = points[r];
        points[r] = temp;
    }
    public boolean isCloser(int[] a, int[] b) {
        double lenA = Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
        double lenB = Math.sqrt(Math.pow(b[0], 2) + Math.pow(b[1], 2));
        return lenA < lenB;
    }

    // approach 3: quick select (7 ms)

    // time O(n) average and O(n^2) worst case
    // space: O(1)

    public int[][] kClosest_3(int[][] points, int K) {
        int l = 0, r = points.length - 1;
        while (l <= r) {
            int m = partition(points, l, r);
            if (m == K) {
                break;
            }
            else if (m < K) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return Arrays.copyOf(points, K);
    }
    public int partition(int[][] points, int l, int r) {
        int i = l - 1, j = l, pivot = r;
        while (j < pivot) {
            if (isCloser(points[j], points[pivot])) {
                i++;
                swap(points, i, j);
            }
            j++;
        }
        i++;
        swap(points, i, pivot);
        return i;
    }
    public void swap(int[][] points, int l, int r) {
        int[] temp = points[l];
        points[l] = points[r];
        points[r] = temp;
    }
    public boolean isCloser(int[] a, int[] b) {
        return Math.sqrt(a[0] * a[0] + a[1] * a[1]) <  Math.sqrt(b[0] * b[0] + b[1] * b[1]);
    }
}
