package Leetcode;
import java.util.*;

public class P0212_WordSearchII {

    // initial approach: Trie & backtracking
    // the basic idea is to use word dictionary to build the trie and then
    // dfs the board and backtracking as usual

    int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    TrieNode root;
    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        root = new TrieNode();
        buildTrie(words, root);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res.size() < words.length) {
                    search(res, board, visited, i, j, root);
                }
                else {
                    break;
                }
            }
        }
        return res;
    }
    public void search(List<String> res, char[][] board, boolean[][] visited, int i, int j, TrieNode root) {
        TrieNode itr = root;
        if (itr.children[board[i][j] - 'a'] == null) {
            return;
        }
        else {
            itr = itr.children[board[i][j] - 'a'];
            if (itr.word != null) {
                res.add(itr.word);
                itr.word = null;
            }
            visited[i][j] = true;
            for (int[] dir : dirs) {
                int y = i + dir[0];
                int x = j + dir[1];
                if (x >= 0 && y >= 0 && y < board.length && x < board[0].length && !visited[y][x]) {
                    search(res, board, visited, y, x, itr);
                }
            }
            visited[i][j] = false;
        }
    }
    public void buildTrie(String[] words, TrieNode root) {
        for (String word : words) {
            TrieNode itr = root;
            for (char c : word.toCharArray()) {
                if (itr.children[c - 'a'] == null) {
                    itr.children[c - 'a'] = new TrieNode();
                }
                itr = itr.children[c - 'a'];
            }
            itr.word = word;
        }
    }
}
