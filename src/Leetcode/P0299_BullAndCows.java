package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0299_BullAndCows {

    // approach 1: two passes
    // The basic idea i to use a frequency array to store how many times a number appear in secret
    // and then we will do two passes: first pass is to check bulls and second pass is to check cows

    public String getHint(String secret, String guess) {
        int[] s = new int[10];
        for (char c : secret.toCharArray()) {
            s[c - '0']++;
        }
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                s[guess.charAt(i) - '0']--;
                bulls++;
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) continue;
            int digit = guess.charAt(i) - '0';
            if (s[digit]-- > 0) {
                cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }

    // approach 2: one pass
    // The idea is to iterate over the numbers in secret and in guess and count all
    // bulls right away. For cows maintain an array that stores count of the number
    // appearances in secret and in guess. Increment cows when either number from
    // secret was already seen in guest or vice versa.

    public String getHint_OnePass(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) bulls++;
            // two digits are different
            else {
                // number from secret was already seen
                if (numbers[s] < 0) cows++;
                // number from guess was already seen
                if (numbers[g] > 0) cows++;
                numbers[s] ++;
                numbers[g] --;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
