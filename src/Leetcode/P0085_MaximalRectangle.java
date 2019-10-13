package Leetcode;
import java.util.*;

public class P0085_MaximalRectangle {

    // Both approaches are based on approaches to 84.Largest Rectangle In Histogram

    // approach 1: DP
    // the following analysis credits to wahcheung
    //
    //    [
    //        ["1","0","1","0","0"],
    //        ["1","0","1","1","1"],
    //        ["1","1","1","1","1"],
    //        ["1","0","0","1","0"]
    //    ]
    //    策略: 把matrix看成多个直方图, 每一行及其上方的数据都构成一个直方图, 需要考察matrix.size()个直方图
    //    对于每个点(row, col), 我们最后都计算以这个点上方的连续的'1'往left, right方向延申可以得到的最大的矩形的面积
    //    通过这种方法获取的矩形一定会把最大的矩形包含在内
    //    height[row][col]记录的是(row, col)这个坐标为底座的直方图柱子的高度, 如果这个点是'0', 那么高度当然是0了
    //    left[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最左边的位置
    //    right[row][col]记录的是(row, col)这个坐标点对应的height可以延申到的最右边的位置+1
    //    以上面的matrix为例,
    //    对于(row=2, col=1)这个点, left=0, right=5, height=1
    //    对于(row=2, col=2)这个点, left=2, right=3, height=3
    //    (2,2)这个点与(2,1)紧挨着,left和right却已经变化如此之大了, 这是因为left和right除了受左右两边的'1'影响, 还受到了其上方连续的'1'的制约
    //    由于点(2,2)上有height=3个'1', 这几个'1'的left的最大值作为当前点的left, 这几个'1'的right的最小值作为当前点的right
    //    因此, 实际上, 我们是要找以hight对应的这条线段往左右两边移动(只能往全是'1'的地方移动), 可以扫过的最大面积
    //    当hight与目标最大矩形区域的最短的height重合时, 最大矩形的面积就找到了, 如上面的例子, 就是点(2,3)或(2,4)对应的height

    // time: O(m * n)
    // space: O(n)

    public int maximalRectangle_dp(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(right, n);

        for (int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n;
            // update left array and height array;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                    height[j]++;
                }
                else {
                    height[j] = 0;
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // update right array
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                }
                else {
                    right[j] = n;
                    curRight = j;
                }
            }
            // update max area
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j] - left[j]) * height[j]);
            }

        }
        return max;
    }

    // approach 2: stack
    // same approach to maximal rectangle in histogram, but in here we need to build histogram
    // by ourselves

    // time: O(m * n)
    // space: O(n)

    public int maximalRectangle_stack(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] histogram = new int[n];
        int max = 0;

        for (int i = 0; i < m; i++) {
            buildHistogram(matrix, histogram, i);
            Stack<Integer> s = new Stack<>();
            int j = 0;
            while (j <= n) {
                int height = j == n ? 0 : histogram[j];
                if (s.empty() || histogram[s.peek()] <= height) {
                    s.push(j);
                    j++;
                }
                else {
                    int tp = s.pop();
                    max = Math.max(max, histogram[tp] * (j - 1 - (s.empty() ? -1 : s.peek())));
                }
            }
        }
        return max;
    }
    public void buildHistogram(char[][] matrix, int[] h, int i) {
        for (int j = 0; j < matrix[i].length; j++) {
            if (matrix[i][j] == '0') {
                h[j] = 0;
            }
            else {
                h[j] += 1;
            }
        }
        return;
    }
}
