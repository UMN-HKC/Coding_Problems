package Leetcode;

public class P0371_SumOfTwoIntegers {

    // idea from discussion board
    // https://leetcode.com/problems/sum-of-two-integers/discuss/132479/Simple-explanation-on-how-to-arrive-at-the-solution
    // the idea is to use & to find carry and use ^ to do the sum. The trick here is how to combine these
    // two operators to perform integer summation. Basically, we use an extra variable to help us store the
    // temp result from a&b

    public int getSum(int a, int b) {
        int temp = 0;
        // iteration stops when there's no carry left
        while (b != 0) {
            // temp stores the temporary carry from a&b
            temp = a&b;
            // a stores the summation result from a + b, however without incorporating carry
            a = a ^ b;
            // shift carry one bit to its left, which is the correct position
            b = temp << 1;
        }
        return a;
    }

}
