package Leetcode;

public class P0374_GuessNumberHigherOrLower {

    /* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
   int guess(int num); */

    public int guessNumber(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int guess = guess(mid);
            if (guess == 0) {
                return mid;
            }
            else if (guess == 1) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return start;
    }
}
