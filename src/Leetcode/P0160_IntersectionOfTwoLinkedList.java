package Leetcode;

public class P0160_IntersectionOfTwoLinkedList {

    // approach 1:
    // The idea is to find the difference of length between two linked list first
    // and then move the pointer of the longer list first with the difference
    // in length (meanwhile, check intersection). Next, move two pointers together
    // of two lists together and check for intersection.

    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode itrA = headA, itrB = headB;
        while (itrA != null && itrB != null) {
            if (itrA == itrB) return itrA;
            itrA = itrA.next;
            itrB = itrB.next;
        }
        // same length and no intersection
        if (itrA == null && itrB == null) {
            return null;
        }
        else if (itrA == null) {    // b is longer
            int more = getMore(itrB);
            return intersect(headA, headB, more);
        }
        else {
            int more = getMore(itrA);
            return intersect(headB, headA, more);
        }

    }
    public ListNode intersect(ListNode a, ListNode b, int more) {
        for (int i = 0; i < more; i++) {
            if (b == a) return b;
            b = b.next;
        }
        while (a != null && b != null) {
            if (a == b) return a;
            a = a.next;
            b = b.next;
        }
        return null;
    }
    public int getMore(ListNode a) {
        int more = 0;
        while (a != null) {
            a = a.next;
            more++;
        }
        return more;
    }

    // approach 2:

    // okay, this approach is smart, and no need to calculate difference in len
    // The basic idea is to move two pointers together and when one node get to
    // the end, reset it to the start of the other list, same applies for the
    // other node as well. In this way, during the second iteration, two pointers
    // will have the same rest of the length to traverse.

    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        // the return node is either the intersection node or null
        return a;
    }
}
