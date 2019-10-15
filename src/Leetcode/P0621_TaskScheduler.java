package Leetcode;

public class P0621_TaskScheduler {

    // approach 1:
    // link: https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation

    // The basic idea is to calculate the required idles slots that we need besides the
    // total number of tasks. To get that, we will need to determine the most frequent
    // tasks and the number of tasks of that frequency. Why do we need that? Because in
    // order to satisfy the n cooling times between each same task, we need to first
    // separate those most frequent tasks, and the rest of tasks will be easily inserted
    // among them and won't cause contradiction.
    // Once we get the most frequent tasks and the number of that frequency. We are able
    // to set up our "frame", and calculate number of idle slots we need after inserting
    // the rest of tasks into empty slots. Available tasks to insert into empty slots is
    // calculated by subtracting the "frame" which is the max frequency times the number of
    // that frequency.
    // Note that we are not actually placing tasks, we only need to calculate the number of
    // idle slots we need besides the total number of tasks. Thus, we return total + idles.

    // time: O(n)
    // space: O(1)

    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0;
        int maxCount = 0;
        for(char task : tasks) {
            counter[task - 'A']++;
            if(max == counter[task - 'A']) {
                maxCount++;
            }
            else if(max < counter[task - 'A']) {
                max = counter[task - 'A'];
                maxCount = 1;
            }
        }

        int partCount = max - 1;
        int partLength = n - (maxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - max * maxCount;
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}
