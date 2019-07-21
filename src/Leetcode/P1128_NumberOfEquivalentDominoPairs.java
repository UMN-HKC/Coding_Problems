package Leetcode;
import java.util.*;

public class P1128_NumberOfEquivalentDominoPairs {

    // couldn't figure out initially
    // the basic idea is to use map to store encoded pairs. For example, [1,2] and [2,1]
    // is a domino pair, so we can encode them both as "12". So next time, we encounter
    // a [1,2] or [2,1], we add the current frequency of "12" directly to our result
    // because the new pair can be equivalent to all the pairs decoded from "12".

    public int numEquivDominoPairs(int[][] dominoes) {
        if (dominoes == null || dominoes.length == 0) return 0;
        Map<String, Integer> map = new HashMap<>();
        int res = 0;

        for (int i = 0; i < dominoes.length; i++) {
            StringBuilder sb = new StringBuilder();
            int[] domino = dominoes[i];
            if (domino[0] < domino[1]) {
                sb.append(domino[0]).append(domino[1]);
            }
            else {
                sb.append(domino[1]).append(domino[0]);
            }
            String s = sb.toString();
            res += map.getOrDefault(s, 0);
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return res;
    }
}
