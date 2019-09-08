package Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P0296_BestMeetingTime {

    // approach 1: find median
    // time: O(Math.max(nlogn, m * n)
    // space: O(m + n)

//    这道题让我们求最佳的开会地点，该地点需要到每个为1的点的曼哈顿距离之和最小，题目中给了我们
//    提示，让我们先从一维的情况来分析，那么我们先看一维时有两个点A和B的情况,
//
//    ______A_____P_______B_______
//
//    那么我们可以发现，只要开会为位置P在 [A, B] 区间内，不管在哪，距离之和都是A和B之间的距离，
//    如果P不在 [A, B] 之间，那么距离之和就会大于A和B之间的距离，那么我们现在再加两个点C和D：
//
//    ______C_____A_____P_______B______D______
//
//    我们通过分析可以得出，P点的最佳位置就是在 [A, B] 区间内，这样和四个点的距离之和为AB距离加上
//    CD 距离，在其他任意一点的距离都会大于这个距离，那么分析出来了上述规律，这题就变得很容易了，
//    我们只要给位置排好序，然后用最后一个坐标减去第一个坐标，即 CD 距离，倒数第二个坐标减去第二个坐标，
//    即 AB 距离，以此类推，直到最中间停止，那么一维的情况分析出来了，二维的情况就是两个一维相加即可

    public int minTotalDistance_1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(cols);

        int i = 0, j = rows.size() - 1;
        int total = 0;
        while (i < j) {
            total += rows.get(j) - rows.get(i);
            total += cols.get(j) - cols.get(i);
            i++;
            j--;
        }
        return total;
    }

    // approach 2: bucket-like approach O(n) time and O(n) space

    // The idea is similar to the approach 1, but instead of comparison sorting,
    // we use a bucket to keep our information sorted. The idea is to to have a
    // row bucket and col bucket and iterate the grid and increment respective
    // row indices and col indices position in the row and col bucket each time
    // we encounter a 1.
    // Afterwards, it is linear time to scan through both buckets and get our result

    public int minTotalDistance_2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rowBucket = new int[m];
        int[] colBucket = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowBucket[i]++;
                    colBucket[j]++;
                }
            }
        }
        return calculateBucket(rowBucket) + calculateBucket(colBucket);
    }
    public int calculateBucket(int[] bucket) {
        int i = 0, j = bucket.length - 1;
        int total = 0;
        while (i < j) {
            while (i < j && bucket[i] == 0) i++;
            while (i < j && bucket[j] == 0) j--;
            if (i < j) total += j - i;
            bucket[j]--;
            bucket[i]--;
        }
        return total;
    }
}
