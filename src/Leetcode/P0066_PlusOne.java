package Leetcode;

public class P0066_PlusOne {


    // initial approach: brute force

    public int[] plusOne_initial(int[] digits) {
        if (digits == null || digits.length == 0) {
            int[] res = new int[1];
            res[0] = 1;
            return res;
        }
        int n = digits.length;
        if (digits[n - 1] != 9) {
            digits[n - 1] += 1;
            return digits;
        }
        else {
            int carry = 1;
            int itr = n - 2;
            digits[n - 1] = 0;

            while (itr >= 0 && carry != 0) {
                int sum = digits[itr] + carry;
                digits[itr] = sum % 10;
                carry = sum / 10;
                itr--;
            }
            if (carry != 0) {
                int[] res = new int[digits.length + 1];
                for (int i = 0; i < res.length; i++) {
                    res[i] = i == 0 ? 1 : digits[i - 1];
                }
                return res;
            }
            else {
                return digits;
            }
        }
    }

    // approach 2: idea borrowed from discussion board (elegant approach)
    // the basic idea is that iterate from back of the array and whenever
    // the digit is not 9, we increment it and we are done since there's no
    // need to carry. Otherwise, we set it to 0 and continue.
    // if we are done with while loop which means the last operation sets
    // the digit to 0 and we have a carry, then simply create a new array
    // and set the first digit to 1.

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            else {
                digits[i] = 0;
            }
        }
        int[] res = new int[n+1];
        res[0] = 1;
        return res;
    }
}
