package Leetcode;
import java.util.*;

public class P0127_WordLadder {

    // huahua's video helpes: https://www.youtube.com/watch?v=vWPCm69MSfs&t=822s

    // approach 1: bfs

    // n: # of words in dictionary; l: length of word
    // time: O(n * l * 26)
    // space: O(n)

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int min = Integer.MAX_VALUE;
        int cnt = 2;
        Queue<String> queue = new LinkedList<>();
        Set<String> dict = new HashSet<>(wordList);
        // add endWord to the dict so we can detect it directly when checking dict
        queue.offer(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String curWord = queue.poll();
                for (int i = 0; i < curWord.length(); i++) {
                    char old = curWord.charAt(i);
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != old) {
                            String newWord = curWord.substring(0,i) + c + curWord.substring(i+1);
                            if (dict.contains(newWord)) {
                                if (newWord.equals(endWord)) {
                                    return cnt;
                                }
                                else {
                                    dict.remove(newWord);
                                    queue.offer(newWord);
                                }
                            }
                        }
                    }
                }
            }
            cnt++;
        }
        return 0;
    }

    // approach 2: two-ended bfs
    // use set to simulate queue because we want to check whether two ends meet in O(1) time

    public int ladderLength_two_end_bfs(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        int cnt = 1;
        Set<String> dict = new HashSet<>(wordList);
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        set1.add(beginWord);
        set2.add(endWord);
        while (set1.size() != 0 && set2.size() != 0) {
            cnt++;
            if (set2.size() < set1.size()) {
                Set<String> temp = new HashSet<>();
                temp = set1;
                set1 = set2;
                set2 = temp;
            }
            Set<String> temp = new HashSet<>();
            for (String s : set1) {
                char[] word = s.toCharArray();
                for (int i = 0; i < word.length; i++) {
                    char old = word[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (word[i] != c) {
                            word[i] = c;
                        }
                        String newWord = new String(word);
                        if (set2.contains(newWord)) {
                            return cnt;
                        }
                        if (dict.contains(newWord)) {
                            temp.add(newWord);
                            dict.remove(newWord);
                        }
                    }
                    word[i] = old;
                }
            }
            set1 = temp;
        }
        return 0;
    }
}
