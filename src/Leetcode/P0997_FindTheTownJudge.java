package Leetcode;
import java.util.*;

public class P0997_FindTheTownJudge {

    // approach 1:
    // find the potential town judges and then validate it
    
    public int findJudge(int N, int[][] trust) {
        int[] trustOthersArray = new int[N];
        for (int i = 0; i < trust.length; i++) {
            trustOthersArray[trust[i][1] - 1]++;
        }
        Set<Integer> candidates = new HashSet<>();
        for (int i = 0; i < trustOthersArray.length; i++) {
            if (trustOthersArray[i] == N - 1) {
                candidates.add(i + 1);
            }
        }
        for (int i = 0; i < trust.length; i++) {
            if (candidates.contains(trust[i][0])) {
                candidates.remove(trust[i][0]);
            }
        }
        if (candidates.size() != 1) return -1;
        return (int)new ArrayList(candidates).get(0);
    }

    // approach 2: cleaner
    // add 1 to being trusted and subtract 1 from trusting others
    // thus only the one with exact N - 1 value is the town judge

    public int findJudge_2(int N, int[][] trust) {
        int[] trustOthersArray = new int[N];
        for (int i = 0; i < trust.length; i++) {
            trustOthersArray[trust[i][1] - 1]++;
            trustOthersArray[trust[i][0] - 1]--;
        }

        for (int i = 0; i < trustOthersArray.length; i++) {
            if (trustOthersArray[i] == N - 1) {
                return i + 1;
            }
        }

        return -1;
    }


}
