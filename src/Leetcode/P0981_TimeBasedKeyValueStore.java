package Leetcode;

import java.util.*;

public class P0981_TimeBasedKeyValueStore {

    // approach 1: map
    // The basic idea is to map key string to an ordered data structure in terms of timestamp
    // and also stores the value string. Also, we need to be able to get the largest previous
    // timestamp that is smaller than the given timestamp. Thus, it is natural to think about
    // using hash map to map key string to a treemap which uses timestamp as treemap's key

    Map<String, TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }
        TreeMap<Integer, String> order = map.get(key);
        order.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> order = map.get(key);
        Map.Entry<Integer, String> entry = order.floorEntry(timestamp);
        if (entry == null) return "";
        return entry.getValue();
    }

    // a little improve, use list to store timestamps.
    // because timestamps are guaranteed to be strictly increasing, so inserting them
    // into the list will result in a sorted list and we can apply binary search for get()
    // but without using treemap, we reduce set() operation to be O(1) instead of O(logn)
    
    Map<String, List<Integer>> map;
    Map<Integer, String> valueMap;
    public TimeMap() {
        map = new HashMap<>();
        valueMap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(timestamp);
        valueMap.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        List<Integer> list = map.get(key);
        int l = 0, r = list.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (list.get(m) == timestamp) {
                return valueMap.get(timestamp);
            }
            if (list.get(m) < timestamp) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return r >= 0 ? valueMap.get(list.get(r)) : "";
    }
}
