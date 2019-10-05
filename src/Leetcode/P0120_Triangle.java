package Leetcode;

import java.util.List;

public class P0120_Triangle {

    // initial approach: DFS (TLE)
    // should not use DFS to approach this problem since the problem has overlapping subproblems

    public int minimumTotal(List<List<Integer>> triangle) {
        int[] minPathSum = new int[1];
        minPathSum[0] = Integer.MAX_VALUE;
        dfs(triangle, 0, 0, 0, minPathSum);
        return minPathSum[0];
    }
    public void dfs(List<List<Integer>> triangle, int r, int c, int sum, int[] min) {
        if (r >= triangle.size() || c >= triangle.get(r).size()) return;
        if (r == triangle.size() - 1) {
            min[0] = Math.min(min[0], sum + triangle.get(r).get(c));
            return;
        }
        dfs(triangle, r + 1, c, sum + triangle.get(r).get(c), min);
        dfs(triangle, r + 1, c + 1, sum + triangle.get(r).get(c), min);
        return;
    }

    // approach 2: DP(top-down)

    public int minimumTotal_dp(List<List<Integer>> triangle) {
        int r = triangle.size();
        int c = triangle.get(r - 1).size() ;
        int[][] grid = new int[r][c];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = triangle.get(i).get(j);
                }
                else if (j == triangle.get(i).size() - 1) {
                    grid[i][j] = grid[i-1][j-1] + triangle.get(i).get(j);
                }
                else if (j == 0) {
                    grid[i][j] = grid[i-1][j] + triangle.get(i).get(j);
                }
                else {
                    grid[i][j] = triangle.get(i).get(j) + Math.min(grid[i-1][j-1], grid[i-1][j]);
                }
            }
        }
        for (int num : grid[r-1]) {
            min = Math.min(min, num);
        }
        return min;
    }

    // optimize space complexity for approach 2

    public int minimumTotal_space_efficient(List<List<Integer>> triangle) {
        int r = triangle.size();
        int c = triangle.get(r - 1).size() ;
        int[] cur = new int[c];
        int[] prev = new int[c];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == 0 && j == 0) {
                    cur[j] = triangle.get(i).get(j);
                }
                else if (j == i) {
                    cur[j] = prev[j-1] + triangle.get(i).get(j);
                }
                else if (j == 0) {
                    cur[j] = prev[j] + triangle.get(i).get(j);
                }
                else {
                    cur[j] = triangle.get(i).get(j) + Math.min(prev[j-1], prev[j]);
                }
            }
            int[] temp = cur;
            cur = prev;
            prev = temp;
        }
        for (int num : prev) {
            min = Math.min(min, num);
        }
        return min;
    }

    // approach 3: alternatively, we could operates directly on the triangle list provided

    public int minimumTotal_space_most_efficent(List<List<Integer>> triangle) {
        int r = triangle.size();
        int c = triangle.get(r - 1).size();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j <= i; j++) {
                int val = triangle.get(i).get(j);
                if (i == 0 && j == 0) {
                    continue;
                }
                else if (j == i) {
                    triangle.get(i).set(i, triangle.get(i - 1).get(j - 1) + val);
                }
                else if (j == 0) {
                    triangle.get(i).set(j, triangle.get(i - 1).get(j) + val);
                }
                else {
                    triangle.get(i).set(j, Math.min(triangle.get(i-1).get(j), triangle.get(i-1).get(j-1)) + val);
                }
            }
        }
        for (int num : triangle.get(r-1)) {
            min = Math.min(min, num);
        }
        return min;
    }

    // approach 4: savage
    // The basic idea is to modify on the input list directly as approach 3 but
    // going from bottom to top which saves a lot of checking since there's always
    // a bottom and a bottom right element available

    public int minimumTotal_savage(List<List<Integer>> triangle) {
        int[] A = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
            }
        }
        return A[0];
    }

}
