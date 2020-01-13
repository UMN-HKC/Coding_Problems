package Leetcode;
import java.util.*;

public class P0295_FindMedianFromDataStream {

    // approach 1: naive approach
    // add: O(n)
    // find: O(1)

    int size;
    List<Integer> list;
    public MedianFinder() {
        size = 0;
        list = new ArrayList<>();
    }
    public void addNum(int num) {
        if (size == 0) list.add(num);
        else if (num <= list.get(0)) {
            list.add(0, num);
        }
        else if (num >= list.get(size - 1)) {
            list.add(num);
        }
        else {
            int l = 0, r = size - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                int mVal = list.get(m);
                if (num <= mVal && num >= list.get(m - 1)) {
                    list.add(m, num);
                    break;
                }
                else if (num <= mVal) {
                    r = m - 1;
                }
                else {
                    l = m + 1;
                }
            }
        }
        size++;
    }
    public double findMedian() {
        if (size % 2 == 0) {
            return (double)(list.get(size / 2 - 1) + list.get(size / 2)) / 2;
        }
        else {
            return (double)list.get(size / 2);
        }
    }

    // approach 2: min & max heaps
    // https://leetcode.com/problems/find-median-from-data-stream/discuss/74047/JavaPython-two-heap-solution-O(log-n)-add-O(1)-find
    // Any time before we add a new number, there are two scenarios, (total n numbers, k = n / 2):
    //
    // (1) length of (small, large) == (k, k)
    // (2) length of (small, large) == (k, k + 1)
    // After adding the number, total (n + 1) numbers, they will become:
    //
    // (1) length of (small, large) == (k, k + 1)
    // (2) length of (small, large) == (k + 1, k + 1)

    // add: O(logn)
    // find: O(1)

    private PriorityQueue<Integer> min;
    private PriorityQueue<Integer> max;
    private boolean even;
    public MedianFinder() {
        min = new PriorityQueue<>();
        max = new PriorityQueue<>((a, b) -> b - a);
        even = true;
    }

    public void addNum(int num) {
        if (even) {
            max.offer(num);
            min.offer(max.poll());
        }
        else {
            min.offer(num);
            max.offer(min.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (even) {
            return (double)(max.peek() + min.peek()) / 2;
        }
        else {
            return (double)min.peek();
        }
    }
}
