package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0166_FractionToRecurringDecimal {


    // approach 1: brute force
    // just implement it. The whole number part and then the fractional part.
    // and pay attention to edge cases, such as sign, overflow, etc

    public String fractionToDecimal(int numerator, int denominator) {
        // if numerator is 0, just return
        if (numerator == 0) return "0";
        // get the sign
        int sign = (numerator > 0 && denominator > 0 || (numerator < 0 && denominator < 0)) ? 1 : -1;
        // deal with overflow, so we init both numbers to long type
        // and use their absolute values to construct our string
        long n = Math.abs((long)numerator);
        long d = Math.abs((long)denominator);
        StringBuilder sb = new StringBuilder(sign == 1 ? "" : "-");
        // construct the whole number part
        sb.append(n / d);
        n %= d;
        if (n == 0) {
            return sb.toString();
        }
        // construct fractional part, and use hash map to
        // record appeared mod value and its position
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        map.put(n, sb.length());
        while (n != 0) {
            n *= 10;
            sb.append(n / d);
            if (map.containsKey(n % d)) {
                int idx = map.get(n % d);
                sb.insert(idx, "(").append(")");
                break;
            }
            else {
                n %= d;
                map.put(n, sb.length());
            }
        }
        return sb.toString();
    }
}
