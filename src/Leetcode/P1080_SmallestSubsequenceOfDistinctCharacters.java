package Leetcode;

import java.util.*;

public class P1080_SmallestSubsequenceOfDistinctCharacters {

    // couldn't figure out initially
    // idea borrowed from discussion board
    // Basic idea is to use monotonic increasing stack

    // time: O(n)
    // space: O(1) because we will have at most 26 characters in our stack

    // credit to rosemelon

//    Intuition to algorithm: We count up the numbers of times each letter appears. We also keep another dictionary
//    that remembers if a character is in our stack or not. At the beginning of each iteration, we decrement the number
//    of times the character can still be encountered in the string in letterDict.
//    For each character in our string:
//      1). If we have already have the letter in our stack, we skip to the next letter.
//      2). If we do not have the letter in our stack and our stack is not empty, we check to see if
//          a. Our current character is smaller than the last character on the stack and
//          b. If the last letter in the stack still exists later on in the string
//          If a + b is true, we can pop off that last character on the stack. We keep doing a + b until it isn't true, then we append our current character
//      3). We set that the current character is in the stack by setting it in used.

    public String smallestSubsequence(String text) {
        if (text == null || text.length() == 0) return text;
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : text.toCharArray()) {
            map.put(c, map.get(c) - 1);
            if (set.contains(c)) {
                continue;
            }
            while (!stack.empty() && c < stack.peek() && map.get(stack.peek()) > 0) {
                char temp = stack.pop();
                set.remove(temp);
            }
            stack.push(c);
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
