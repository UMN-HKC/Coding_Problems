package Leetcode;

public class P1232_CheckIfItIsAStraightLine {

    // approach 1: check for slope
    // first check for vertical line and horizontal line
    // and then check for other case

    public boolean checkStraightLine(int[][] coordinates) {
        int m = coordinates.length;
        if (coordinates[0][0] == coordinates[1][0]) {
            for (int i = 1; i < m - 1; i++) {
                if (coordinates[i][0] != coordinates[i + 1][0]) return false;
            }
            return true;
        }
        else if (coordinates[0][1] == coordinates[1][1]) {
            for (int i = 1; i < m - 1; i++) {
                if (coordinates[i][1] != coordinates[i + 1][1]) return false;
            }
            return true;
        }
        double k = (double)(coordinates[0][1] - coordinates[1][1])
                / (coordinates[0][0] - coordinates[1][0]);

        for (int i = 1; i < m - 1; i++) {
            if (coordinates[i][0] == coordinates[i + 1][0]
                    || coordinates[i][1] == coordinates[i + 1][1]) {
                return false;
            }
            double curK = (double)(coordinates[i][1] - coordinates[i + 1][1])
                    / (coordinates[i][0] - coordinates[i + 1][0]);

            if (curK != k) return false;
        }
        return true;
    }

    // approach 2: cleaner
    // to avoid checking divisionByZero, we could transform slope calculation
    // which is originally (y1 - y0) / (x1 - x0) = (y2 - y1) / (x2 - x1) to
    // (y1 - y0) * (x2 - x1) = (y2 - y1) * (x1 - x0)

    public boolean checkStraightLine_2(int[][] coordinates) {
        int x0 = coordinates[0][0], x1 = coordinates[1][0], y0 = coordinates[0][1], y1= coordinates[1][1];
        int dx = coordinates[0][0] - coordinates[1][0], dy = coordinates[0][1] - coordinates[1][1];

        for (int i = 2; i < coordinates.length; i++) {
            if ((coordinates[i][0] - x1) * dy != (coordinates[i][1] - y1) * dx) {
                return false;
            }
        }
        return true;
    }

}
