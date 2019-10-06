package Leetcode;
import java.util.*;

public class P0380_InsertDeleteGetRandomO1 {

    // approach 1: hashmap + arraylist

    // the basic idea is to use hash map to map value to their inserted index and use
    // arraylist to actually store the values. Insert and getRandom is simple. For remove(),
    // each time we remove, we move the last element in the arraylist to overwrite the
    // value that is to be removed. Then we remove the last element and then update
    // the map. Be careful that if the value gets removed is already the last element,
    // we do not need to update the last value's location since it is removed anyway.

    class RandomizedSet {

        /** Initialize your data structure here. */
        Map<Integer, Integer> map;
        List<Integer> list;
        Random random;
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            else {
                map.put(val, list.size());
                list.add(val);
                return true;
            }
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            else {
                if (map.get(val) == list.size() - 1) {
                    map.remove(val);
                    list.remove(list.size() - 1);
                }
                else {
                    int removeElementIdx = map.get(val);
                    int lastElement = list.get(list.size() - 1);
                    // remove from map and update map
                    map.remove(val);
                    map.put(lastElement, removeElementIdx);
                    // remove from list and update list
                    list.set(removeElementIdx, lastElement);
                    list.remove(list.size() - 1);
                }
                return true;
            }
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int idx = random.nextInt(list.size());
            return list.get(idx);
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
