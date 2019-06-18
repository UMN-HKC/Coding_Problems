package Leetcode;

public class P0038_CountAndSay {

    // the key point is to understand the problem statement
//            1.     1
//            2.     11
//            3.     21
//            4.     1211
//            5.     111221

    // brute force: 实现题

    public String countAndSay(int n) {
        if (n == 1) return "1";
        String[] strs = new String[n];
        strs[0] = "1";
        for (int i = 1; i < n; i++) {
            int cnt = 0;
            int itr = 0;
            String last = strs[i - 1];
            char prev = last.charAt(0);
            String cur = "";
            while (itr < last.length()) {
                while (itr < last.length() && last.charAt(itr) == prev) {
                    cnt++;
                    itr++;
                }
                cur += String.valueOf(cnt) + prev;
                // remember to reset cnt to 0 and prev to the current character
                if (itr < last.length()) {
                    prev = last.charAt(itr);
                    cnt = 0;
                }
            }
            strs[i] = cur;
        }
        return strs[strs.length - 1];
    }
}
