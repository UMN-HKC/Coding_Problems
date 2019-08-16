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
    // add: O(logn)
    // find: O(1)

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>((a, b) -> a - b);
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        // be careful that we push num to max heap first and then
        // poll a max num from max heap and offer it to min heap
        // Afterwards, we do a check to make sure max heap's size
        // is equal or greater than min heap by 1.
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    public double findMedian() {
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            return (double)(minHeap.peek() + maxHeap.peek()) / 2;
        }
        else {
            return (double)maxHeap.peek();
        }
    }
}
