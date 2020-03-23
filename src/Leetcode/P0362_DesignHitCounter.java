package Leetcode;

public class P0362_DesignHitCounter {

    // approach 1: circular array

    // The key is that we will keep an rolling array of size 300 and simulate circular array
    // but in here we will keep a metadata: lastTime
    // For both required functions, we will call move(). What move() does is that it will
    // clear out all values inside 'gap' where
    // 'gap' is the difference between lastTime and current timestamp, which is also
    // region outside the valid 300 minutes time frame(when time stamp is already greater
    // than 300, but it handles the case under 300 as well). When time is under 300 minutes,
    // move() won't actually do anything. But when time is already higher than 300 minutes
    // lasttime is like the end pointer of the circular array, gap is the length that
    // we want to move the end pointer and meanwhile delete values inside gap.


    /** Initialize your data structure here. */
    int lastTime;
    int N;
    int total;
    int[] cnt;
    public P0362_DesignHitCounter() {
        N = 300;
        cnt = new int[N];
        lastTime = 0;
        total = 0;
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        move(timestamp);
        total++;
        cnt[timestamp % N]++;
        lastTime = timestamp;
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        move(timestamp);
        return total;
    }
    private void move(int timestamp) {
        int delta = Math.min(300, timestamp - lastTime);
        for (int i = 1; i <= delta; i++) {
            total -= cnt[(lastTime + i) % 300];
            cnt[(lastTime + i) % 300] = 0;
        }
    }
}
