package Leetcode;
import java.util.*;

public class P0336_PalindromePairs {

    // approach 1: intuitive and brute force

    // time: O(n * (k^2))

    public List<List<Integer>> palindromePairs_1(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    StringBuilder sbStr2 = new StringBuilder(str2);
                    String revStr2 = sbStr2.reverse().toString();
                    if (map.containsKey(revStr2) && map.get(revStr2) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(revStr2));
                        list.add(i);
                        res.add(list);
                    }
                }
                if (isPalindrome(str2)) {
                    StringBuilder sbStr1 = new StringBuilder(str1);
                    String revStr1 = sbStr1.reverse().toString();
                    if (j != words[i].length() && map.containsKey(revStr1) && map.get(revStr1) != i) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(revStr1));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    // approach 2: Trie (hard to understand but smart)

    // time: O(n * (k^2))

    class Trie {
        int idx;
        List<Integer> list;
        Trie[] children;
        public Trie() {
            idx = -1;
            children = new Trie[26];
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs_2(String[] words) {
        Trie root = new Trie();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            search(res, root, words[i], i);
        }
        return res;
    }
    public void search(List<List<Integer>> res, Trie root, String word, int idx) {
        Trie itr = root;
        for (int i = 0; i < word.length(); i++) {
            if (itr.idx >= 0 && itr.idx != idx && isPalindrome(word, i, word.length() - 1)) {
                res.add(Arrays.asList(idx, itr.idx));
            }
            char c = word.charAt(i);
            if (itr.children[c - 'a'] == null) {
                return;
            }
            itr = itr.children[c - 'a'];
        }
        for (int i : itr.list) {
            if (i != idx) {
                res.add(Arrays.asList(idx, i));
            }
        }
    }
    public void addWord(Trie root, String word, int idx) {
        Trie itr = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (itr.children[c - 'a'] == null) {
                itr.children[c - 'a'] = new Trie();
            }
            if (isPalindrome(word, 0, i)) {
                itr.list.add(idx);
            }
            itr = itr.children[c - 'a'];
        }
        // add to the ending character
        itr.list.add(idx);
        itr.idx = idx;
    }
    public boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }
}
