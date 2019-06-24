package Leetcode;
import java.util.*;

public class P1094_CarPooling {

    // idea borrowed from discussion board
    // Similar to Meeting Room II

    // time: O(nlogn)
    // space: O(n)

    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];
            // let previous passengers, who ends their trip before the current trip starts, get off the car
            while (!pq.isEmpty() && trip[1] >= pq.peek()[2]) {
                capacity += pq.poll()[0];
            }
            capacity -= trip[0];
            if (capacity < 0) {
                return false;
            }
            pq.offer(trip);
        }
        return true;
    }
}
