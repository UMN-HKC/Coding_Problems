package Leetcode;
import java.util.*;


public class P0315_CountOfSmallerNumbersAfterSelf {

    // approach 1: Fenwick tree
    // link: https://www.youtube.com/watch?v=2SVLYsq5W8M

    // To solve the problem with Binary Index Tree(Fenwick tree), we need to convert the
    // problem first. So, finding smaller numbers after self is the same as finding smaller
    // numbers before self with reversed array. Then, we can further convert the problem to
    // finding how many smaller ranks are before the current rank, where we map numbers to
    // their respective rank(relative order in a distinct sorted array). We can preprocess
    // the mapping by make a copy of the original array, sort the array and then iterating
    // through the array to map each number to its respective rank.
    // Next, we can apply BIT to solve the problem. Basically, we will iterate from the back
    // of the original array, and for each number, we can get its rank directly and do a
    // query on (rank - 1) to get the number of smaller ranks before the current rank, and
    // also remember to make a update call.

    // time: O(nlogn)
    // space: O(k), k is the number of unique numbers in the array

    class BIT {
        public int[] rank;
        public int getLowBit(int index) {
            return (-index)&index;
        }
        public BIT(int n) {
            rank = new int[n+1];
        }
        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += rank[index];
                index -= getLowBit(index);
            }
            return res;
        }
        public void update(int index, int delta) {
            while (index < rank.length) {
                rank[index] += delta;
                index += getLowBit(index);
            }
            return;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < sorted.length; i++) {
            if (!map.containsKey(sorted[i])) {
                map.put(sorted[i], rank);
                rank++;
            }
        }
        BIT bit = new BIT(map.size());
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int temp = bit.query(map.get(nums[i]) - 1);
            res.addFirst(temp);
            bit.update(map.get(nums[i]), 1);
        }
        return res;
    }

    // approach 2: merge sort (mind blowing)
    // link: https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation

    // The basic idea is that the smaller numbers on the right of a number are exactly those
    // that jump from its right to its left during a stable sort. We will keep a rightCount
    // variable to record how many times right numbers jumps(right number smaller than left number).
    // When we add left number(left <= right), we add rightCount to corresponding index. When we
    // add right number(left > right), we increment rightCount by 1, meaning right side number
    // makes a jump.
    // Note that we do not sort the values directly, instead we will create an additional array
    // that stores the indices and also use a map to map indices to their values, and we sort
    // indices!

    public List<Integer> countSmaller_2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(i, nums[i]);
        }
        int[] idxArr = new int[len];
        for (int i = 0; i < len; i++) {
            idxArr[i] = i;
        }
        int[] cnt = new int[len];
        mergeSort(map, cnt, idxArr, 0, len - 1);
        for (int i = 0; i < cnt.length; i++) {
            res.add(cnt[i]);
        }
        return res;
    }
    public void mergeSort(Map<Integer, Integer> map, int[] cnt, int[] idxArr, int l, int r) {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        mergeSort(map, cnt, idxArr, l, m);
        mergeSort(map, cnt, idxArr, m + 1, r);
        merge(map, cnt, idxArr, l, m, r);
    }

    public void merge(Map<Integer, Integer> map, int[] cnt, int[] idxArr, int l, int m, int r) {
        int[] sorted = new int[r - l + 1];
        int rightCount = 0;
        int k = 0;
        int i = l, j = m + 1;
        while (i <= m || j <= r) {
            if (j == r + 1) {
                sorted[k++] = idxArr[i];
                cnt[idxArr[i]] += rightCount;
                i++;
            }
            else if (i == m + 1) {
                sorted[k++] = idxArr[j];
                j++;
            }
            else {
                int leftVal = map.get(idxArr[i]);
                int rightVal = map.get(idxArr[j]);
                if (leftVal <= rightVal) {
                    sorted[k++] = idxArr[i];
                    cnt[idxArr[i]] += rightCount;
                    i++;
                }
                else {
                    sorted[k++] = idxArr[j];
                    rightCount++;
                    j++;
                }
            }
        }
        for (int a = l; a <= r; a++) {
            idxArr[a] = sorted[a - l];
        }
    }
}
