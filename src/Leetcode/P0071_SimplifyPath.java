package Leetcode;

import java.util.Stack;

public class P0071_SimplifyPath {

    // Initial approach: use stack
    // when we go into the path, we might need to revert back to previous path, so
    // it would be good if we use some data structure to let us revert back where
    // stack will do.

    // time: O(n)
    // space: O(n)

    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] pathTokens = path.split("/");
        for (int i = 0; i < pathTokens.length; i++) {
            if (pathTokens[i].equals("..")) {
                if (!stack.empty()) {
                    stack.pop();
                }
            }
            else if (!pathTokens[i].equals(".") && pathTokens[i].length() != 0) {
                stack.push(pathTokens[i]);
            }
        }
        // if stack is empty, we need to return "/" directly
        if (stack.empty()) return "/";
        String res = "";
        while (!stack.empty()) {
            res = "/" + stack.pop() + res;
        }
        return res;
    }
}
