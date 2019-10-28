package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1237_FindPositiveIntegerSolutionforAGivenEquatioN {

    // approach 1: binary search

    // The basic idea is that, since we know the range of inputs, we can iterate the range
    // and every time fix one input, and binary search on the second input. Move range based
    // on the result we get from the function

    public List<List<Integer>> findSolution(CustomFunction f, int z) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            int s = 1, e = 1000;
            while (s <= e) {
                int m = s + (e - s) / 2;
                if (f.f(i, m) == z) {
                    res.add(new ArrayList(Arrays.asList(i, m)));
                    break;
                }
                else if (f.f(i, m) > z) {
                    e = m - 1;
                }
                else {
                    s = m + 1;
                }
            }
        }
        return res;
    }
}
