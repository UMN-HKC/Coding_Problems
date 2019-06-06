package Leetcode;


public class P0278_FirstBadVersion {
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    // initial approach: binary search for the first bad version

    // if we use exclusive termination condition, when we encounter a good version, we
    // set start to its right, so start always ends at a possible first bad version.
    // If we do it the other way around, setting start = mid and end = mid - 1, we might
    // run into infinite loop for case like 'ggb'(3,3) because mid will become start again but
    // start is at a good version and it never exceeds mid thus infinite loop

    // time: O(nlogn)
    // space: O(1)

    public int firstBadVersion_exclusive(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }

    // alternatively, we can also do inclusive condition
    // we know that start = mid + 1 will always gets us a possible first bad version
    // so when it terminates it means we are at a first bad version when we return start

    public int firstBadVersion_inclusive(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }
}
