package Leetcode;

import java.util.*;

public class P0752_OpenTheLock {

    // approach 1: BFS
    // The basic idea is to realize that the problem could be solved using breadth first
    // search, because the problem is asking for the minimum turns to get to the target.
    // To perform bfs on this problem, for each level of searching, we will modify one digit
    // at each of the four positions to its 'nearby'(+1/-1) digit for each lock number at
    // this level. Once we encounter target, we are done.

    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        for (String deadend : deadends) {
            visited.add(deadend);
        }
        if (visited.contains("0000")) return -1;
        visited.add("0000");
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        int turn = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            turn++;
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return turn;
                }
                char[] chars = cur.toCharArray();
                for (int j = 0; j < 4; j++) {
                    char old = chars[j];
                    int digit = chars[j] - '0';
                    int incre = digit == 9 ? 0 : digit + 1;
                    int decre = digit == 0 ? 9 : digit - 1;
                    chars[j] = (char)(incre + '0');
                    String str1 = new String(chars);
                    if (!visited.contains(str1)) {
                        visited.add(str1);
                        q.offer(str1);
                    }
                    chars[j] = (char)(decre + '0');
                    String str2 = new String(chars);
                    if (!visited.contains(str2)) {
                        visited.add(str2);
                        q.offer(str2);
                    }
                    chars[j] = old;
                }
            }
        }
        return -1;
    }


    // approach 2: Bi-directional BFS

    // use set to simulate queue, since we wanna use set to do O(1) check

    public int openLock_2(String[] deadends, String target) {
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String deadend : deadends) {
            visited.add(deadend);
        }
        if (visited.contains("0000")) return -1;
        q1.add("0000");
        q2.add(target);
        int turn = 0;
        while (q1.size() != 0 && q2.size() != 0) {
            turn++;
            Set<String> temp = new HashSet<>();
            for (String cur : q1) {
                visited.add(cur);
                char[] chars = cur.toCharArray();
                for (int j = 0; j < 4; j++) {
                    char old = chars[j];
                    int digit = chars[j] - '0';
                    int incre = digit == 9 ? 0 : digit + 1;
                    int decre = digit == 0 ? 9 : digit - 1;
                    chars[j] = (char)(incre + '0');
                    String str1 = new String(chars);
                    if (!visited.contains(str1)) {
                        if (q2.contains(str1)) {
                            return turn;
                        }
                        temp.add(str1);
                    }
                    chars[j] = (char)(decre + '0');
                    String str2 = new String(chars);
                    if (!visited.contains(str2)) {
                        if (q2.contains(str2)) {
                            return turn;
                        }
                        temp.add(str2);
                    }
                    chars[j] = old;
                }
            }
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

}
