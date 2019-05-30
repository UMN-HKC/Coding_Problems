package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class P0133_CloneGraph {

    // initial approach: bfs
    // spent a lot of time figuring out my thought, but in the end still kind of messy
    // initially used a hashset to indicate visited node, however we don't have that
    // issue here since hashmap already serves that functionality.

    // time O(V + E)
    // space O(V)

    public Node cloneGraph_bfs(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Node clone = new Node(node.val);
        clone.neighbors = new ArrayList<>();
        map.put(node, clone);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node original = queue.poll();
            for (Node neighbor : original.neighbors) {
                if (!map.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    Node cloneNeighbor = new Node(neighbor.val);
                    cloneNeighbor.neighbors = new ArrayList<>();
                    map.put(neighbor, cloneNeighbor);
                }

                map.get(original).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }


    // approach 2: dfs

    // time: O(V + E)
    // space: O(V)

    public Node cloneGraph_dfs(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }
    public Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }
        return clone;
    }

}
