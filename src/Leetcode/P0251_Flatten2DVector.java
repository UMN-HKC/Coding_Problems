package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0251_Flatten2DVector {

    // approach 1: list, pre process
    // space: O(n)

    List<Integer> list;
    int size;
    int cur;
    public Vector2D(int[][] v) {
        int cur = 0;
        list = new ArrayList<>();
        for (int i = 0; i < v.length; i++) {
            if (v[i] != null) {
                for (int j = 0; j < v[i].length; j++) {
                    list.add(v[i][j]);
                }
            }
        }
        size = list.size();
    }

    public int next() {
        return list.get(cur++);
    }

    public boolean hasNext() {
        return cur < size;
    }


    // approach 2: keep a row and a col variables, and read on the run. no need to pre process
    // space: O(1)

    int[][] v;
    int r;
    int c;

    public Vector2D(int[][] v) {
        this.v = v;
        r = 0;
        c = 0;
    }

    public int next() {
        int val = v[r][c];
        if (c == v[r].length - 1) {
            r++;
            c = 0;
        }
        else {
            c++;
        }
        return val;
    }

    public boolean hasNext() {
        if (r >= v.length) return false;
        while (r < v.length && v[r].length == 0) r++;
        return r < v.length;
    }
}
