package Leetcode;

public class P0243_ShortestWordDistance {

//    the simplified version is shorter and more concise than my initial solution,
//    though the idea is similar
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

    public int shortestDistance_simplified(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                p1 = i;

            if (words[i].equals(word2))
                p2 = i;

            if (p1 != -1 && p2 != -1)
                min = Math.min(min, Math.abs(p1 - p2));
        }

        return min;
    }

}
