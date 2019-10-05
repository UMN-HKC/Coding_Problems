package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0166_FractionToRecurringDecimal {


    // approach 1: brute force (pay attention to edge cases, such as sign, overflow, etc)
    // deal with the sign -> convert to long type -> record while number part -> deal with fractional part
    // note that when dealing with fractional part, we use map to store mod value instead of div value
    // because div value can appear multiple times, such as 0.0005 but mod value indicates if we have
    // encountered repeating part indeed.

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
