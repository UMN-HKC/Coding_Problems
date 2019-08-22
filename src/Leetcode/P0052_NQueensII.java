package Leetcode;
import java.util.HashSet;
import java.util.Set;

public class P0052_NQueensII {

    // approach 1: backtracking, but we do not need to actually place the queens.
    // This approach is smart in that it sees that this cell's r + c remains same as
    // its up 45 degrees and r - c remains same as its up 135 degrees. So we can utilize
    // this characteristic and set to help us detect invalid placement of queeen and
    // help us backtrack.

    Set<Integer> visitedCol = new HashSet<>();
    Set<Integer> visitedDiag1 = new HashSet<>();
    Set<Integer> visitedDiag2 = new HashSet<>();
    public int totalNQueens(int n) {
        int[] cnt = {0};
        helper(cnt, 0, n);
        return cnt[0];
    }
    public void helper(int[] cnt, int r, int n) {
        if (r == n) cnt[0]++;
        for (int i = 0; i < n; i++) {
            if (visitedCol.contains(i)) continue;
            if (visitedDiag1.contains(r+i)) continue;
            if (visitedDiag2.contains(r-i)) continue;
            visitedCol.add(i);
            visitedDiag1.add(r+i);
            visitedDiag2.add(r-i);
            helper(cnt, r+1, n);
            visitedCol.remove(i);
            visitedDiag1.remove(r+i);
            visitedDiag2.remove(r-i);
        }
    }
}
