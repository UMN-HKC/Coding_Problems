package Leetcode;

import java.util.Arrays;

public class P0274_HIndex {
//    这是第一遍做题根据hint的sort的解法，时间复杂度为O（nlogn）
    public int hIndex_sort(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int index = citations.length;
        Arrays.sort(citations);
        // int index = citations[citations.length - 1];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= index) {
                return index;
            }
            else {
                index--;
            }
        }
        return index;
    }
//    这是discussion里面提到的counting sort。也是第一次学习这个sorting algorithm，适用场景就是当数组里面的元素的
//    值和index有联系的时候。基本想法是第一次traversal，把元素的值放入相应index的bucket里面。第二次traversal进行
//    summation。
    public int hIndex_counting_sort(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }
}
