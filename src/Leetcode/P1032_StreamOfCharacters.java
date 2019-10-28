package Leetcode;

import java.util.*;

public class P1032_StreamOfCharacters {

    // approach 1: trie + list of pointers (slow)

    class Trie {
        Trie[] children;
        boolean isWord;
        public Trie() {
            isWord = false;
            children = new Trie[26];
        }
    }
    Trie root;
    List<Trie> pointers;
    public StreamChecker(String[] words) {
        root = new Trie();
        pointers = new ArrayList<>();
        for (String word : words) {
            Trie itr = root;
            for (char c : word.toCharArray()) {
                if (itr.children[c - 'a'] == null) {
                    itr.children[c - 'a'] = new Trie();
                }
                itr = itr.children[c - 'a'];
            }
            itr.isWord = true;
        }
    }

    public boolean query(char letter) {
        Trie node = new Trie();
        node = root;
        pointers.add(node);
        boolean success = false;
        List<Trie> newPtrList = new ArrayList<>();
        for (Trie ptr : pointers) {
            if (ptr.children[letter - 'a'] != null) {
                newPtrList.add(ptr.children[letter - 'a']);
                success = success || ptr.children[letter - 'a'].isWord;
            }
        }
        pointers = newPtrList;
        return success;
    }


    // approach 2: trie + string (faster)

    // The basic idea is that we will build the trie with each word in reverse order
    // and keep a string of all the characters read so far. Each time we do a query,
    // we will traverse from the back of the string and at the same time traverse the
    // trie until we find a trie node that is a word

    class Trie {
        Trie[] children;
        boolean isWord;
        public Trie() {
            isWord = false;
            children = new Trie[26];
        }
    }
    StringBuilder sb = new StringBuilder();
    Trie root;
    int maxLen;
    public StreamChecker(String[] words) {
        root = new Trie();
        maxLen = 0;
        for (String word : words) {
            Trie itr = root;
            int len = 0;
            for (int i = word.length() - 1; i >= 0; i--) {
                len++;
                char c = word.charAt(i);
                if (itr.children[c - 'a'] == null) {
                    itr.children[c - 'a'] = new Trie();
                }
                itr = itr.children[c - 'a'];
            }
            maxLen = Math.max(maxLen, len);
            itr.isWord = true;
        }
    }

    public boolean query(char letter) {
        sb.append(letter);
        Trie itr = root;
        for (int i = sb.length() - 1; i >= 0 && sb.length() - i <= maxLen
                && itr.children[sb.charAt(i) - 'a'] != null; i--) {
            char c = sb.charAt(i);
            if (itr.children[sb.charAt(i) - 'a'].isWord) return true;
            else {
                itr = itr.children[sb.charAt(i) - 'a'];
            }
        }
        return false;
    }
}

