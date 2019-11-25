package Leetcode;

public class P0287_FindTheDuplicateNumber {

    // approach 1: same approach to find_cycle_in_linkedlistII

    // Slow and fast pointer. The key point is to realize that if there exists a duplicate number
    // it will redirect our pointer to a previously visited index during our traversal.
    // For example, with array [2,1,3,4,2], the sequence of traversing(in terms of index) is
    //       ______________
    //      |              |
    // 0 -> 2 -> 3 -> 4 -> 2
    // note that when we encounter a duplicate number, we run into a cycle which is exactly what
    // happens in find cycle in linked list

    // time: O(n)

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        // note that we have to do while(true) because we need to let pointers move first
        // to simulate linkedlist pointer movements. If we do while(nums[slow] != nums[nums[fast]])
        // pointers may not move even one step when loop ends.
        while (true) {
            slow = nums[slow];
            fast = nums[fast];
            fast = nums[fast];
            if (slow == fast) {
                fast = 0;
                break;
            }
        }
        while (nums[slow] != nums[fast]) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return nums[slow];
    }

    // approach 2: binary search

    // slower but it is good to think about an alternate approach and this approach is interesting
    // the basic idea is that starting from 1 to n, we will pick a mid and for each mid, we will
    // iterate through the array and keep a cnt to record how many numbers are less or equal to
    // mid. If there are more than mid numbers in the array that are greater or equal to mid, we know
    // that the duplicate number is in the [1, mid] range. Otherwise, in the [mid+1, end] range.

    // Why is that? For example, with array [2,1,3,4,2], we pick mid=3, and we get number of elements
    // that is smaller or equal to 3 is 4, then according to pigeonhole principle, the duplicate number is
    // less than or equal to 3. So we narrow our search to [1,3].

    public int findDuplicate_binarysearch(int[] nums) {
        int start = 1, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt > mid) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return start;
    }
}

