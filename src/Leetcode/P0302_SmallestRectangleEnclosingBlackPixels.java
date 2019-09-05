package Leetcode;
import java.util.*;

public class P0302_SmallestRectangleEnclosingBlackPixels {

    // approach 1: bfs
    // The idea is to use bfs to explore the connected black pixels, and meanwhile
    // find the leftmost, rightmost, top and bottom black pixel's position.

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int minArea(char[][] image, int x, int y) {
        int leftMost = Integer.MAX_VALUE;
        int rightMost = Integer.MIN_VALUE;
        int top = Integer.MAX_VALUE;
        int bottom = Integer.MIN_VALUE;
        int m = image.length;
        int n = image[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        image[x][y] = '0';

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            leftMost = Math.min(leftMost, cur[1]);
            rightMost = Math.max(rightMost, cur[1]);
            top = Math.min(top, cur[0]);
            bottom = Math.max(bottom, cur[0]);
            for (int[] dir : dirs) {
                int r = cur[0] + dir[0];
                int c = cur[1] + dir[1];
                if (r < 0 || c < 0 || r >= m || c >= n || image[r][c] == '0') continue;
                image[r][c] = '0';
                q.offer(new int[]{r, c});
            }
        }
        return (rightMost - leftMost + 1) * (bottom - top + 1);
    }

    // approach 2: Binary search
    // The idea is basically use binary search to find the leftmost or top black
    // pixel and find the first white pixel to the right of the black pixel for
    // right and bottom.

    public int minArea_bs(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int left = binarySearchColumn(image, 0, y, 0, m, true);
        int right = binarySearchColumn(image, y, n, 0, m, false);
        int top = binarySearchRow(image, 0, x, left, right, true);
        int bottom = binarySearchRow(image, x, m, left, right, false);
        System.out.println(left + "," +  right + "," + top + "," + bottom);
        return (right - left) * (bottom - top);
    }
    public int binarySearchColumn(char[][] image, int l, int r, int beginRow, int endRow, boolean option) {
        while (l < r) {
            int k = beginRow;
            int mid = l + (r - l) / 2;
            while (k < endRow && image[k][mid] == '0') k++;
            if (k < endRow == option) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }
    public int binarySearchRow(char[][] image, int l, int r, int beginCol, int endCol, boolean option) {
        while (l < r) {
            int k = beginCol;
            int mid = l + (r - l) / 2;
            while (k < endCol && image[mid][k] == '0') k++;
            if (k < endCol == option) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l;
    }
}
