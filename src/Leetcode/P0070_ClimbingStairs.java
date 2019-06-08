package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0070_ClimbingStairs {

    // Approach 1: Naive approach (TLE)
    public int climbStairs_naive(int n) {
        if (n <= 2) return n;
        return climbStairs_naive(n - 1) + climbStairs_naive(n - 2);
    }

    // Approach 2:
    // recursion with memoization(top-down)
    Map<Integer, Integer> map = new HashMap<>();
    public int climbStairs_mem(int n) {
        if (n <= 2) return n;
        else {
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int steps = climbStairs_mem(n - 1) + climbStairs_mem(n - 2);
            map.put(n, steps);
            return steps;
        }
    }

    // Aproach 3: DP (bottom-up)
    public int climbStairs_dp(int n) {
        if (n == 0) return 0;
        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;
        for (int i = 2; i <= n; i++) {
            steps[i] = steps[i-1] + steps[i-2];
        }
        return steps[n];
    }

    // Approach 4: optimize space
    public int climbStairs_two_variables(int n) {
        if (n == 0) return 0;
        int lastOne = 1;
        int lastTwo = 1;
        for (int i = 2; i <= n; i++) {
            int temp = lastOne;
            lastOne += lastTwo;
            lastTwo = temp;
        }
        return lastOne;
    }
}
