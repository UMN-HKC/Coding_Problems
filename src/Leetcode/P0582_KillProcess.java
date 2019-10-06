package Leetcode;

import java.util.*;

public class P0582_KillProcess {

    // approach 1: hashmap

    // The basic idea is to map each parent to its children. Then recurse kill() operation
    // on each of its children. During each kill() operation, we will kill each child's children
    // as well until we reach the leaf and there are no children left for that node.

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = pid.size();
        for (int i = 0; i < n; i++) {
            int child = pid.get(i);
            int parent = ppid.get(i);
            if (!map.containsKey(parent)) {
                map.put(parent, new HashSet<>());
            }
            map.get(parent).add(child);
        }
        killChildren(res, map, kill);
        return res;
    }
    public void killChildren(List<Integer> res, Map<Integer, Set<Integer>> map, int kill) {
        res.add(kill);
        if (map.containsKey(kill)) {
            Set<Integer> children = map.get(kill);
            for (Integer child : children) {
                killChildren(res, map, child);
            }
        }
    }
}
