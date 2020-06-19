package Leetcode;

import java.util.*;

public class P0787_CheapestFlightWithinKStops {

    // approach 1: Dijkstra
    // The basic idea is to build the graph and apply dijkstra to find the
    // smallest cost going from src to dst. We use priority queue to store
    // pair[to, price, stop] in terms of price in increasing order.
    // Note that when using pq to perform dijkstra, we may have same stops
    // existing in the queue but with different price.
    // Note that we do not need visited boolean array to keep track of visited
    // cities, since we might need to actually revisit a city that has been
    // visited earlier.
    // Also note, we only push a stop to the queue when its count of stops is within K

    // time: O(ElogV)
    // space: O(V)

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(new int[]{to, cost});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{src, 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCity = cur[0];
            int curStops = cur[1];
            int curCost = cur[2];
            if (curCity == dst) {
                return curCost;
            }
            if (map.containsKey(curCity)) {
                for (int[] next : map.get(curCity)) {
                    int nextCity = next[0], cost = next[1];
                    if (curStops <= K) {
                        pq.offer(new int[]{nextCity, curStops + 1, curCost + cost});
                    }
                }
            }
        }
        return -1;
    }
}
