package Leetcode;

import java.util.Arrays;

public class P1465_MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {

    // approach 1: array, sort
    // The basic idea is to find the maximum cut width and cut height in the
    // whole cake. So, we could sort arrays first and then it's easy to find
    // them. Note that when we calculate maxH * maxW, we need to cast them to
    // long type first, otherwise the multiplication will result in overflow.

    public static final int modulo = (int)Math.pow(10, 9) + 7;
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxH = horizontalCuts[0], maxW = verticalCuts[0];

        for (int i = 1; i <= horizontalCuts.length; i++) {
            if (i == horizontalCuts.length) {
                maxH = Math.max(maxH, (h - horizontalCuts[i - 1]) % modulo);
            }
            else {
                maxH = Math.max(maxH, (horizontalCuts[i] - horizontalCuts[i - 1]) % modulo);
            }
        }

        for (int i = 1; i <= verticalCuts.length; i++) {
            if (i == verticalCuts.length) {
                maxW = Math.max(maxW, (w - verticalCuts[i - 1]) % modulo);
            }
            else {
                maxW = Math.max(maxW, (verticalCuts[i] - verticalCuts[i - 1]) % modulo);
            }
        }
        return (int)(((long)maxH * (long)maxW) % modulo);
    }
}
