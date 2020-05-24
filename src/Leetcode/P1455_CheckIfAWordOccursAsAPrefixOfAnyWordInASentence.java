package Leetcode;

public class P1455_CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {

    // approach 1: split and check

    public int isPrefixOfWord(String sentence, String searchWord) {
        int idx = -1;
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(searchWord)) {
                return i + 1;
            }
        }
        return idx;
    }
}
