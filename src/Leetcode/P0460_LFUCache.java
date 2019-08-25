package Leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class P0460_LFUCache {

    // approach 1: O(1)
    // The key idea is to use two hash maps to map key to value and key to its frequency
    // respectively. We will need another map that maps frequency to a linked hash set
    // of all keys that are of same frequency. Within this linked hash set, we will
    // add keys to the back of the hash set, and when evicting, evict the front by
    // utilizing iterator.
    // We will have a touch function whose sole responsibility is to increase frequency
    // of a key and update all its related values in other maps. So, both get() and push()
    // could utilize this function. However, in push(), we will need do more work

    // Note that, whenever we call push() and the cap is already full, we will reset
    // curMin to 1 because the frequency of the newly added key is 1 and this is
    // also the least frequency we can get.

    Map<Integer, Integer> values;
    Map<Integer, Integer> freqMap;
    Map<Integer, LinkedHashSet<Integer>> freqToList;
    int curMin;
    int cnt;
    int cap;
    public LFUCache(int capacity) {
        values = new HashMap<>();
        freqMap = new HashMap<>();
        freqToList = new HashMap<>();
        curMin = 1;
        cnt = 0;
        cap = capacity;
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            return -1;
        }
        touch(key);
        return values.get(key);
    }

    public void put(int key, int value) {
        if (cap == 0) return;
        if (values.containsKey(key)) {
            // if key is already present, just update it
            // in the freqToList and freqMap
            touch(key);
            // update the value for sure
            values.put(key, value);
            return;
        }
        // if cap is full
        if (cnt == cap) {
            // remove the least recently visted key from freqToList
            int removedKey = freqToList.get(curMin).iterator().next();
            freqToList.get(curMin).remove(removedKey);
            // remove it from map and freqMap as well
            values.remove(removedKey);
            freqMap.remove(removedKey);
            cnt--;
        }
        curMin = 1;
        if (!freqToList.containsKey(curMin)) {
            freqToList.put(curMin, new LinkedHashSet<>());
        }
        freqToList.get(curMin).add(key);
        freqMap.put(key, curMin);
        values.put(key, value);
        cnt++;
    }

    public void touch(int key) {
        int freq = freqMap.get(key);
        // remove the key from current freq and bump it to next freq;
        freqToList.get(freq).remove(key);
        freqMap.remove(key);
        if (freqToList.get(freq).size() == 0) {
            freqToList.remove(freq);
            if (curMin == freq)
                curMin++;
        }
        if (!freqToList.containsKey(freq + 1)) {
            freqToList.put(freq + 1, new LinkedHashSet<>());
        }
        freqToList.get(freq + 1).add(key);
        freqMap.put(key, freq + 1);
    }
}
