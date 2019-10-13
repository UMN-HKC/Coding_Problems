package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0346_MovingAverageFromDataStream {

    // approach 1: queue

    Queue<Integer> q;
    int size;
    int sum;
    public MovingAverage(int size) {
        this.size = size;
        sum = 0;
        q = new LinkedList<>();
    }

    public double next(int val) {
        if (q.size() == size) {
            sum -= q.poll();
        }
        q.offer(val);
        sum += val;
        return (double)sum / q.size();
    }
}
