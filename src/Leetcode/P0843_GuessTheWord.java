package Leetcode;

import java.util.*;

public class P0843_GuessTheWord {

    /**
     * // This is the Master's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface Master {
     *     public int guess(String word) {}
     * }
     */

    // approach 1: minmax

    // each time we make a guess, we use the score returned by the guess() call. And iterate all current
    // words and add the same match score words to next level list, because secret word is among them.

    public void findSecretWord(String[] wordlist, Master master) {
        Random rd = new Random();
        String guess = wordlist[rd.nextInt(wordlist.length)];
        List<String> list = new ArrayList<>();
        for (String cur : wordlist) {
            list.add(cur);
        }
        int k = 10;
        while (k > 0) {
            int score = master.guess(guess);
            if (score == 6) break;
            List<String> next = new ArrayList<>();
            for (String word : list) {
                if (match(word, guess) == score) {
                    next.add(word);
                }
            }
            guess = next.get(rd.nextInt(next.size()));
            list = next;
            k--;
        }
    }
    public int match(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            if (s1.charAt(i) == s2.charAt(i)) cnt++;
        }
        return cnt;
    }
}
