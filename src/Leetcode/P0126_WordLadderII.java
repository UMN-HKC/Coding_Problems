package Leetcode;
import java.util.*;

public class P0126_WordLadderII {

    // approach 1: bfs + dfs
    // hard to me, refer to Huahua's video: https://www.youtube.com/watch?v=PblfQrdWXQ4&t=1064s

    // The basic idea is to realize we can visualize the problem as a graph problem. Use bfs to construct
    // the graph and then use dfs to explore all the shortest path. When building the graph, we should note
    // that different from "Word Ladder", we cannot just remove a string from dictionary after just visiting it
    // because we may need it in other branch in the current level of bfs. But after visiting the current
    // level, we delete all the visited string in the last level from the dictionary. Also, use a flag to
    // indicate whether we have found a ladder in the last level, if so, we terminate the while loop. This is
    // why bfs ensures the shortest path.

    // n: # of words in dictionary; l: length of word; p: number of path
    // time: O(n * (l ^ 26))
    // space: O(n + p * l)

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        Map<String, List<String>> graph = new HashMap<>();
        bfs(graph, new HashSet<>(), beginWord, endWord, dict);
        dfs(graph, res, new ArrayList<>(), beginWord, endWord);
        return res;
    }
    public void dfs(Map<String, List<String>> graph, List<List<String>> res, List<String> list, String beginWord, String endWord) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList(list));
        }
        else {
            // need to check if the begin word exists in the graph
            // because it might be a word that is in the same level
            // as the end word, so it will not have been added to graph
            if (graph.containsKey(beginWord)) {
                for (String next : graph.get(beginWord)) {
                    dfs(graph, res, list, next, endWord);
                }
            }

        }
        list.remove(list.size() - 1);
    }
    public void bfs(Map<String, List<String>> graph, Set<String> curLevel, String beginWord, String endWord, Set<String> dict) {
        boolean found = false;
        curLevel.add(beginWord);
        while (curLevel.size() != 0 && !found) {
            Set<String> nextLevel = new HashSet<>();
            // remove words from last level
            for (String s : curLevel) {
                dict.remove(s);
            }
            for (String cur : curLevel) {
                char[] chars = cur.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        chars[i] = c;
                        String next = new String(chars);
                        if (next.equals(endWord)) {
                            found = true;
                            if (!graph.containsKey(cur)) {
                                graph.put(cur, new ArrayList<>());
                            }
                            graph.get(cur).add(next);
                        }
                        else if (dict.contains(next) && !found) {
                            if (!graph.containsKey(cur)) {
                                graph.put(cur, new ArrayList<>());
                            }
                            graph.get(cur).add(next);
                            nextLevel.add(next);
                        }
                    }
                    chars[i] = old;
                }
            }
            curLevel = nextLevel;
        }
    }
}
