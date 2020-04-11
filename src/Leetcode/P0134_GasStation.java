package Leetcode;

public class P0134_GasStation {


    // approach 1: brute force
    // The idea is to check for each starting point

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int tank = 0;
            for (int j = 0; j <= n; j++) {
                if (j == n) {
                    return i;
                }
                tank += gas[(i+j)%n];
                tank -= cost[(i+j)%n];
                if (tank < 0) break;
            }
        }
        return -1;
    }

    // approach 2: linear
    // youtube video that sparks the idea: https://www.youtube.com/watch?v=nTKdYm_5-ZY&t=1142s
    // The basic idea is to keep track of how much gas surplus we still have starting from
    // index, and how much deficit we have accumulated along the whole path. Each time
    // when surplus is not enough to let us arrive to next stop, we set index(new starting point)
    // to the next index and reset surplus for the next starting station.
    // Finally, after the loop we need to check if the surplus we have accumulated
    // is enough to cover the deficit for the whole trip.

    public int canCompleteCircuit_2(int[] gas, int[] cost) {
        int surplus = 0;
        int deficit = 0;
        int index = 0;
        int n = gas.length;

        for (int i = 0; i < n; i++) {
            if (gas[i] + surplus < cost[i]) {
                deficit += cost[i] - (gas[i] + surplus);  // keep track of the total deficit that we have aacumulated
                index = i + 1;  // choose the next index as new starting point
                surplus = 0;  // starting again from new station
            }
            else {
                // add rest of gas to our surplus
                surplus += gas[i] - cost[i];
            }
        }
        return surplus >= deficit ? index : -1;
    }
}
