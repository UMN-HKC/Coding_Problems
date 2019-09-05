package Leetcode;
import java.util.*;

public class P0379_DesignPhoneDirectory {

    // approach 1: O(n) for get()
    // need to be optimized

    int cnt;
    boolean[] isNumAvailable;
    public PhoneDirectory(int maxNumbers) {
        cnt = maxNumbers;
        isNumAvailable = new boolean[maxNumbers];
        Arrays.fill(isNumAvailable, true);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (cnt == 0) return -1;
        for (int i = 0; i < isNumAvailable.length; i++) {
            if (isNumAvailable[i]) {
                isNumAvailable[i] = false;
                cnt--;
                return i;
            }
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return isNumAvailable[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        cnt++;
        isNumAvailable[number] = true;
    }

    // approach 2: O(1) for all functions

    // use a queue to store available numbers and a set to store used numbers

    Queue<Integer> available;
    Set<Integer> used;
    public PhoneDirectory(int maxNumbers) {
        available = new LinkedList<>();
        used = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            available.offer(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.isEmpty()) return -1;
        int removed = available.poll();
        used.add(removed);
        return removed;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number)) {
            available.offer(number);
        }
    }
}
