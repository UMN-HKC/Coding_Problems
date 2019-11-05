package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0932_BeautifulArray {

    // approach 1: math
    // link: https://leetcode.com/problems/beautiful-array/discuss/186679/Odd-%2B-Even-Pattern-O(N)
    // First: divide and conquer, why to divide to odd and even part (or merge odd and even part together)?
    // that’s say, we have two part: odd = {1, 5, 3}, even = {2, 4, 6}
    // any number from odd part and any number from even part will alway obey the rule A[k] * 2 != A[i] + A[j]
    // Ex: 5 from odd part, 6 from even part, for any k between 5 and 6, A[k] * 2 != 5 + 6
    // So merge two parts will form beautiful array!
    //
    // Next, we need to make sure the odd and even part are beautiful arrays!
    // Second: how to find beautiful array that contains only odd (even) number?
    // as the beautiful array properties that Lee mentioned, Addition and Multiplication
    //We can get the odd/even beautiful array from previous beautiful array by addition and multiplication

    // - Addition
    // If we have A[k] * 2 != A[i] + A[j],
    // (A[k] + x) * 2 = A[k] * 2 + 2x != A[i] + A[j] + 2x = (A[i] + x) + (A[j] + x)
    // E.g: [1,3,2] + 1 = [2,4,3].
    //
    // - Multiplication
    // If we have A[k] * 2 != A[i] + A[j],
    // for any x != 0,
    // (A[k] * x) * 2 = A[k] * 2 * x != (A[i] + A[j]) * x = (A[i] * x) + (A[j] * x)

    public int[] beautifulArray(int N) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        while (list.size() < N) {
            List<Integer> temp = new ArrayList<>();
            for (int num : list) {
                if (num * 2 - 1 <= N) temp.add(num * 2 - 1);
            }
            for (int num : list) {
                if (num * 2 <= N) temp.add(num * 2);
            }
            list = temp;
        }
        int[] res = new int[N];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }
}
