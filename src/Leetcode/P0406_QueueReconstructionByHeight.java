package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0406_QueueReconstructionByHeight {

    // approach 1: greedy, sort

    // The key is to realize that k value for each person is only related
    // with people that are of greater height than him/her. So we sort the
    // array by height and if two people's height equal to each other, smaller
    // index comes first. After that, we can just insert people to result list
    // by their k value. Because taller people are inserted first, the following
    // shorter people's k value is exactly the position he/she needs to be inserted
    // to satisfy the condition.

//    E.g.
//            input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//    subarray after step 1: [[7,0], [7,1]]
//    subarray after step 2: [[7,0], [6,1], [7,1]]
//                            ...

    // time: O(n^2)
    // space: O(n)

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], person);
        }
        return list.toArray(new int[0][0]);
    }
}
