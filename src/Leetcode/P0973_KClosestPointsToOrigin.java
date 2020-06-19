package Leetcode;
import java.util.*;
import java.util.stream.IntStream;

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


    // approach 2: quick select (7 ms)

    // time O(n) average and O(n^2) worst case
    // space: O(1)

    public int[][] kClosest_3(int[][] points, int K) {
        K--;
        int p = -1;
        int l = 0, r = points.length - 1;
        while ((p = partition(points, l, r)) != K) {
            if (p < K) {
                l = p + 1;
            }
            else {
                r = p - 1;
            }
        }
        // just to practice java 8 stream 
        return IntStream.range(0, K + 1).boxed().map(i -> points[i]).toArray(int[][]::new);
    }
    private int partition(int[][] points, int l, int r) {
        Random random = new Random();
        int pivot = random.nextInt(r - l + 1) + l;
        double pivotDist = getDistance(points, pivot);
        swap(points, pivot, r);
        int i = l, j = l;

        while (j < r) {
            if (getDistance(points, j) <= pivotDist) {
                swap(points, i, j);
                i++;
                j++;
            }
            else {
                j++;
            }
        }
        swap(points, i, r);
        return i;
    }
    private double getDistance(int[][] points, int idx) {
        return Math.sqrt(Math.pow(points[idx][0], 2)
                + Math.pow(points[idx][1], 2));
    }
    private void swap(int[][] points, int l, int r) {
        int[] temp = points[l];
        points[l] = points[r];
        points[r] = temp;
    }
}
