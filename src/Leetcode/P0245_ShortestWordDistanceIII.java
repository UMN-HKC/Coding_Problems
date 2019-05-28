package Leetcode;

public class P0245_ShortestWordDistanceIII {

    // my initial solution for p243 actually deals with word1 and word2 being same, so I
    // will just use that as a general solution for both p243 and p245

    public int shortestDistance_initial(String[] words, String word1, String word2) {
        if (words.length == 2) {
            return 1;
        }
        int len = words.length;
        int l = 0;
        int r = 1;
        int res = Integer.MAX_VALUE;
        while (l < len && r < len) {
            // match
            if ((words[l].equals(word1) && words[r].equals(word2)
                    || (words[l].equals(word2) && words[r].equals(word1)))) {
                res = Math.min(res, r - l);
                l = r;
                r++;
            }
            // left word not match
            else if ((!words[l].equals(word1) && !words[l].equals(word2)) || words[l].equals(words[r])) {
                l = r;
                r++;
            }
            // right word not match
            else if (!words[r].equals(word1) && !words[r].equals(word2)) {
                r++;
            }
            else {
                l++;
                r++;
            }
        }
        return res;
    }
}
