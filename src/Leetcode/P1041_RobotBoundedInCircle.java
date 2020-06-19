package Leetcode;

public class P1041_RobotBoundedInCircle {

    // approach 1: brute force
    // idea inspired by the hint:
    // The robot stays in the circle iff (looking at the final vector) it changes direction (ie. doesn't stay pointing north), or it moves 0.

    public boolean isRobotBounded(String instructions) {
        int curX = 0, curY = 0;
        int curDir = 1;
        for (char c : instructions.toCharArray()) {
            if (c == 'G') {
                curX += (curDir == 2 || curDir == -2) ? (curDir == -2 ? -1 : 1) : 0;
                curY += (curDir == 1 || curDir == -1) ? (curDir == -1 ? -1 : 1) : 0;
            }
            else {
                if ((curDir == 1 && c == 'L') || (curDir == -1 && c == 'R')) {
                    curDir = -2;
                }
                else if ((curDir == 1 && c == 'R') || (curDir == -1 && c == 'L')) {
                    curDir = 2;
                }
                else if ((curDir == 2 && c == 'L') || (curDir == -2 && c == 'R')) {
                    curDir = 1;
                }
                else {
                    curDir = -1;
                }
            }
        }
        return curDir != 1 || (curX == 0 && curY == 0);
    }
}
