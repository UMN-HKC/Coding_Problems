package Leetcode;

import java.util.*;

public class P0642_DesignSearchAutocompleteSystem {

    // appraoch 1: Trie + DFS

    // The basic idea is that we will use trie to store historic sentences, each sentence
    // string would be stored at the bottom trie node instead of in each trie node. So
    // in this case, we will need to perform DFS to find all the possible auto-complete
    // sentences the current typed character would result. Note that we use trie.children[27]
    // to store ' ' and remember to reset after '#'.

    class Trie {
        Trie[] children;
        String sentence;
        boolean isSentence;
        int time;
        public Trie() {
            children = new Trie[27];
        }
    }
    // trie root
    Trie root;
    // current stop
    Trie itr;
    // current typed characters
    String cur;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        itr = root;
        cur = new String();
        for (int i = 0; i < sentences.length; i++) {
            int time = times[i];
            String sentence = sentences[i];
            Trie node = root;
            for (char c : sentence.toCharArray()) {
                if (c == ' ') {
                    if (node.children[26] == null) {
                        node.children[26] = new Trie();
                    }
                    node = node.children[26];
                }
                else {
                    if (node.children[c - 'a'] == null) {
                        node.children[c - 'a'] = new Trie();
                    }
                    node = node.children[c - 'a'];
                }
            }
            node.time = time;
            node.sentence = sentence;
            node.isSentence = true;
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            if (itr.sentence != null && itr.sentence.equals(cur)) {
                itr.time++;
            }
            else {
                itr.isSentence = true;
                itr.sentence = new String(cur);
                itr.time = 1;
            }
            // reset
            cur = "";
            itr = root;
            return new ArrayList<>();
        }
        else {
            cur += c;
            int entry = c ==  ' ' ? 26 : (int)(c - 'a');
            if (itr.children[entry] == null) {
                itr.children[entry] = new Trie();
                itr = itr.children[entry];
                return new ArrayList<>();
            }
            TreeMap<Integer, List<String>> map = new TreeMap<>((a, b) -> b - a);
            itr = itr.children[entry];
            Trie t = itr;
            search(map, t);
            return getTopThree(map);
        }
    }
    public List<String> getTopThree(TreeMap<Integer, List<String>> map) {
        List<String> res = new ArrayList<>();
        int cnt = 0;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            int time = entry.getKey();
            List<String> list = entry.getValue();
            Collections.sort(list);
            for (int i = 0; i < list.size() && res.size() < 3; i++) {
                res.add(list.get(i));
            }
            if (res.size() == 3) break;
        }
        return res;
    }
    public void search(TreeMap<Integer, List<String>> map, Trie t) {
        if (t != null) {
            if (t.isSentence) {
                if (!map.containsKey(t.time)) {
                    map.put(t.time, new ArrayList<>());
                }
                map.get(t.time).add(new String(t.sentence));
            }
            if (itr.children != null) {
                for (Trie child : t.children) {
                    search(map, child);
                }
            }
        }
    }
}
