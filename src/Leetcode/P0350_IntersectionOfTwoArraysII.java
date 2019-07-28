package Leetcode;
import java.util.*;

public class P0350_IntersectionOfTwoArraysII {

    // approach 1: hashmap

    // time: O(n)
    // space: O(n)

    public int[] intersect_1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                int curFreq = map.get(num);
                if (curFreq == 1) {
                    map.remove(num);
                }
                else {
                    map.put(num, curFreq - 1);
                }
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    // approach 2: two pointers

    // time: O(n)
    // space: O(n)

    public int[] intersect_2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            }
            else if (nums1[i] > nums2[j]) {
                j++;
            }
            else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        i = 0;
        int[] res = new int[list.size()];
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    // approach 3: binary search

    // time: O(nlogn), assume m < n
    // space: O(1) if result array is not considered as extra space

    public int[] intersect_3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return new int[0];
        if (nums1.length > nums2.length) {
            return intersect_3(nums2, nums1);
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length) {
            // find the index of lower bound of intersection elements
            int l = binarySearch(nums2, nums1[i], j, nums2.length - 1);
            if (l != -1) {
                j = l;
                while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
                    list.add(nums1[i]);
                    i++;
                    j++;
                }
            }
            else {
                i++;
            }
        }
        i = 0;
        int[] res = new int[list.size()];
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }
    public int binarySearch(int[] num, int target, int l, int r) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (num[m] >= target) {
                r = m - 1;
            }
            else {
                l = m + 1;
            }
        }
        if (l < num.length) return num[l] == target ? l : -1;
        else {
            return -1;
        }
    }

}
