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
        if (!wordList.contains(endWord)) {
            return res;
        }
        Set<String> dict = new HashSet<>(wordList);
        Set<String> set1 = new HashSet<>();
        Map<String, List<String>> parents = new HashMap<>();
        // build graph
        bfs(set1, beginWord, endWord, dict, parents);
        // build path(dfs)
        if (parents.containsKey(endWord)) {
            dfs(res, new LinkedList<>(), beginWord, endWord, parents);
        }
        return res;
    }
    public void bfs(Set<String> set1, String beginWord, String endWord, Set<String> dict, Map<String, List<String>> parents) {
        boolean found = false;
        set1.add(beginWord);
        while (!set1.isEmpty() && !found) {
            // remove strings from dictionary which has been visited in last level to prevent cycle
            for (String s : set1) {
                dict.remove(s);
            }
            Set<String> temp = new HashSet<>();
            // current level
            for (String s : set1) {
                String p = s;
                char[] chars = s.toCharArray();
                // try each character for one string in current level
                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (old == c) {
                            continue;
                        }
                        chars[i] = c;
                        String newWord = new String(chars);
                        if (newWord.equals(endWord)) {

                            List<String> list = parents.getOrDefault(newWord, new LinkedList());
                            list.add(p);
                            parents.put(newWord, list);
                            found = true;
                        }
                        else if (dict.contains(newWord) && !found) {
                            List<String> list = parents.getOrDefault(newWord, new LinkedList());
                            list.add(p);
                            parents.put(newWord, list);
                            temp.add(newWord);
                        }
                    }
                    chars[i] = old;
                }
            }
            set1 = temp;
        }
        return;
    }
    public void dfs(List<List<String>> res, LinkedList<String> list, String beginWord, String curWord, Map<String, List<String>> parents) {
        if (curWord.equals(beginWord)) {
            list.addFirst(curWord);
            res.add(new LinkedList<>(list));
            list.removeFirst();
            return;
        }
        for (String p : parents.get(curWord)) {
            list.addFirst(curWord);
            dfs(res, list, beginWord, p, parents);
            list.removeFirst();
        }
    }
}
