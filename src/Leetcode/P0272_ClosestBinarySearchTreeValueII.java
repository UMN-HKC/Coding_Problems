package Leetcode;
import java.util.*;

public class P0272_ClosestBinarySearchTreeValueII {

    // approach 1: max heap
    // The basic idea is to use max heap which store the nodes' value, with value whose
    // difference with the target being greatest at the top of the heap.
    // Use any kind of tree traversal to add nodes' value to the queue, when queue size
    // becomes k, we can compare difference between current node's value and target with the top
    // one in the heap (that's why we use max heap).

    // time: O(NlogK)
    // space: O(K)

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
                (a, b) -> (int)Math.abs(target - (int)b) - (int)Math.abs(target - (int)a));
        helper(root, pq, k, target);
        return new ArrayList<Integer>(pq);
    }
    public void helper(TreeNode node, PriorityQueue<Integer> pq, int k, double target) {
        if (node == null) return;
        if (pq.size() < k) {
            pq.offer(node.val);
        }
        else {
            if (Math.abs(target - (int)pq.peek()) > Math.abs(target - node.val)) {
                pq.poll();
                pq.offer(node.val);
            }
        }
        helper(node.left, pq, k, target) ;
        helper(node.right, pq, k, target);
    }

    // approach 2: convert to array and find the k closest elements to target
    // brute force: convert bst to array and then binary search the closest value and then expand
    // to two sides, and each time add a closer element to the target from two sides until we add k elements

    // time: O(n)
    // space: O(n)

    public List<Integer> closestKValues_converttoarray(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        convertToList(root, list);
        int[] bst = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bst[i] = list.get(i);
            System.out.println(bst[i]);
        }

        int index = binarySearch(target, bst);
        int l = index, r = index + 1;
        while ((l >= 0 || r < bst.length) && k > 0) {
            if (l >= 0 && r < bst.length) {
                if (Math.abs(target - bst[l]) < Math.abs(target - bst[r])) {
                    res.add(bst[l]);
                    l--;
                }
                else {
                    res.add(bst[r]);
                    r++;
                }
            }
            else if (l >= 0) {
                res.add(bst[l]);
                l--;
            }
            else {
                res.add(bst[r]);
                r++;
            }
            k--;
        }
        return res;
    }
    public int binarySearch(double target, int[] bst) {
        int start = 0, end = bst.length - 1;
        int nearest = Integer.MAX_VALUE;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (Math.abs(target - bst[mid]) > Math.abs(target - bst[end])) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }
    public void convertToList(TreeNode node, List<Integer> list) {
        if (node == null) return;
        convertToList(node.left, list);
        list.add(node.val);
        convertToList(node.right, list);
    }

    // approach 3: deque
    // The basic idea is to use inorder traversal to maintain bst sequence and store nodes' value
    // to a double-ended queue. Then, we can just from two ends until the size of queue becomes k
    // which means we have the k closest elements now. When polling from two ends, always poll
    // the greater difference with the target first

    // time: O(n)
    // space: O(n)

    public List<Integer> closestKValues_deque(TreeNode root, double target, int k) {
        Deque<Integer> dq = new LinkedList<>();
        inorder(root, dq);
        while (dq.size() > k) {
            if (Math.abs(target - dq.peekFirst()) < Math.abs(target - dq.peekLast())) {
                dq.pollLast();
            }
            else {
                dq.pollFirst();
            }
        }
        return new ArrayList(dq);
    }
    public void inorder(TreeNode node, Deque<Integer> dq) {
        if (node == null) return;
        inorder(node.left, dq);
        dq.add(node.val);
        inorder(node.right, dq);
    }
}
