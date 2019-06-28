package Leetcode;
import java.util.*;


public class P0315_CountOfSmallerNumbersAfterSelf {

    // hard, couldn't figure out initially except brute force. But learned a new data structure
    // approach 1: Fenwick tree
    // great explanation and illustration with pics from Huahua: https://www.youtube.com/watch?v=2SVLYsq5W8M

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
}
