package Leetcode;
import java.util.*;

public class P0060_PermutationSequence {

    // couldn't figure out initially
    // this link explains in great detail: https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)

    // the basic idea is to utilize permutation and its relation with factorial number to build up our result.
    // (we know that sequence with n will have n! permutations)
    // To make it more concrete, or example if we have n = 4 where we will have sequence as the following, and say
    // we wanna find k=14th sequence in the following(we will set k = k - 1 to make it work for array index).
    // 1 {2, 3, 4}
    // 2 {1, 3, 4}
    // 3 {1, 2, 4}
    // 4 {1, 2, 3}
    // What we will do in this approach is to locate the first digit in the sequence:
    // digit=k/(n-1)!  =>  this will rule out all sequences before k in this level.
    // Specifically, it means all sequence numbers starting with 1 and 2 will be ruled out since the number
    // of all their permutation is 2*(3!)==2*(3*2)==12, which is less than 13th

    // time: O(1) since n is at most 9 so the list size is at most 9
    // space: O(1)

    public String getPermutation(int n, int k) {
        String res = "";
        int[] facLookup = new int[n + 1];
        List<Integer> list = new ArrayList<>();

        // build look up table
        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                facLookup[i] = 1;
            }
            else {
                facLookup[i] = i * facLookup[i-1];
            }
        }
        // build list
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        // work with array index
        k--;
        while (list.size() != 0) {
            int idx = k/facLookup[n-1];
            res += list.get(idx);
            k -= idx * facLookup[n-1];
            list.remove(idx);
            n--;
        }
        return res;
    }
}
