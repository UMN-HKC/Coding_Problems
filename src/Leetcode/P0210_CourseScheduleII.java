package Leetcode;
import java.util.*;

public class P0210_CourseScheduleII {

    // approach 1: bfs
    // same as the first problem, just print out the result

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        int orderCnt = 0;
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            cnt++;
            int finished = queue.poll();
            order[orderCnt++] = finished;
            for (int[] pair : prerequisites) {
                if (pair[1] == finished) {
                    if (--indegree[pair[0]] == 0) {
                        queue.offer(pair[0]);
                    }
                }
            }
        }

        return cnt == numCourses ? order : new int[0];
    }
}
