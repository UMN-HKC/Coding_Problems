package Leetcode;

public class P0364_NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) {
            return 0;
        }
        int prevSum = 0;
        int sum = 0;
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            queue.offer(ni);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    levelSum += ni.getInteger();
                }
                else {
                    for (NestedInteger j : ni.getList()) {
                        queue.offer(j);
                    }
                }
            }
            // each level will get accumulated n times where n is the number of level above it
            prevSum += levelSum;
            sum += prevSum;
        }
        return sum;
    }
}
