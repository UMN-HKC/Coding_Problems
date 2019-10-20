package Leetcode;
import java.util.*;

public class P1215_SteppingNumber {

    // approach 1: DFS
    // The basic idea is to start from single digit, for each single digit, pass it
    // to our recursive call. Inside recursive function, we extract its rightmost digit
    // and explore its neighbors (+1/-1). Note that we rightmost digit is 0 or 9, we can
    // only explore its incre or decre to prevent duplicates.
    // Also note that 0 is dealt separately.

    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            helper(res, low, high, i);
        }
        Collections.sort(res);
        return res;
    }
    public void helper(List<Integer> res, int low, int hi, long cur) {
        if (cur <= hi && cur >= low) res.add((int)cur);
        // note that we still proceed to explore when cur is less than low
        if (cur == 0 || cur > hi) return;
        int rightMost = (int)(cur % 10);
        if (rightMost == 0) {
            helper(res, low, hi, cur * 10 + rightMost + 1);
        }
        else if (rightMost == 9) {
            helper(res, low, hi, cur * 10 + rightMost - 1);
        }
        else {
            helper(res, low, hi, cur * 10 + rightMost + 1);
            helper(res, low, hi, cur * 10 + rightMost - 1);
        }
    }

    // approach 2: BFS

    // start node = 0
    // From 0, we can move to 1 2 3 4 5 6 7 8 9 [ Add these to queue. ]
    // now from 1, we can move to 12 and 10
    // from 2, we can move to 23 and 21
    // from 3,, we can move to 34 and 32
    // .
    // .
    // and so on

    public List<Integer> countSteppingNumbers_2(int low, int high) {
        List<Integer> res = new ArrayList<>();
        Queue<Long> q = new LinkedList<>();
        for (long i = 1; i <= 9; i++) {
            q.offer(i);
        }
        if (low == 0) res.add(0);
        while (!q.isEmpty()) {
            long cur = q.poll();
            if (cur >= low && cur <= high) {
                res.add((int)cur);
            }
            if (cur > high) continue;
            long rightMost = cur % 10;
            long incre = cur * 10 + rightMost + 1;
            long decre = cur * 10 + rightMost - 1;
            if (rightMost > 0) {
                q.offer(decre);
            }
            if (rightMost < 9) {
                q.offer(incre);
            }
        }
        return res;
    }
}
