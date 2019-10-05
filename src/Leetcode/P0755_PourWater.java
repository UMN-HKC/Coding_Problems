package Leetcode;

public class P0755_PourWater {

    // approach 1:
    // The basic idea is to greedily fill the leftmost util all heights to the left is not
    // lower than heights[K]. Then greedily fill the rightmost util all heights to the right
    // is not lower than heights[K]. If it is not possible to drop the water either to the
    // left or to the right, we simply add 1 to heights[K].

    // time: O(k * n)

    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            int cur = K;
            // greedily fill to the left
            while (cur > 0 && heights[cur] >= heights[cur - 1]) cur--;
            // greedily fill to the right
            while (cur < heights.length - 1 && heights[cur] >= heights[cur + 1]) cur++;
            // move back to K
            while (cur > K && heights[cur] >= heights[cur - 1]) cur--;
            heights[cur]++;
        }
        return heights;
    }
}
