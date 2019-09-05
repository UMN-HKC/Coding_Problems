package Leetcode;

import java.util.*;

public class P0170_TwoSumIII {

    // approach 1: O(n) add, O(1) find
    // TLE

    // Some test cases have a lot of add and few find operations
    // so optimizing find is not efficient in here

    Set<Integer> nums;
    Set<Integer> values;
    public TwoSum() {
        nums = new HashSet<>();
        values = new HashSet<>();
    }
    public void add(int number) {
        if (nums.contains(number)) {
            values.add(number * 2);
        }
        else {
            for (int num : nums) {
                values.add(num + number);
            }
            nums.add(number);
        }

    }
    public boolean find(int value) {
        return values.contains(value);
    }

    // approach 2: O(1) add and O(n) find -> AC
    // 188 ms

    // use a map to store numbers and its frequency. The idea is that when we
    // call find operation, we will iterate through the map entries and for each
    // entry, we try to find if there exists another value in the map that their
    // sum equals the target value

    Map<Integer, Integer> map;
    public TwoSum() {
        map = new HashMap<>();
    }
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            if ((key == value - key && val > 1) || (key != value - key && map.containsKey(value - key))) {
                return true;
            }
        }
        return false;
    }

    // approach 3: O(1) add and O(n) find
    // 120 ms

    // instead of iterating through map entry, we use an extra list to store unique
    // numbers and then iterate through the list which in this case is much faster
    // than iterating through map entries

    List<Integer> nums;
    Map<Integer, Integer> map;
    public TwoSum() {
        nums = new ArrayList<>();
        map = new HashMap<>();
    }
    public void add(int number) {
        if (!map.containsKey(number)) {
            nums.add(number);
        }
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    public boolean find(int value) {
        for (int i = 0; i < nums.size(); i++) {
            int num1 = nums.get(i);
            int num2 = value - num1;
            if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) {
                return true;
            }
        }
        return false;
    }

    // approach 4: O(1) add O(n) find
    // 56 ms

    // same as approach 3, but we optimize it by adding upper bound and lower bound

    int min;
    int max;
    List<Integer> nums;
    Map<Integer, Integer> map;
    public TwoSum() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        nums = new ArrayList<>();
        map = new HashMap<>();
    }
    public void add(int number) {
        min = Math.min(number, min);
        max = Math.max(number, max);
        if (!map.containsKey(number)) {
            nums.add(number);
        }
        map.put(number, map.getOrDefault(number, 0) + 1);
    }
    public boolean find(int value) {
        // if the target value is less than double of min or greater than double of
        // max, then it is not possible to find the sum equaling the value
        if (value < min * 2 || value > max * 2) return false;
        for (int i = 0; i < nums.size(); i++) {
            int num1 = nums.get(i);
            int num2 = value - num1;
            if ((num1 == num2 && map.get(num1) > 1) || (num1 != num2 && map.containsKey(num2))) {
                return true;
            }
        }
        return false;
    }
}
