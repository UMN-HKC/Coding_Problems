package Leetcode;

import java.util.*;

public class P0787_CheapestFlightWithinKStops {

    // approach 1: Dijkstra
    // The basic idea is to build the graph and apply dijkstra to find the
    // smallest cost going from src to dst. We use priority queue to store
    // pair[to, price, stop] in terms of price in increasing order.
    // Note that when using pq to perform dijkstra, we may have same stops
    // existing in the queue but with different price. Also, a stop is considered
    // visited when it is popped out of the queue, and at that point we are also
    // sure that there's no other path to reach this stop that can come at an even
    // lower price.
    // Also note, we only push a stop to the queue when its count of stops is within K

    // time: O(ElogV)
    // space: O(V)

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < flights.length; i++) {
            int[] flight = flights[i];
            if (!map.containsKey(flight[0])) {
                map.put(flight[0], new ArrayList<>());
            }
            map.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        boolean[] visited = new boolean[n];
        K++;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{src, 0, 0});
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int cur = pair[0];
            int price = pair[1];
            int cnt = pair[2];
            visited[cur] = true;
            if (cur == dst) return price;
            if (map.containsKey(cur)) {
                for (int[] next : map.get(cur)) {
                    if (!visited[next[0]] && cnt + 1 <= K) {
                        int newDes = next[0];
                        int newPrice = next[1] + price;
                        pq.offer(new int[]{newDes, newPrice, cnt + 1});
                    }
                }
            }
        }
        return -1;
    }
}
