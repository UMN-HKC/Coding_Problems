package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
}
