package Leetcode;
import java.util.*;
public class P0316_RemoveDuplicateLetters {

    // initial approach: monotonic increasing stack without with duplication checking
    // idea inspired by another problem while this problem need duplication checking

    // basically use a stack to store the unique letters where bottom elements
    // are lexicographically smaller than top elements(monotonically increasing)

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() < 2) return s;

        Stack<Character> stack = new Stack<>();
        int[] cnt = new int[26];
        boolean[] added = new boolean[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // pop all the elements already in the stack but are lexicographically smaller than
            // the current visiting one, but will not add if the current one is already in the stack
            while (!stack.empty() && c < stack.peek() && cnt[stack.peek() - 'a'] > 0 && !added[c - 'a']) {
                added[stack.pop() - 'a'] = false;
            }
            // mark as visited
            if (!added[c - 'a']) {
                added[c - 'a'] = true;
                stack.push(c);
            }
            // each passed letter's count should be decremented each time
            cnt[c - 'a']--;

        }
        StringBuilder res = new StringBuilder();
        while (!stack.empty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
}
