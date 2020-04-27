package Leetcode;

public class P1424_DiagonalTraverseII {

    // approach 1:
    // https://leetcode.com/problems/diagonal-traverse-ii/discuss/597741/Clean-Simple-Easiest-Explanation-with-a-picture-and-code-with-comments

    // recognize the pattern that elements on the same diagonal have the same sum of row and column
    // so we can just do a regular traversal through the list of list of numbers and add each number
    // into their respective "diagonal bucket".

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        int cnt = 0;
        int maxSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != null && nums.get(i).size() != 0) {
                List<Integer> list = nums.get(i);
                for (int j = 0; j < list.size(); j++) {
                    cnt++;
                    int sum = i + j;
                    maxSum = Math.max(sum, maxSum);
                    map.putIfAbsent(sum, new LinkedList<>());
                    map.get(sum).addFirst(list.get(j));
                }
            }
        }
        int[] res = new int[cnt];
        cnt = 0;
        for (int i = 0; i <= maxSum; i++) {
            if (map.containsKey(i)) {
                LinkedList<Integer> list = map.get(i);
                for (int j = 0; j < list.size(); j++) {
                    res[cnt] = list.get(j);
                    cnt++;
                }
            }
        }
        return res;
    }
}
