package Leetcode;
import java.util.*;

public class P0266_PalindromePermutation {

    // approach 1: hashmap
    // The key is to realize that if we have more than 1 odd number of characters
    // we cannot form palindrome for this string.

    public boolean canPermutePalindrome(String s) {
        int cnt = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) cnt++;
        }
        return cnt < 2;
    }
}
