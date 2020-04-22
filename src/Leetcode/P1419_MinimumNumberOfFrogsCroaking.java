package Leetcode;

public class P1419_MinimumNumberOfFrogsCroaking {

    // approach 1: state machine
    // The problem can be abstracted as counting the maximum number
    // of ongoing state machines. The following is the 5 states we have
    //   0 ---> 1 ---> 2 ---> 3 ---> 4
    //   c      r      o      a      k
    // each time we encounter one state, we decrement 1 from the previous
    // state because the current state is coming from the previous state.
    // But if the previous state is less than 1, it means the current
    // state is not valid, we simply return -1.
    // The result is the maximum sum of ongoing states. Note that when
    // we calculate the sum, we only add up the first 4 states because
    // the last state is the ending state.
    // In the end, we check if all states before the final state have
    // finished as well as if we have any final state.

    public int minNumberOfFrogs(String croakOfFrogs) {
        int[] cnt = new int[5];
        int max = 0;
        for (int i = 0; i < croakOfFrogs.length(); i++) {
            char c = croakOfFrogs.charAt(i);
            int idx = "croak".indexOf(c);
            if (idx == 0) {
                cnt[0]++;
            }
            else {
                if (cnt[idx - 1] < 1) {
                    return -1;
                }
                cnt[idx - 1]--;
                cnt[idx]++;
            }
            max = Math.max(max, cnt[0] + cnt[1] + cnt[2] + cnt[3]);
        }
        if (cnt[0] == 0 && cnt[1] == 0 && cnt[2] == 0 && cnt[3] == 0 && cnt[4] != 0) {
            return max;
        }
        return -1;
    }
}
