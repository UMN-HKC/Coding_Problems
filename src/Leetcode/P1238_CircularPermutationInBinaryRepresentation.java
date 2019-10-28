package Leetcode;

import java.util.*;

public class P1238_CircularPermutationInBinaryRepresentation {

    // approach 1: based on gray code

    // Simply copy paste gray code solution. Then find the start position in the gray
    // code permutation, iterate all numbers from there.

    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = grayCode(n);
        int s = 0;
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i) == start) {
                s = i;
                break;
            }
        }
        List<Integer> orderedRes = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            s = s%res.size();
            orderedRes.add(res.get(s));
            s++;
        }
        return orderedRes;
    }
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) | (1<<i));
            }
        }
        return res;
    }
}
