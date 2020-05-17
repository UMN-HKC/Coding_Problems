package Leetcode;
import java.util.*;

public class P0402_RemoveKDigits {

    // approach 1: stack
    
    // The idea is divided into two steps. The first step is to iterate through the num string from left
    // to right and exclude all monotonically decreasing digit sequence and add the rest of digits to
    // the stack. Then, the digit sequence left in the stack is the minimum number we could construct from
    // the stack. If we need to remove more digits, we pop from the top of the stack until no more needed.


    public String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            int cur = num.charAt(i) - '0';
            while (k > 0 && !stack.isEmpty() && stack.peekFirst() > cur) {
                stack.removeFirst();
                k--;
            }
            stack.addFirst(cur);
        }
        while (k > 0) {
            stack.removeFirst();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeFirst());
        }
        String res = sb.reverse().toString();
        int i = 0;
        while (i < res.length() && res.charAt(i) == '0') i++;
        return i < res.length() ? res.substring(i) : "0";
    }
}
