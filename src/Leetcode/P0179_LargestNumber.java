package Leetcode;
import java.util.*;

public class P0179_LargestNumber {

    // need to come back to it later

    // used String built-in compareTo function
    // failed to implement it myself

    public String largestNumber(int[] nums) {
        PriorityQueue<String> pq = new PriorityQueue<String>(new numComparator());
        String res = "";
        for (int num : nums) {
            pq.offer(String.valueOf(num));
        }
        while (!pq.isEmpty()) {
            res += pq.poll();
        }
        if (res.charAt(0) == '0') return "0";
        return res;
    }
    class numComparator implements Comparator<String> {
        public int compare(String str1, String str2) {

            String s1 = str1 + str2;
            String s2 = str2 + str1;
            return s2.compareTo(s1); // reverse order here, so we can do append() later

            // if (s1.length() > s2.length()) {
            //     return compare(s2, s1);
            // }
            // int i = 0;
            // while (i < s1.length()) {
            //     if (s1.charAt(i) > s2.charAt(i)) {
            //         return -1;
            //     }
            //     else if (s1.charAt(i) < s2.charAt(i)) {
            //         return 1;
            //     }
            //     i++;
            // }
            // return s1.length() == s2.length() && i == s1.length() ? 0 : s2.charAt(i) - s1.charAt(0);
        }
    }
}
