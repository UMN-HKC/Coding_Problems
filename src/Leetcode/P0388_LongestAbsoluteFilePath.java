package Leetcode;

import java.util.Stack;

public class P0388_LongestAbsoluteFilePath {

    // kind of figured out the solution
    // idea borrowed from discussion board
    // We determine the level by getting the number of "\t" each string path has
    // use stack to store each level's path length, and because of the characteristic of
    // stack, we can pop to backtrack to previous directory and get that path length

    // time: O(n) each string into and out of stack for once
    // space: O(n)

    public int lengthLongestPath(String input) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // dummy length, when depth is 0, its path length is 0
        stack.push(0);
        String[] lines = input.split("\n");

        for (int i = 0; i < lines.length; i++) {
            int cur = 0;
            String s = lines[i];
            // get the number of tabs (this also deals with white spaces)
            int numOfTab = getNumOfTab(s);
            // backtrack to previous directory
            while (stack.size() > numOfTab + 1) {
                stack.pop();
            }
            // +1 is for "/"
            cur += stack.peek() + s.length() - numOfTab + 1;
            stack.push(cur);
            if (s.contains(".")) {
                // cur - 1 is because the file does not end with "/"
                max = Math.max(cur - 1, max);
            }
        }
        return max;
    }
    public int getNumOfTab(String s) {
        return s.lastIndexOf("\t")+1;
    }
}
