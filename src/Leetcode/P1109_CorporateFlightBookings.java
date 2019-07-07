package Leetcode;

public class P1109_CorporateFlightBookings {

    // approach 1: O(n^2)

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] booking : bookings) {
            int from = booking[0];
            int to = booking[1];
            for (int i = from; i <= to; i++) {
                res[i - 1] += booking[2];
            }
        }
        return res;
    }

    // approach 2: O(n)
    // The basic idea is that we will have two for loops:
    // The first one, we add seats for each starting.
    // flight, and release(subtract) seats for the flight which is right after the end flight in advance
    // after this loop, some flight might have negative reserved seats in progress. :(
    // For the second loop, we will add reserved seats to the current flight from its previous flight.
    // This will add number of reserved seats to flights after the start flight of each booking.
    // Will we add extra seats to unrelated flight? Yes. But those extra seats have been released in
    // advance in our first loop.

    public int[] corpFlightBookings_linear(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int[] booking = bookings[i];
            res[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                res[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
