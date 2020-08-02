package Leetcode;

public class P1344_AngleBetweenHandsOfAClock {

     // approach 1:

    public double angleClock(int hour, int minutes) {
        double degreeForHour = 30 * (hour + (double)minutes / 60);
        double degreeForMin = minutes * 6;
        return calculateAngle(degreeForHour, degreeForMin);
    }
    private double calculateAngle(double d1, double d2) {
        if (d1 < d2) {
            return calculateAngle(d2, d1);
        }
        if (d1 - d2 > 180) {
            return d2 + 360 - d1;
        }
        else {
            return d1 - d2;
        }
    }
}
