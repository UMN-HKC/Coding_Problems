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

    // approach 2: merge sort
    // https://leetcode.com/problems/queue-reconstruction-by-height/discuss/143403/O(nlogn)-modified-merge-sort-solution-with-detailed-explanation

    // time: O(nlogn)
    // space: O(n)

    public int[][] reconstructQueue_2(int[][] people) {

        int n = people.length;
        int[][] indexAndRestJumps = new int[n][2];
        for (int i = 0; i < n; i++) {
            indexAndRestJumps[i][0] = i;
            indexAndRestJumps[i][1] = people[i][1];
        }
        Arrays.sort(indexAndRestJumps, (a, b) -> people[a[0]][0] == people[b[0]][0] ?
                b[1] - a[1] : people[a[0]][0] - people[b[0]][0]);

        mergeSort(people, indexAndRestJumps, 0, n - 1);

        int[][] res = new int[n][2];
        for (int i = 0; i < n; i++) {
            int idx = indexAndRestJumps[i][0];
            res[i][0] = people[idx][0];
            res[i][1] = people[idx][1];
        }
        return res;

    }
    private void mergeSort(int[][] people, int[][] indexAndRestJumps, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(people, indexAndRestJumps, l, m);
        mergeSort(people, indexAndRestJumps, m + 1, r);
        merge(people, indexAndRestJumps, l, m, m + 1, r);
    }

    private void merge(int[][] people, int[][] indexAndRestJumps, int l1, int r1, int l2, int r2) {
        int len = (r1 - l1) + (r2 - l2) + 2 ;
        int[][] res = new int[len][2];
        int s1 = l1, s2 = l2, e1 = r1, e2 = r2;
        int i = 0;
        int jumps = 0;
        while (l1 <= r1 || l2 <= r2) {
            if (l1 <= r1 && l2 <= r2) {
                if (indexAndRestJumps[l1][1] - jumps < indexAndRestJumps[l2][1]) {
                    indexAndRestJumps[l1][1] -= jumps;
                    res[i][0] = indexAndRestJumps[l1][0];
                    res[i][1] = indexAndRestJumps[l1][1];
                    l1++;
                }
                else if (indexAndRestJumps[l1][1] - jumps > indexAndRestJumps[l2][1]) {
                    res[i][0] = indexAndRestJumps[l2][0];
                    res[i][1] = indexAndRestJumps[l2][1];
                    // jump to l1's left
                    jumps++;
                    l2++;
                }
                // if equal jumps, insert shorter one
                else {
                    int idxOfL1 = indexAndRestJumps[l1][0];
                    int idxOfL2 = indexAndRestJumps[l2][0];
                    if (people[idxOfL1][0] < people[idxOfL2][0]) {
                        indexAndRestJumps[l1][1] -= jumps;
                        res[i][0] = indexAndRestJumps[l1][0];
                        res[i][1] = indexAndRestJumps[l1][1];
                        l1++;
                    }
                    else {
                        res[i][0] = indexAndRestJumps[l2][0];
                        res[i][1] = indexAndRestJumps[l2][1];
                        jumps++;
                        l2++;
                    }
                }
            }
            else if (l1 <= r1) {
                indexAndRestJumps[l1][1] -= jumps;
                res[i][0] = indexAndRestJumps[l1][0];
                res[i][1] = indexAndRestJumps[l1][1];
                l1++;
            }
            else {
                res[i][0] = indexAndRestJumps[l2][0];
                res[i][1] = indexAndRestJumps[l2][1];
                l2++;
            }
            i++;
        }
        for (int j = 0; j < len; j++) {
            indexAndRestJumps[s1+j][0] = res[j][0];
            indexAndRestJumps[s1+j][1] = res[j][1];
        }
    }


}
