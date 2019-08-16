package Leetcode;
import java.util.*;

public class P0202_HappyNumber {

    // approach 1: HashSet

    public boolean isHappy(int n) {
        if (n < 0) return false;
        Set<Integer> visited = new HashSet<>();
        while (n != 1) {
            int temp = n;
            int sum = 0;
            while (temp != 0) {
                int digit = temp % 10;
                temp /= 10;
                sum += digit * digit;
            }
            if (visited.contains(sum)) return false;
            visited.add(sum);
            n = sum;
        }
        return true;
    }
}
