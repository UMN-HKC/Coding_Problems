package Leetcode;

import java.util.Arrays;

public class P1029_TwoCityScheduling {

    // approach 1: greedy

    // 假设所有的人都选择城市A， 这时候sum=sum{a[i][0]},
    // 然后要选择一半的人改成B， 这个时候, 选择某一个人对sum的影响是d=a[i][1]-a[i][0],
    // 那么， 我们要让结果最小， 就需要让这个d最小， 那按照这个d对数组排序，然后选择最小的一半就好

    public int twoCitySchedCost(int[][] costs) {

        Arrays.sort(costs, ((a, b) -> (a[1] - a[0]) - (b[1] - b[0])));
        int total = 0;
        for (int i = 0; i < costs.length; i++) {
            total += i >= costs.length / 2 ? costs[i][0] : costs[i][1];
        }
        return total;
    }
}
