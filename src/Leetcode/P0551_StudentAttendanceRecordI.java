package Leetcode;

public class P0551_StudentAttendanceRecordI {

    // approach 1: brute force

    public boolean checkRecord(String s) {
        int As = 0;
        int Ls = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                As++;
                Ls = 0;
            }
            else if (s.charAt(i) == 'L') {
                Ls++;
            }
            else {
                Ls = 0;
            }
            if (As == 2 || Ls == 3) return false;
        }
        return true;
    }
}
