package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0488_ZumaGame {

    // approach 1: dfs

    // The basic idea is that for each board string, we will iterate through and for
    // each possible removable contiguous characters, we remove them and recurse on the
    // new string. The base case is that once the passed in board string is an empty string
    // which means we have successfully finished the game, so we update the result step.

    // time: O((n + m)^m * (n^2)), n = 20, m = 5
    // 在board上面选个位子插入我们的球，每个都是独立的时间，所以总共有25^5的插入方法
    // n^2是removeConsecutive方法的时间复杂度
    // space: O(m * n) m是递归深度，在每一层我们都会创建n长度的string

    int minSteps = Integer.MAX_VALUE;
    public int findMinStep(String board, String hand) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : hand.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        dfs(board, map, 0);
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }
    public void dfs(String board, Map<Character, Integer> map, int cnt) {
        // before doing anything, we need to clean the board(remove 3 and more contiguous characters)
        board = removeConsecutive(board);
        if (board == null || board.length() == 0) {
            minSteps = Math.min(minSteps, cnt);
            return;
        }
        int i = 0, j = 0;
        while (i < board.length()) {
            char ch = board.charAt(i);
            while (j < board.length() && board.charAt(j) == ch) j++;
            if (map.containsKey(ch)) {
                int need = 3 - (j - i);
                if (map.get(ch) >= need) {
                    map.put(ch, map.get(ch) - need);
                    String newStr = board.substring(0, i) + board.substring(j);
                    dfs(newStr, map, cnt + need);
                    map.put(ch, map.get(ch) + need);
                }
            }
            i = j;
        }
    }
    private String removeConsecutive(String board) {
        int i = 0, j = 0;
        while (j < board.length()) {
            while (j < board.length() && board.charAt(i) == board.charAt(j)) {
                j++;
            }
            if (j - i >= 3) return removeConsecutive(board.substring(0, i) + board.substring(j));
            else {
                i = j;
            }
        }
        return board;
    }
}
