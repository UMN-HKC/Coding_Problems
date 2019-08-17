package Leetcode;
import java.util.Random;

public class P0398_RandomPickIndex {

    // reservoir sampling
    // the basic idea is that each time we call pick, we will go through the entire
    // array, and use cur to record target index stored so far and cnt to store the
    // number of target number we have encountered. cnt combined with random generator
    // guarantees the equal possibility

    Random rd;
    int[] array;
    public Solution(int[] nums) {
        array = nums;
        rd = new Random();
    }

    public int pick(int target) {
        int cur = -1;
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                cnt++;
                if (rd.nextInt(cnt) == cnt - 1) {
                    cur = i;
                }
            }
        }
        return cur;
    }
}
