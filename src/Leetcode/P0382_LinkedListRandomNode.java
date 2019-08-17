package Leetcode;
import java.util.Random;

public class P0382_LinkedListRandomNode {

    // reservoir sampling
    // the basic idea is that each time we call getRandom(), we will iterate through
    // the list once. Use cur to record the value picked so far and use cnt to record
    // the number of values we have encountered so far. cnt and random generator together
    // guarantees the equal possibility.
    
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    Random rd;
    ListNode head;
    public Solution(ListNode head) {
        rd = new Random();
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode itr = head;
        int cnt = 1;
        int cur = itr.val;
        while (itr != null) {
            if (rd.nextInt(cnt) == cnt - 1) {
                cur = itr.val;
            }
            itr = itr.next;
            cnt++;
        }
        return cur;
    }
}
