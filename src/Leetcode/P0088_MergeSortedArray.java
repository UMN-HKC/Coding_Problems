package Leetcode;

public class P0088_MergeSortedArray {

    // approach 1:
    // the basic idea is to iterate from end of nums1 array and also have two pointers
    // initially pointing to end of numbers in each array and put larger numbers
    // at the end of nums1 array. After first loop, keep pushing numbers in nums2 array
    // into nums1 array if n1 pointer has not reached the beginning of the nums2 array.

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int m1 = m-1, n1 = n-1, itr = nums1.length - 1;
        while (m1 >= 0 && n1 >= 0) {
            if (nums1[m1] > nums2[n1]) {
                nums1[itr] = nums1[m1];
                m1--;
            }
            else {
                nums1[itr] = nums2[n1];
                n1--;
            }
            itr--;
        }
        while (n1 >= 0) {
            nums1[itr] = nums2[n1];
            n1--;
            itr--;
        }
    }
}
