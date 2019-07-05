package Leetcode;
import java.util.*;

public class P0207_CourseSchedule {

    // approach 1: topological sort (bfs)

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        int cnt = 0;
        int[] inDegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        // get all courses' number of prerequisite courses
        for (int[] prerequisite : prerequisites) {
            int needToTake = prerequisite[0];
            inDegree[needToTake]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int finished = queue.poll();
            cnt++;
            for (int[] pair : prerequisites) {
                if (pair[1] == finished) {
                    if (--inDegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }
        return cnt == numCourses;
    }

    // approach 2: dfs (detecting cycle)

    LinkedList<Integer>[] graph;
    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;
        graph = new LinkedList[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            graph[pair[0]].add(pair[1]);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, visited)) {
                return false;
            }
        }
        return true;
    }
    public boolean hasCycle(int i, int[] visited) {
        if (visited[i] == 1) return true;
        if (visited[i] == 2) return false;
        visited[i] = 1;
        for (int pre : graph[i]) {
            if (hasCycle(pre, visited)) {
                return false;
            }
        }
        visited[i] = 2;
        return false;
    }
}
