package Leetcode;
import java.util.*;

public class P0736_ParseLispExpression {

    // approach 1: hash map + recursion
    // There are some key points to solve this problem.
    // The major challenge for this problem is to be able to parse tokens correctly for each
    // level of expression computations and handle same variable under different context.
    // We will use hash map to pass variables and their values in its parent scope to its child
    // scope. At the start of the child context, it will make a copy of the hash map instead of
    // operating directly on the passed hash map, since we are only passing the reference and once
    // we modify the reference, the smaller scope will affect the parent scope.

    // When we parse tokens, we only split the current level tokens and add to list, for deeper levels
    // we will add the whole chunk to the list which will be recursively evaluated in the calling function.
    // We differentiate levels by keeping a counter, increment counter with "(" and decrement counter
    // with ")", and counter == 0 means we are in the current level


    public int evaluate(String expression) {
        return eval(expression, new HashMap<>());
    }
    public int eval(String s, Map<String, Integer> map) {
        if (s.charAt(0) != '(') {
            if (s.charAt(0) == '-' || Character.isDigit(s.charAt(0))) {
                return Integer.parseInt(s);
            }
            return map.get(s);
        }

        Map<String, Integer> curMap = new HashMap<>(map);
        List<String> tokens = parseTokens(s.substring(s.charAt(1) == 'm' ? 6 : 5, s.length() - 1));
        String head = s.substring(0, 2);
        if (head.equals("(m") || head.equals("(a")) {
            return head.equals("(m") ? eval(tokens.get(0), curMap) * eval(tokens.get(1), curMap)
                    : eval(tokens.get(0), curMap) + eval(tokens.get(1), curMap);
        }
        else {
            for (int i = 0; i < tokens.size() - 2; i += 2) {
                curMap.put(tokens.get(i), eval(tokens.get(i + 1), curMap));
            }
            return eval(tokens.get(tokens.size() - 1), curMap);
        }
    }
    public List<String> parseTokens(String s) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            if (cnt == 0 && c == ' ') {
                tokens.add(new String(sb));
                sb = new StringBuilder();
            }
            else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) tokens.add(new String(sb));
        return tokens;
    }
}
