package Leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class P1047_RemoveAllAdjacentDuplicatesInString {

    // approach 1: stringbuilder

    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (sb.length() != 0 && S.charAt(i) == sb.charAt(sb.length() - 1)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            else {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }

    // approach 2: stack

    public String removeDuplicates_2(String S) {
        Stack<Character> s = new Stack<>();
        for (char c : S.toCharArray()) {
            if (!s.empty() && s.peek() == c) {
                s.pop();
            }
            else {
                s.push(c);
            }
        }
        String res = "";
        while (!s.empty()) {
            res = s.pop() + res;
        }
        return res;
    }

    // use deque instead of stack so that we could directly append to result

    public String removeDuplicates(String S) {
        Deque<Character> dq = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            if (dq.isEmpty() || dq.peekLast() != S.charAt(i)) {
                dq.offerLast(S.charAt(i));
            }
            else {
                dq.pollLast();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pollFirst());
        }
        return sb.toString();
    }
}
