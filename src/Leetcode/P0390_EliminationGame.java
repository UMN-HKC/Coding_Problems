package Leetcode;

public class P0390_EliminationGame {

    // approach 1: brute force

    public int lastRemaining(int n) {
        if (n == 1) return 1;
        boolean[] visited = new boolean[n];
        int cnt = 0;
        int step = 2;
        while (true) {
            // to the right
            int i = 0;
            while (visited[i]) i++;
            while (i < n) {
                visited[i] = true;
                cnt++;
                if (cnt == n - 1) {
                    break;
                }
                i += step;
            }
            if (cnt == n - 1) break;
            // to the left
            step *= 2;
            i = n - 1;
            while (visited[i]) i--;
            while (i >= 0) {
                visited[i] = true;
                cnt++;
                if (cnt == n - 1) {
                    break;
                }
                i -= step;
            }
            if (cnt == n - 1) break;
            step *= 2;
        }
        return find(visited);
    }
    public int find(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) return i + 1;
        }
        return -1;
    }

    // approach 1:
    // https://leetcode.com/problems/elimination-game/discuss/87119/JAVA%3A-Easiest-solution-O(logN)-with-explanation

    // The basic idea is to keep track of the first element from left that hasn't been removed
    // Update the first element(head) as the following two situation occurs:
    // - if we move from left
    // - if we move from right and the total remaining number % 2 == 1
        //like 2 4 6 8 10, we move from 10, we will take out 10, 6 and 2, head is deleted and move to 4
        //like 2 4 6 8 10 12, we move from 12, we will take out 12, 8, 4, head is still remaining 2
    // Also, keep track of the steps that we need to move to update the head. After each iteration, step *= 2;
    // Thus, we do not actually need to know what elements are removed, after each iteration, we eliminate
    // half the elements by simply doing n /= 2

    // time: O(logn)
    // space: O(1)

    public int lastRemaining_2(int n) {
        boolean left = true;
        int head = 1;
        int step = 1;
        while (n != 1) {
            if (left) {
                head += step;
            }
            else if (n % 2 == 1) {
                head += step;
            }
            left = !left;
            n /= 2;
            step *= 2;
        }
        return head;
    }
}
