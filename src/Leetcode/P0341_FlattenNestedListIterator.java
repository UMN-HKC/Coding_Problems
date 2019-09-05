package Leetcode;

public class P0341_FlattenNestedListIterator {

    // approach 1: queue (recursion)
    // pre flatten the nested integer when doing initialization

    Queue<Integer> queue = new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        getIntegers(nestedList);
    }
    public void getIntegers(List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger ni = nestedList.get(i);
            if (ni.isInteger()) {
                queue.offer(ni.getInteger());
            }
            else {
                getIntegers(ni.getList());
            }
        }
    }
    @Override
    public Integer next() {
        if (hasNext()) {
            return queue.poll();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    // approach 2: stack, iterative
    // flatten on the go

    Stack<NestedInteger> stack;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) return false;
        while (!stack.empty() && !stack.peek().isInteger()) {
            NestedInteger ni = stack.pop();
            List<NestedInteger> list = ni.getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        return (!stack.empty() && stack.peek().isInteger());
    }
}
