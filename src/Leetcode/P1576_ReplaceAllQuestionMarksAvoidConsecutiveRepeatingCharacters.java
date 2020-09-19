package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P1576_ReplaceAllQuestionMarksAvoidConsecutiveRepeatingCharacters {

    // initial approach

    public String modifyString(String s) {
        Queue<Character> q = new LinkedList<>();
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            if (c <= 'z' && c >= 'a') {
                chars[c - 'a']++;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 0) {
                q.offer((char)('a' + i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            }
            else {
                while (i + 1 < s.length() && q.peek() == s.charAt(i + 1)
                        || (i - 1 >= 0 && q.peek() == sb.charAt(sb.length() - 1))) {
                    q.offer(q.poll());
                }
                char temp = q.poll();
                sb.append(temp);
                q.offer(temp);
            }
        }
        return sb.toString();
    }

    // approach 2:
    // Convert string to a char array where we could iterate and modify directly and we only need to iterate
    // for 3 characters for each ? character to determine which one we need that does not have consecutive repeating characters.

    public String modifyString_2(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '?') {
                for (int j = 0; j < 3; j++) {
                    if (i > 0 && arr[i - 1] - 'a' == j) continue;
                    if (i + 1 < arr.length && arr[i + 1] - 'a' == j) continue;
                    arr[i] = (char) ('a' + j);
                    break;
                }
            }
        }
        return String.valueOf(arr);
    }
}
