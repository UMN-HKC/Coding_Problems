package Leetcode;

public class P0258_AddDigits {

    // initial approach: brute force
    public int addDigits_initial(int num) {
        while (num >= 10) {
            int temp = 0;
            while (num != 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }

    // approach 2: congruence rule

    /***
     * note the pattern:
     0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ...
     0 1 2 3 4 5 6 7 8 9 1  2  3  4  5  6  7  8  9  1 ...
     */
    public int addDigits(int num) {
        return 1 + ((num - 1) % 9);
    }
}
