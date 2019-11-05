package Leetcode;

public class P1056_ConfusingNumber {

    // approach 1: two pointers

    // The basic idea is to convert number to string and use two pointers to check if it contains
    // invalid numbers and if the number is same after rotation by checking from two sides and
    // making sure two characters pair is not contained in the rotatable string

    public static String rotatable = "00|11|69|96|88";
    public boolean confusingNumber(int N) {
        return isValid(String.valueOf(N));
    }
    public boolean isValid(String s) {
        boolean sameAfterRotation = true;
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            String c1 = s.substring(i, i + 1), c2 = s.substring(j, j + 1);
            if (!rotatable.contains(c1 + c2)) {
                sameAfterRotation = false;
            }
            if (!rotatable.contains(c1) || !rotatable.contains(c2)) {
                return false;
            }
            i++;
            j--;
        }
        return !sameAfterRotation;
    }

    // approach 2: build its reverse number (faster),  compatible with p.1088
    // instead of converting to string and then process the string, building its
    // reverse number only involves integer which is faster
    // note that trailing 0s case will be covered and we do not need to worry about that

    public static int[] valid = {0, 1, -1, -1, -1, -1, 6, -1, 8, 9};
    public static int[] reverse = {0, 1, 2, 3, 4, 5, 9, 7, 8, 6};
    public boolean confusingNumber_2(int N) {
        return isValid(N);
    }
    public boolean isValid(int num) {
        int rev = 0;
        int copy = num;
        while (num != 0) {
            int digit = num % 10;
            if (valid[digit] == -1) return false;
            rev = rev * 10 + reverse[digit];
            num /= 10;
        }
        return rev != copy;
    }
}
