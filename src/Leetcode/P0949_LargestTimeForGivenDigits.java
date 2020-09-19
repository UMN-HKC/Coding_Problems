package Leetcode;

public class P0949_LargestTimeForGivenDigits {

    // approach 1: brute force (dfs, all permutations)

    String curLargest = "";
    public String largestTimeFromDigits(int[] A) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[4];
        dfs(A, visited, sb);
        return curLargest.length() == 0 ? curLargest : curLargest.substring(0, 2) + ":" + curLargest.substring(2);
    }
    private void dfs(int[] A, boolean[] visited, StringBuilder sb) {
        if (sb.length() == 4) {
            if (isValidAndLarger(sb)) {
                curLargest = new String(sb);
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(A[i]);
                dfs(A, visited, sb);
                visited[i] = false;
                sb.setLength(sb.length() - 1);
            }
        }
    }
    private boolean isValidAndLarger(StringBuilder sb) {
        // validate
        if (sb.charAt(0) > '2' || sb.charAt(2) > '5' || (sb.charAt(0) == '2' && sb.charAt(1) > '3')) {
            return false;
        }
        // check if it is larger
        if (curLargest.length() == 0) {
            return true;
        }
        else {
            int curHour = Integer.parseInt(curLargest.substring(0, 2));
            int nextHour = Integer.parseInt(sb.substring(0, 2));
            int curMin = Integer.parseInt(curLargest.substring(2));
            int nextMin = Integer.parseInt(sb.substring(2));
            if (nextHour < curHour) {
                return false;
            }
            else if (nextHour > curHour) {
                return true;
            }
            else {
                return nextMin > curMin;
            }
        }
    }
}
