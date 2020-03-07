package Leetcode;
import java.util.*;

public class P1366_RankTeamsbyVotes {

    // approach 1: sort

    // Build array rank where rank[i][j] is the number of votes for team i to be the j-th rank.
    // Sort the trams by rank array. if rank array is the same for two or more teams, sort them by the ID in ascending order.

    public String rankTeams(String[] votes) {
        if (votes.length == 1) {
            return votes[0];
        }
        int size = votes[0].length();
        Map<Character, int[]> map = new HashMap<>();
        for (String vote : votes) {
            for (int i = 0; i < size; i++) {
                char c = vote.charAt(i);
                map.putIfAbsent(c, new int[size]);
                map.get(c)[i]++;
            }
        }
        List<Character> list = new ArrayList(map.keySet());
        Collections.sort(list, (a, b) -> {
            for (int i = 0; i < size; i++) {
                if (map.get(a)[i] != map.get(b)[i]) {
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a - b;
        });

        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}
