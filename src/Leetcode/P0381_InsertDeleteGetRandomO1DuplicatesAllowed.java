package Leetcode;
import java.util.*;

public class P0381_InsertDeleteGetRandomO1DuplicatesAllowed {

    // approach 1: hashmap(integer to set) + arraylist
    
    // similar approach as the initial problem. Since we allow duplicates, we
    // map our value to a set of its occurred indices.

    class RandomizedCollection {

        /** Initialize your data structure here. */
        Random rd;
        Map<Integer, Set<Integer>> map;
        List<Integer> list;
        public RandomizedCollection() {
            rd = new Random();
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean exist = map.containsKey(val);
            if (!exist) {
                map.put(val, new HashSet<>());
            }
            map.get(val).add(list.size());
            list.add(val);
            return !exist;
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {

            if (!map.containsKey(val)) {
                return false;
            }
            Set<Integer> indices = map.get(val);
            int loc = indices.iterator().next();
            indices.remove(loc);
            int lastVal = list.get(list.size() - 1);
            list.set(loc, lastVal);
            list.remove(list.size() - 1);
            map.get(lastVal).remove(list.size());
            if (loc != list.size()) {
                map.get(lastVal).add(loc);
            }
            if (indices.size() == 0) map.remove(val);
            return true;
        }


        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get(rd.nextInt(list.size()));
        }
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
