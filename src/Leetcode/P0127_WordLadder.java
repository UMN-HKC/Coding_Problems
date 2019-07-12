package Leetcode;
import java.util.*;

public class P0127_WordLadder {

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
}
