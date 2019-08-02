package Leetcode;

public class P0211_AddAndSearchWord {


    // trie approach

    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }
    /** Initialize your data structure here. */
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode itr = root;
        for (char c : word.toCharArray()) {
            if (itr.children[c - 'a'] == null) {
                itr.children[c - 'a'] = new TrieNode();
            }
            itr = itr.children[c - 'a'];
        }
        itr.word = word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode itr = root;
        return searchHelper(word, 0, itr);
    }
    public boolean searchHelper(String word, int idx, TrieNode itr) {
        if (idx > word.length()) {
            return false;
        }
        if (idx == word.length()) {
            if (itr.word != null) {
                return true;
            }
            else {
                return false;
            }
        }

        char c = word.charAt(idx);
        if (c == '.') {
            for (TrieNode child : itr.children) {
                if (child != null) {
                    if (searchHelper(word, idx + 1, child)) {
                        return true;
                    }
                }
            }
            return false;
        }
        else {
            if (itr.children[c - 'a'] == null) return false;
            else {
                return searchHelper(word, idx + 1, itr.children[c - 'a']);
            }
        }
    }
}
