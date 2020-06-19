package Leetcode;
import java.util.*;

public class P0402_RemoveKDigits {

    // approach 1: stack
    // https://www.youtube.com/watch?v=3QJzHqNAEXs

    // The idea is to achieve the result sequence in non-decreasing order because we want left digits
    // be as small as possible as they have more digit weight than the right digits. We will use stack
    // because it is a good data structure that we can store digits and also check its previous digits
    // and maybe remove the previous digits. As we iterate through the digits from left to right, once
    // we encounter a "dip", the previous digit is greater than the current one, we will try to flat
    // the curve by removing the previous digits. Until there are no greater value previous digits, we
    // add the current digit to the stack. Note that, if at this time the stack is empty and the current
    // digit is 0, we will not add 0 to the stack.
    // After we finish iterating the digit sequence, if we need to remove more digits, we will pop
    // the digits from the stack until no more digits need to be removed. Why do we remove digits
    // from top of the stack? Because the stack is now in an non-decreasing order from bottom to top.
    // So digits at the top of the stack are greater.


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
