package Leetcode;
import java.io.BufferedWriter;
import java.io.ObjectOutputStream;
import java.util.*;

public class P0332_ReconstructItinerary {

    // approach 1: brute force (graph dfs with backtracking)
    // time: O(n!) like permutation, say we have n tickets and in the worst case
    //             we choose 1 ticket and next time, we have (n - 1) choice
    // space: O(n)

    int stops = 1;
    public List<String> findItinerary_1(List<List<String>> tickets) {
        BufferedWriter
        Map<String, List<String>> map = new HashMap<>();
        buildGraph(map, tickets);
        for (List<String> list : map.values()) {
            // since we want it in lexical order, we sort all outgoing edges first
            Collections.sort(list);
        }
        List<String> res = new ArrayList<>();
        res.add("JFK");
        if (dfs(res, map, "JFK")) {
            return res;
        }
        return res;
    }
    public boolean dfs(List<String> res, Map<String, List<String>> map, String cur) {
        if (res.size() == stops) {
            return true;
        }
        if (!map.containsKey(cur)) {
            return false;
        }
        List<String> tos = map.get(cur);
        for (int i = 0; i < tos.size(); i++) {
            String des = tos.get(i);
            tos.remove(i);
            res.add(des);
            if (dfs(res, map, des)) {
                return true;
            }
            tos.add(i, des);
            res.remove(res.size() - 1);
        }
        return false;
    }
    public void buildGraph(Map<String, List<String>> map, List<List<String>> tickets) {
        for (List<String> list : tickets) {
            String from = list.get(0);
            String to = list.get(1);
            List<String> des = new ArrayList<>();
            if (!map.containsKey(from)) {
                des.add(to);
            }
            else {
                des = map.get(from);
                des.add(to);
            }
            map.put(from, des);
            stops++;
        }
    }

    // approach 2: (HierHolzer's alg)

    // youtube video on finding Eulerian Path: https://www.youtube.com/watch?v=8MpoO2zA2l4

    // The problem is in fact finding the Eulerian path. Since the problem guarantees that we
    // will have at least one Eulerian path, it saves us time to verify it and our task is
    // to apply hierholzer algorithm to find the eulerian path.

    // The key is to realize that when there's no more edge in our priority queue
    // in the current recursion call, it means we have finished visiting our destination
    // or running into a dead end. We then need to add the starting city to the front of
    // the result list in the current stack.

    // The nodes which have odd degrees (int and out) are the entrance or exit. In your example it's JFK and A.
    // If there are no nodes have odd degrees, we could follow any path without stuck until hit the last exit node
    // The reason we got stuck is because that we hit the exit

    // time: O(nlogn)
    // Hierholzer: O(n)
    // buildGraph O(nlogn)
    // space: O(n)

    public List<String> findItinerary_2(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        buildGraph(map, tickets);
        LinkedList<String> res = new LinkedList<>();
        dfs(map, res,"JFK");
        return res;
    }
    public void dfs(Map<String, PriorityQueue<String>> map, LinkedList<String> res, String cur) {

        // we do not return directly when cur is not in the map
        // because cur might be the end des and we will need to add
        // it to the result, so we check it in the while loop

        PriorityQueue<String> pq = map.get(cur);
        while(pq != null && !pq.isEmpty()) {
            dfs(map, res, pq.poll());
        }
        res.addFirst(cur);
    }
    public void buildGraph(Map<String, PriorityQueue<String>> map, List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)) {
                PriorityQueue<String> pq = new PriorityQueue<>();
                map.put(from, pq);
            }
            map.get(from).offer(to);
        }
    }
}
