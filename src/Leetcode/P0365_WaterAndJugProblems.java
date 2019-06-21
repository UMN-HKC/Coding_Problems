package Leetcode;
import java.util.*;

public class P0365_WaterAndJugProblems {

    // couldn't figure out initially
    // idea borrowed from discussion board. The first approach is brute force using bfs, basically trying every
    // valid operation (adding x water/adding y water/pouring x water/pouring y water)

    public boolean canMeasureWater_brute_force_bfs(int x, int y, int z) {
        if (z < 0 || x + y < z) return false;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        queue.offer(0);
        while (!queue.isEmpty()) {
            int num = queue.poll();
            if (num + x <= x + y && set.add(num + x)) {
                queue.offer(num + x);
            }
            if (num + y <= x + y && set.add(num + y)) {
                queue.offer(num + y);
            }
            if (num - x >= 0 && set.add(num - x)) {
                queue.offer(num - x);
            }
            if (num - y >= 0 && set.add(num - y)) {
                queue.offer(num - y);
            }
            if (set.contains(z)) return true;
        }
        return false;
    }

    // approach 2: math solution (realize it is GCD problem)

    public boolean canMeasureWater(int x, int y, int z) {
        if (z < 0 || x + y < z) return false;
        if (x == z || y == z) return true;
        return z % gcd(x, y) == 0;
    }
    public int gcd(int num1, int num2) {
        if (num1 < num2) {
            return gcd(num2, num1);
        }
        while (num2 != 0) {
            int temp = num2;
            num1 %= num2;
            num2 = num1;
            num1 = temp;
        }
        return num1;
    }
}
