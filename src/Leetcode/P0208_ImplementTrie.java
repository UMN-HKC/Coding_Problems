package Leetcode;

import java.util.Comparator;

public class P0208_ImplementTrie {

    // approach 1: 实现题

    class Trie {
        Trie[] children;
        boolean isWord;

        public Trie() {
            children = new Trie[26];
        }
        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie root = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (root.children[c - 'a'] == null) {
                    root.children[c - 'a'] = new Trie();
                }
                root = root.children[c - 'a'];
            }
            root.isWord = true;
        }
        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie root = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (root.children[c - 'a'] == null) return false;
                root = root.children[c - 'a'];
            }
            return root.isWord;
        }
        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Trie root = this;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (root.children[c - 'a'] == null) return false;
                root = root.children[c - 'a'];
            }
            return true;
        }
    }
}
