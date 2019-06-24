package Leetcode;

public class P0092_ReverseLinkedListII {

    // initial approach:
    // The key to do it in one pass is that we need to keep track of list before m and at m
    // so that after we reverse the target range of list, we can connect them back.

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        int cnt = 1;
        ListNode beforeM = null, markM = null;
        ListNode prev = null, cur = head, next = head;
        while (cnt <= n) {
            next = next.next;
            if (cnt < m) {
                prev = cur;
                cur = next;
            }
            else if (cnt >= m && cnt < n){
                if (cnt == m) {
                    beforeM = prev;
                    markM = cur;
                }
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            else if (cnt == n) {
                markM.next = next;
                cur.next = prev;
                // if beforeM is null, we do not do anything yet
                if (beforeM != null) {
                    beforeM.next = cur;
                }
                break;
            }
            cnt++;
        }
        // when there's no node before m, it means where cur stops
        // at which is node at position n will be the new head
        return beforeM == null ? cur : head;
    }
}
