package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0299_BullAndCows {
    public String getHint_initial(String secret, String guess) {
        if (secret == null || secret.length() == 0) {
            return "0A0B";
        }
        Map<Character, Integer> map = new HashMap<>();
        int A = 0;
        int B = 0;
        for (Character c : secret.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
                char c = secret.charAt(i);
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
        }
        for (int i = 0; i < secret.length(); i++) {
            char c = guess.charAt(i);
            if (secret.charAt(i) != c && map.containsKey(c)) {
                B++;
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
        }
        return Integer.toString(A) + "A" + Integer.toString(B) + "B";
    }

    public String getHint_OnePass(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) bulls++;
            else {
                if (numbers[s] < 0) cows++;
                if (numbers[g] > 0) cows++;
                numbers[s] ++;
                numbers[g] --;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
