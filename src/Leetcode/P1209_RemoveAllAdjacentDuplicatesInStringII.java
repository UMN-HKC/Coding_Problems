package Leetcode;

import java.util.Stack;

public class P1209_RemoveAllAdjacentDuplicatesInStringII {

    // approach 1: brute force, this is actually not slow
    // The basic idea is that each time iterate through the whole string and delete
    // duplicates k characters, after a iteration, create a new string and do the same
    // thing again until no deletion occurs after an iteration

    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() < k) return s;
        boolean delete = false;
        do {
            delete = false;
            int l = 0, r = s.length() - 1;
            char[] chars = s.toCharArray();
            int i = l;
            while (i + k - 1<= r) {
                int j = i;
                while (j <= r && j - i < k && chars[j] == chars[i] ) {
                    j++;
                }
                if (j - i == k) {
                    delete = true;
                    while (i < j) {
                        chars[i++] = ' ';
                    }
                }
                else {
                    i = j;
                }
            }
            if (delete) {
                s = String.valueOf(chars).replace(" ", "");
            }
        } while (delete);
        return s;
    }

    // approach 2: stack

    // Create a node class to store character and its frequency. The basic idea is that
    // each time we visit a character, we check if the node at the top of the stack is
    // the same character, if it is and after adding current character, its frequency
    // is k, we pop this node out. Otherwise, we either create a new node or modify the
    // frequency of the node at the top of the stack

    public String removeDuplicates_2(String s, int k){
        int len=s.length();
        Stack<Node> stack = new Stack<>();
        for(char c : s.toCharArray()){
            //compare prev with cur
            if(!stack.isEmpty() && stack.peek().c == c){
                stack.peek().count++;
            }else{
                //not repeat
                stack.push(new Node(c,1));
            }
            //remove when num of dup == k
            if(stack.peek().count==k) stack.pop();
        }
        //build result
        StringBuilder sb = new StringBuilder();
        for(Node node : stack){
            for(int i=0;i<node.count;i++){
                sb.append(node.c);
            }
        }
        return sb.toString();
    }

    class Node{
        char c;
        int count;
        public Node(char c, int count){
            this.c=c;
            this.count=count;
        }
    }
}
