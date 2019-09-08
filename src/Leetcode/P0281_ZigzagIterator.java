package Leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P0281_ZigzagIterator {

    // approach 1: naive approach
    // use variables to simulate zigzag

    int at;
    int pos1;
    int pos2;
    List<Integer> v1;
    List<Integer> v2;
    public ZigzagIterator_1(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        at = 0;
        pos1 = 0;
        pos2 = 0;
    }

    public int next() {
        int res;
        if (at == 0) {
            if (pos1 < v1.size()) {
                res = v1.get(pos1++);
            }
            else {
                res = v2.get(pos2++);
            }
            at = 1;
        }
        else {
            if (pos2 < v2.size()) {
                res = v2.get(pos2++);
            }
            else {
                res = v1.get(pos1++);
            }
            at = 0;
        }
        return res;
    }

    public boolean hasNext() {
        return pos1 < v1.size() || pos2 < v2.size();
    }

    // approach 2: queue + iterator (extendable approach)
    // use queue to store iterators of lists and also simulate zigzig

    Queue<Iterator> q;
    public ZigzagIterator_2(List<Integer> v1, List<Integer> v2) {
        q = new LinkedList<>();
        if (v1 != null && v1.size() != 0)
            q.offer(v1.iterator());
        if (v2 != null && v2.size() != 0)
            q.offer(v2.iterator());
    }

    public int next() {
        Iterator<Integer> itr = q.poll();
        int res = itr.next();
        if (itr.hasNext()) q.offer(itr);
        return res;
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}
