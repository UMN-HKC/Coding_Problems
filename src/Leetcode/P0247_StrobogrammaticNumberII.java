package Leetcode;
import java.util.*;

public class P0247_StrobogrammaticNumberII {

    // initial approach: iterative
    // first check if odd number and add single digit if so
    // next, loop for n / 2 times and append strobogrammatic pair to left and right

    public List<String> findStrobogrammatic(int n) {
        List<String> res = null;
        // add single digit first if n is odd
        if (n % 2 != 0) {
            res = Arrays.asList(new String[]{"0", "1", "8"});
            n = n - 1;
        }
        n /= 2;
        // if n is even, we initialize the result list first
        if (res == null) {
            res = Arrays.asList(new String[]{""});
        }
        for (int i = 0; i < n; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < res.size(); j++) {
                String s = res.get(j);
                // 0 cannot be appended at front
                if (i != n - 1) {
                    temp.add("0" + s + "0");
                }
                temp.add("1" + s + "1");
                temp.add("8" + s + "8");
                temp.add("6" + s + "9");
                temp.add("9" + s + "6");
            }
            res = new ArrayList<>(temp);
        }
        return res;
    }
}
