package Leetcode;
import java.util.*;

public class P0310_MinimumHeightTrees {

    // approach 1: bfs
//    We start from every end, by end we mean vertex of degree 1 (aka leaves). We let the
//    pointers move the same speed. When two pointers meet, we keep only one of them, until
//    the last two pointers meet or one step away we then find the roots.
//    It is easy to see that the last two pointers are from the two ends of the longest
//    path in the graph.
//    The actual implementation is similar to the BFS topological sort.
//    Remove the leaves, update the degrees of inner vertexes. Then remove the new leaves.
//    Doing so level by level until there are 2 or 1 nodes left.

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        Set<Integer>[] G = new HashSet[n];
        for (int i = 0; i < n; i++) {
            G[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            int p = edge[0], q = edge[1];
            G[p].add(q);
            G[q].add(p);
        }

        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (G[i].size() == 1) {
                q.offer(i);
            }
        }
        while (n > 2) {
            int size = q.size();
            n -= size;
            for (int i = 0; i < size; i++) {
                int node = q.poll();
                for (int neighbor : G[node]) {
                    G[neighbor].remove(node);
                    if (G[neighbor].size() == 1) {
                        q.offer(neighbor);
                    }
                }
            }
        }
        return q;
    }
}
