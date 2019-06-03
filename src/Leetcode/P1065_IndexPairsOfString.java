package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1065_IndexPairsOfString {

    // initial approach: brute force

    // time: O((text.length)^2)
    // space: O(number of words)

    public int[][] indexPairs(String text, String[] words) {
        // PriorityQueue<int[]> pq = new PriPriorityQueue<>();
        List<int[]> res = new ArrayList<>();
        Set<String> dic = new HashSet<>();
        for (String word : words) {
            dic.add(word);
        }
        for (int i = 0; i < text.length(); i++) {
            for (int j = i; j < text.length(); j++) {
                if (dic.contains(text.substring(i, j+1))) {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    res.add(temp);
                }
            }
        }
        int[][] result = new int[res.size()][2];
        for (int i = 0; i < result.length; i++) {
            int[] temp = res.get(i);
            result[i][0] = temp[0];
            result[i][1] = temp[1];
        }
        return result;
    }
}
