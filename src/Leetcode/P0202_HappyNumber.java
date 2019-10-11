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

    // approach 2: slow and fast pointer to detect cycle

    public boolean isHappy_2(int n) {
        if (n == 1) return true;
        int slow = n, fast = n;
        do {
            slow = getNumber(slow);
            fast = getNumber(fast);
            fast = getNumber(fast);
        } while (slow != fast);
        return slow == 1;
    }
    public int getNumber(int n) {
        int num = 0;
        while (n != 0) {
            int digit = n % 10;
            n /= 10;
            num += digit * digit;
        }
        return num;
    }
}
