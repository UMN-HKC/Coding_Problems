package Leetcode;

import java.util.Stack;

public class P0735_AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length < 2) return asteroids;

        // stack stores all stable asteroids
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++) {
            // if current asteroids is moving right, add it to stack
            if (asteroids[i] > 0) {
                stack.push(asteroids[i]);
            }
            else {
                boolean leftMovingAsteroidExist = true;
                while (!stack.empty() && stack.peek() > 0) {
                    // check for collision
                    if (Math.abs(stack.peek()) == Math.abs(asteroids[i])) {
                        leftMovingAsteroidExist = false;
                        stack.pop();
                        break;
                    }
                    else if (Math.abs(stack.peek()) > Math.abs(asteroids[i])) {
                        leftMovingAsteroidExist = false;
                        break;
                    }
                    else {
                        stack.pop();
                    }
                }
                if (leftMovingAsteroidExist) {
                    stack.push(asteroids[i]);
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
