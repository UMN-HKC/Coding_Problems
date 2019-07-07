package Leetcode;
import java.util.*;

public class P0128_LongestConsecutiveSequence {

    // approach 1: naive approach - with sort O(nlogn) - need improvement

    public int longestConsecutive_sort(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int last = nums[0];
        int max = 1, cnt = 1, itr = 1;

        while (itr < nums.length) {
            while (itr + 1 < nums.length && nums[itr] == nums[itr+1]) itr++;
            if (nums[itr] == last + 1) {
                cnt++;
                max = Math.max(max, cnt);
            }
            else {
                cnt = 1;
            }
            last = nums[itr++];
        }
        return max;
    }

    // approach2: hashmap, keep a boundary
    // 1. See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n.
    //    Variables left and right will be the length of those two sequences, while 0 means there is no sequence
    //    and n will be the boundary point later. Store (left + right + 1) as the associated value to key n into the map.
    // 2. Use left and right to locate the other end of the sequences to the left and right of n respectively,
    //    and replace the value with the new length.

    public int longestConsecutive_map(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // map maps number to its maximum consecutive sequence
        Map<Integer,Integer> map = new HashMap<>();
        int max = 1;
        int sum = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int right = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                sum = left + right + 1;
                max = Math.max(max, sum);
                map.put(num, sum);

                // update neighbor
                map.put(num - left, sum);
                map.put(num + right, sum);
            }
            else {
                continue;
            }
        }
        return max;
    }

    // approach 2: union find

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        UF uf = new UF(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i] + 1)) {
                uf.union(map.get(nums[i]), map.get(nums[i] + 1));
            }

        }
        return uf.getMax();
    }

    class UF {
        int[] p;
        int[] cnt;
        int maxSize;
        public UF(int n) {
            p = new int[n];
            cnt = new int[n];
            maxSize = n == 0 ? 0 : 1;
            for (int i = 0; i < n; i++) {
                p[i] = i;
                cnt[i] = 1;
            }

        }
        public void union(int x, int y) {
            int px = find(p[x]);
            int py = find(p[y]);
            if (px == py) return;
            int sizex = cnt[px];
            int sizey = cnt[py];
            int unionSize = sizex + sizey;
            if (sizex > sizey) {
                p[py] = px;
                cnt[px] += cnt[py];
            }
            else {
                p[px] = py;
                cnt[py] += cnt[px];
            }
            maxSize = Math.max(maxSize, unionSize);
        }
        public int find(int x) {
            if (p[x] == x) return x;
            else {
                p[x] = find(p[x]);
                return p[x];
            }
        }
        public int getMax() {
            return maxSize;
        }
    }
}
