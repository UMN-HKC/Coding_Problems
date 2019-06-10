package Leetcode;

import java.util.Arrays;

public class P1081_LetterTilePossibilities {

    public int numTilePossibilities(String tiles) {
        if (tiles == null || tiles.length() == 0) {
            return 0;
        }
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        int[] res = {0};
        boolean[] visited = new boolean[chars.length];
        dfs(chars, visited, res);
        return res[0];
    }
    public void dfs(char[] tiles, boolean[] visited, int[] res) {
        for (int i = 0; i < tiles.length; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && tiles[i] == tiles[i-1])) continue;
            visited[i] = true;
            res[0]++;
            dfs(tiles, visited, res);
            visited[i] = false;
        }
        return;
    }
}
