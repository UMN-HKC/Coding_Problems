package Leetcode;
import java.util.*;
public class P0316_RemoveDuplicateLetters {

    // approach 1: stack, greedy

    // The basic idea is to use stack to simulate the process of appending letter.
    // Use an array called rest to store the rest number of letters are to be analyzed
    // and added boolean array to indicate whether a letter has been added or not.
    // Before adding a letter to the stack, we first want to make sure that this letter
    // hasn't been added before. Then remove all previous letters which are
    // lexicographically greater than the current letter and it still has duplicates
    // in the later part of string. and remember to reset the removed character's
    // visited status to unvisited.

    // time: O(n)
    // space: O(n)

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 2) return s;

        Stack<Character> stack = new Stack<>();
        int[] rest = new int[26];
        boolean[] added = new boolean[26];

        for (char c : s.toCharArray()) {
            rest[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // pop all the elements already in the stack but are lexicographically greater than
            // the current visiting one, but will not add if the current one is already in the stack
            while (!stack.empty() && c < stack.peek() && rest[stack.peek() - 'a'] > 0 && !added[c - 'a']) {
                added[stack.pop() - 'a'] = false;
            }
            // mark as visited
            if (!added[c - 'a']) {
                added[c - 'a'] = true;
                stack.push(c);
            }
            // each passed letter's count should be decremented each time
            rest[c - 'a']--;

        }
        StringBuilder res = new StringBuilder();
        while (!stack.empty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
