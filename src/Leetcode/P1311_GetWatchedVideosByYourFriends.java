package Leetcode;
import java.util.*;

public class P1311_GetWatchedVideosByYourFriends {

    // approach 1: BFS
    // The basic idea is to apply breadth first search on friends until we reach the required
    // level, and at that point we will add all videos watched by friends at that level to map.
    // After that, we will sort and add to result.

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int curLevel = 0;
        List<String> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(id);
        q.offer(id);
        while (curLevel != level) {
            curLevel++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int self = q.poll();
                int[] myFriends = friends[self];
                for (int friend : myFriends) {
                    if (friend != self && !visited.contains(friend)) {
                        q.offer(friend);
                        visited.add(friend);
                        if (curLevel == level) {
                            List<String> videos = watchedVideos.get(friend);
                            for (String video : videos) {
                                map.put(video, map.getOrDefault(video, 0) + 1);
                            }
                        }
                    }
                }
            }
        }
        PriorityQueue<String> pq = new PriorityQueue<>(
                (a, b) -> map.get(a) == map.get(b) ? a.compareTo(b) : map.get(a) - map.get(b));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry.getKey());
        }
        while (!pq.isEmpty()) {
            String cur = pq.poll();
            res.add(cur);
        }
        return res;
    }


}
