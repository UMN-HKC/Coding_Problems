package Leetcode;
import java.util.*;

public class P0351_AndroidUnlockPatterns {

    // initial approach: slow
    public int numberOfPatterns_1(int m, int n) {
        Map<String, Character> map = new HashMap<>();
        // map lines to their covered key
        init(map);
        boolean[] visited = new boolean[10];
        int[] res = new int[]{0};
        for (int i = m; i <= n; i++) {
            // search for each target length
            dfs(map, res, new StringBuilder(), visited, i, i);
        }
        return res[0];
    }
    public void dfs(Map<String, Character> map, int[] res, StringBuilder sb, boolean[] visited, int len, int remain) {
        if (remain == 0 || len - sb.length() > remain) {
            if (sb.length() == len) {
                res[0]++;
            }
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (len - sb.length() > remain) {
                break;
            }
            // if visited, skip it
            if (visited[i]) {
                continue;
            }
            else {
                int length = sb.length();
                // choose the current key
                // if not the first key, we need to check if its previous key and this key
                // consist a line and whether that line is a valid line or not
                if (sb.length() != 0) {
                    char last = sb.charAt(sb.length() - 1);
                    String line = last + "" + i;
                    String revLine = i + "" + last;
                    if (map.containsKey(line + revLine) || map.containsKey(revLine + line)) {
                        char covered = map.containsKey(line+revLine) ? map.get(line+revLine) : map.get(revLine + line);
                        if (!visited[covered - '0']) {
                            continue;
                        }
                    }
                }
                sb.append(i);
                visited[i] = true;
                dfs(map, res, sb, visited, len, remain - 1);
                sb.setLength(length);
                visited[i] = false;
            }
        }

    }
    public void init(Map<String, Character> map) {
        map.put("1331", '2');
        map.put("1771", '4');
        map.put("1991", '5');
        map.put("2882", '5');
        map.put("3773", '5');
        map.put("3993", '6');
        map.put("4664", '5');
        map.put("7997", '8');
    }

    // approach 2: smart approach and fast
    // optimized by utilizing symmetric property and skip array
    // skip[i][j]: number that is covered by the line connected by number i and number j

    public int numberOfPatterns_2(int m, int n) {
        int res = 0;
        boolean[] visited = new boolean[10];
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = 5;
        for (int i = m; i <= n; i++) {
            res += dfs(skip, visited, i - 1, 1) * 4;
            res += dfs(skip, visited, i - 1, 2) * 4;
            res += dfs(skip, visited, i - 1, 5);
        }
        return res;
    }
    public int dfs(int[][] skip, boolean[] visited, int remain, int num) {
        if (remain < 0) return 0;
        if (remain == 0) return 1;

        int sum = 0;
        visited[num] = true;
        for (int i = 1; i <= 9; i++) {
            // if (not visited) and (the line does not cover any unvisited key)
            if (!visited[i] && (skip[num][i] == 0 || visited[skip[num][i]])) {
                sum += dfs(skip, visited, remain - 1, i);
            }
        }
        // backtrack
        visited[num] = false;
        return sum;
    }
}
