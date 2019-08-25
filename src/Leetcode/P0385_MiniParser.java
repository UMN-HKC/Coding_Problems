package Leetcode;

public class P0385_MiniParser {

    // approach 1: stack
    // when ',' or ']' encountered, we may have a number before it that
    // we need to parse it first. But we need to check if the character
    // before is actually a digit (exceptions like ']]' and '],')
    // Also, '-' is a sign instead of a delimiter

    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            int num = Integer.parseInt(s);
            return new NestedInteger(num);
        }
        else {
            Stack<NestedInteger> stack = new Stack<>();
            int i = 0;
            int num = 0;
            int sign = 1;
            while (i < s.length()) {
                char c = s.charAt(i);
                if (c == '[') {
                    stack.push(new NestedInteger());
                }
                else if (c == '-') {
                    sign = -1;
                }
                else if (Character.isDigit(c)) {
                    num = num * 10 + c - '0';
                }
                else if (c == ',') {
                    if (Character.isDigit(s.charAt(i - 1))) {
                        stack.peek().add(new NestedInteger(num * sign));
                        num = 0;
                        sign = 1;
                    }

                }
                else if (c == ']') {
                    NestedInteger ni = stack.pop();
                    if (Character.isDigit(s.charAt(i - 1))) {
                        ni.add(new NestedInteger(num * sign));
                        num = 0;
                        sign = 1;
                    }
                    if (stack.empty()) {
                        stack.push(ni);
                    }
                    else {
                        NestedInteger last = stack.pop();
                        last.add(ni);
                        stack.push(last);
                    }
                }
                i++;
            }
            return stack.pop();
        }
    }
}
