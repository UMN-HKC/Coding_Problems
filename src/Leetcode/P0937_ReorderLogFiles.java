package Leetcode;
import java.util.*;

public class P0937_ReorderLogFiles {

    // Initial approach:

    // The basic idea is to use a priority queue(max heap) to order our letter strings
    // and use a linked list to act as an intermediate store. Each time we encounter
    // a digit stringl, we put it into our linkedlist. After traversing all logs,
    // we insert strings in our max heap into the front of the linked list.

    // time: O(max(mlogm, m+n) assuming we have m letter strings and n digit strings
    // space: O(m)

    public String[] reorderLogFiles(String[] logs) {
        PriorityQueue<String> pq = new PriorityQueue<>(new myComparator());
        LinkedList<String> list = new LinkedList<>();
        String[] res = new String[logs.length];
        for (String log : logs) {
            char c = log.charAt(log.indexOf(" ")+1);
            if (c <= 'z' && c >= 'a') {
                pq.offer(log);
            }
            else {
                list.add(log);
            }
        }
        while (!pq.isEmpty()) {
            list.addFirst(pq.poll());
        }
        return list.toArray(new String[0]);
    }
    class myComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            int idx1 = s1.indexOf(" ") + 1;
            int idx2 = s2.indexOf(" ") + 1;
            if (s1.substring(idx1).equals(s2.substring(idx2))) {
                return s2.substring(0, idx2).compareTo(s1.substring(0, idx1));
            }
            else {
                return s2.substring(idx2).compareTo(s1.substring(idx1));
            }
        }
    }
}
