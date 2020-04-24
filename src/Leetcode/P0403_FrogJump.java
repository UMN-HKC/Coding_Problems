package Leetcode;

import java.util.*;

public class P0403_FrogJump {

    // approach 1: hashmap

    // the basic idea is to start from first stone and populate next stones where
    // the frog can jump to from the current stone with all the steps it can take
    // at next stones.

    // time: O(n^2)
    // space: O(n^2)

    public static void main(String[] args) {
        int[] stones = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones));
    }

    public static boolean canCross(int[] stones) {
        int n = stones.length;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
        map.put(0, new HashSet<Integer>());
        map.get(0).add(1);
        for (int i = 1; i < n; i++) {
            map.put(stones[i], new HashSet<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int reach = stones[i] + step;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                HashSet<Integer> steps = map.get(reach);
                if (steps != null) {
                    // this is essential, otherwise we would get ConcurrentModificationException
                    // because step - 1 might become reach again
                    if (step - 1 > 0)
                        steps.add(step - 1);
                    steps.add(step);
                    steps.add(step + 1);
                }


            }
        }
        return map.get(stones[n - 1]).size() != 0;
    }
}
