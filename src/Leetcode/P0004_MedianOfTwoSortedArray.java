package Leetcode;

public class P0004_MedianOfTwoSortedArray {

    // approach 1: binary search
    // The basic idea is to partition two array each into two parts, and we will have four
    // elements around the partition lines that we are interested(since they will determine median)
    // First of all, we know that number of elements in the left partition in these two arrays should be
    // equal to the number of elements in the right partition in these two arrays each time.
    //
    // With the knowledge of that, we will partition the smaller size array, say array x, to two parts,
    // and according to the partition in x, we know where to partition the second array, say y.
    // Then, we will compare the four critical elements.
    // We found the correct partition point when the following two conditions are satisfied:
    // 1. the max element in left partition in x array is smaller than min element in right partition in y array
    // 2. the max element in left partition in y array is smaller than min element in right partition in x array
    // otherwise, we move start or end pointer in x array accordingly
    // Note the edge case in this problem when one array is not used for calculating median. In this case,
    // we will set respective critical elements to INT_MAX or INT_MIN to avoid break of the program

    // time: O(log(MIN(m, n)))

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int x = nums1.length, y = nums2.length;
        int maxLeftX = Integer.MIN_VALUE, maxLeftY = Integer.MIN_VALUE;
        int minRightX = Integer.MAX_VALUE, minRightY = Integer.MAX_VALUE;
        // in here, start means there is no element to the left, and end means there are x elements to the left
        // we are doing binary search on the number of elements to the left instead of binary search on index
        int start = 0, end = x;

        while (start <= end) {
            // partitionX means there are partitionX elements to the left
            int partitionX = start + (end - start) / 2;
            // partitionY means there are partitionY elements to the left
            int partitionY = (x + y + 1) / 2 - partitionX;
            // this is the checking of the edge case
            // when there's already 0 elements to the left or 0 elements to the right
            // we set the critical elements to INT_MIN or INT_MAX accordingly
            maxLeftX = partitionX - 1 < 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            maxLeftY = partitionY - 1 < 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            minRightX = partitionX >= nums1.length ? Integer.MAX_VALUE : nums1[partitionX];
            minRightY = partitionY >= nums2.length ? Integer.MAX_VALUE : nums2[partitionY];
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                break;
            }
            else if (maxLeftX > minRightY) {
                end = partitionX - 1;
            }
            else {
                start = partitionX + 1;
            }
        }
        if ((x + y) % 2 == 0) {
            return ((double)(Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))) / 2;
        }
        else {
            return (double)Math.max(maxLeftX, maxLeftY);
        }
    }
}
