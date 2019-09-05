package Leetcode;

import java.util.*;

public class P0241_DifferentWaysToAddParentheses {

    // approach 1: divide and conquer with memoization

    // The idea is to apply divide-and-conquer thinking to recursively solve the
    // problem. The key point here is to look for operators. We iterate through
    // the string and for each operator we find, we know that its left and right
    // strings are its operands. So we could recursively call the function itself
    // and pass these two substrings as its new parameters.
    // We also optimized the recursion with memoization using hashmap.

    // time: O(2^n) it is actually catalan number: http://people.math.sc.edu/howard/Classes/554b/catalan.pdf

    Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input == null || input.length() == 0) return res;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> list1 = map.containsKey(left) ? map.get(left) : diffWaysToCompute(input.substring(0, i));
                List<Integer> list2 = map.containsKey(right) ? map.get(right) : diffWaysToCompute(input.substring(i + 1));
                for (int op1 : list1) {
                    for (int op2 : list2) {
                        switch (c) {
                            case '+':
                                res.add(op1 + op2);
                                break;
                            case '-':
                                res.add(op1 - op2);
                                break;
                            default:
                                res.add(op1 * op2);
                                break;
                        }
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.parseInt(input));
        }
        map.put(input, res);
        return res;
    }
}
