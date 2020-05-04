package Leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P1436_DestinationCity {

    // approach 1:

    public String destCity(List<List<String>> paths) {
        Map<String, Integer> map = new HashMap<>();
        for (List<String> path : paths) {
            String src = path.get(0);
            String des = path.get(1);
            map.put(src, 1);
            map.put(des, map.getOrDefault(des, 0));
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) return entry.getKey();
        }
        return "";
    }
}
