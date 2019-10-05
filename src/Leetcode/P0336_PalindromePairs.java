package Leetcode;
import java.util.*;

public class P0336_PalindromePairs {

    // approach 1: intuitive and brute force

    // time: O((n^2) * k)

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

    // approach 2: hash map

    // There are several cases to be considered that isPalindrome(s1 + s2):
    // Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
    // Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
    // Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.
    // Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
    // To make the search faster, build a HashMap to store the String-idx pairs.

    // time: O(n * (k ^ 2))

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        if (map.containsKey("")) {
            int emptyIdx = map.get("");
            for (int i = 0; i < words.length; i++) {
                if (i == emptyIdx) continue;
                if (isPalindrome(words[i], 0, words[i].length() - 1)) {
                    res.add(Arrays.asList(emptyIdx, i));
                    res.add(Arrays.asList(i, emptyIdx));
                }
            }
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String revWord = reverse(word);
            if (map.containsKey(revWord) && map.get(revWord) != i) {
                res.add(Arrays.asList(i, map.get(revWord)));
            }
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int cut = 1; cut < word.length(); cut++) {
                String revPrefix = reverse(word.substring(0, cut));
                if (map.containsKey(revPrefix) && map.get(revPrefix) != i
                        && isPalindrome(word, cut, word.length() - 1)) {
                    res.add(Arrays.asList(i, map.get(revPrefix)));
                }
                String revSuffix = reverse(word.substring(cut));
                if (map.containsKey(revSuffix) && map.get(revSuffix) != i
                        && isPalindrome(word, 0, cut - 1)) {
                    res.add(Arrays.asList(map.get(revSuffix), i));
                }
            }
        }
        return res;
    }
    public String reverse(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
        return new String(chars);
    }
    public boolean isPalindrome(String word, int l, int r) {
        while (l < r) {
            if (word.charAt(l) != word.charAt(r)) {
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
