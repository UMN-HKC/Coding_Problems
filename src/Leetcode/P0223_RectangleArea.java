package Leetcode;

public class P0223_RectangleArea {

    // approach 1:

    // The key is to realize max and min pattern in getting overlapping area

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // if they do not overlap, simply return sum of their area
        if (E >= C || A >= G || F >= D || B >= H) return (G - E) * (H - F)  + (C - A) * (D - B);
        // otherwise, calculate overlap of bottom left point and top right point
        // max and min can be proved by examples.
        int I = Math.max(A, E);
        int J = Math.max(B, F);
        int K = Math.min(C, G);
        int L = Math.min(D, H);
        int res = (C - A) * (D - B) + (G - E) * (H - F) - (K - I) * (L - J);
        return res;
    }
}
