package Leetcode;

import java.util.*;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

public class P0489_RobotRoomCleaner {

    // approach 1: backtracking

    // The basic idea is to recursively explore one direction until reach end and then turn
    // 90 degrees to its right. Note that to backtrack, we need to turn the robot around and
    // move one step further and then turn the robot around again so that the robot is facing
    // its original direction and ready to turn to its right. We use a set to keep track of
    // visited positions.

    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0);
    }
    public void dfs(Robot robot, Set<String> visited, int y, int x, int dir) {
        String pos = y + ":" + x;
        if (visited.contains(pos)) return;
        visited.add(pos);
        robot.clean();
        for (int i = 0; i < 4; i++) {
            if (robot.move()) {
                int r = y;
                int c = x;
                if (dir == 0) {
                    r--;
                }
                else if (dir == 1) {
                    c++;
                }
                else if (dir == 2) {
                    r++;
                }
                else {
                    c--;
                }

                dfs(robot, visited, r, c, dir);
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            robot.turnRight();
            dir = (dir + 1) % 4;
        }
    }
}
