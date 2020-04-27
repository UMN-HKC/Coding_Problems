package Leetcode;

import java.util.*;

public class P1102_PathWithMaximumMinimumValue {

    // brute force dfs will result in TLE

    // approach 1: BFS, greedy, pq
    // https://www.youtube.com/watch?v=mMm0KfZpsg8

    // The basic idea is to greedily explore greater number path. To achieve that,
    // we use a max heap to add cells into heap so each time we get a currently maximum
    // cell to explore. But when we update our result as we go, we update result to be the
    // smaller one between result and current cell we are at, because we are required to
    // minimize the maximum score. Once we hit end, we return result.

    // time: O(m * n * log(m*n))
    // space: O(m * n)

    public static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int maximumMinimumPath(int[][] A) {
        if (A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        int max = A[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        visited[0][0] = true;
        pq.offer(new int[]{A[0][0], 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[1];
            int j = cur[2];
            max = Math.min(max, cur[0]);
            if(i == m - 1 && j == n - 1)
                break;
            for (int[] dir : dirs) {
                int r = i + dir[0];
                int c = j + dir[1];
                if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) continue;
                visited[r][c] = true;
                pq.offer(new int[]{A[r][c], r, c});
            }
        }
        return max;
    }

    // approach 2: binary search + dfs
    // The basic idea is to apply binary search on the range of values inside the matrix.
    // pick the middle number to be the maximum minimum score and check if using this number
    // as the maximum minimum number will result in a path from begin to end. If it could, we
    // could look for a bigger number, otherwise look for a smaller number

    // note that we do not backtrack when we do dfs, because once a cell cannot lead to end, we
    // do not want to visit it again.

    // time: O(m * n * log(m*n))
    // space: O(m * n)

    public int maximumMinimumPath_2(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;
        int m = A.length, n = A[0].length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                min = Math.min(min, A[i][j]);
                max = Math.max(max, A[i][j]);
            }
        }
        int l = min, r = max;
        int res = 0;
        while (l <= r) {
            boolean[][] visited = new boolean[m][n];
            int mid = l + (r - l) / 2;
            if (search(A, visited, 0, 0, mid)) {
                res = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return res;
    }
    private boolean search(int[][] A, boolean[][] visited, int i, int j, int min) {
        if (i < 0 || j < 0 || i >= A.length || j >= A[0].length || visited[i][j] || A[i][j] < min) return false;
        visited[i][j] = true;
        boolean found = false;
        if (i == A.length - 1 && j == A[0].length - 1) {
            found = true;
        }
        else {
            found = search(A, visited, i + 1, j, min) || search(A, visited, i - 1, j, min)
                    || search(A, visited, i, j + 1, min) || search(A, visited, i, j - 1, min);
        }
        return found;
    }

    // approach 3: Union Find
    // The basic idea is to sort all the numbers in the matrix in non-increasing order
    // and then start to apply union to the greatest value with its neighbors if neighbor
    // cells have already been visited. Once the first cell and the last cell is connected
    // then we know that it is after we union the current number to its neighbors that
    // connected the first and last number. This is also the maximum minimum value we can get.

    // time: O(m * n * log(m*n))
    // space: O(m * n)

    public int maximumMinimumPath_3(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;
        int m = A.length, n = A[0].length;
        int[] ids = new int[m * n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pq.offer(new int[]{A[i][j], i, j});
                ids[i * n + j] = i * n + j;
            }
        }
        boolean[][] visited = new boolean[m][n];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            visited[cur[1]][cur[2]] = true;
            for (int[] dir : dirs) {
                int r = cur[1] + dir[0];
                int c = cur[2] + dir[1];
                if (r < 0 || c < 0 || r >= m || c >= n || !visited[r][c]) continue;
                union(ids, n * cur[1] + cur[2], n * r + c);
                if (find(ids, 0) == find(ids, m * n - 1)) {
                    return cur[0];
                }
            }
        }
        return 0;
    }
    private void union(int[] ids, int p, int q) {
        int pp = find(ids, p);
        int pq = find(ids, q);
        ids[pp] = pq;
    }
    private int find(int[] ids, int p) {
        while (p != ids[p]) {
            ids[p] = ids[ids[p]];
            p = ids[p];
        }
        return p;
    }
}
