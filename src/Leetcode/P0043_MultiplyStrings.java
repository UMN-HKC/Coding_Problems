package Leetcode;

public class P0043_MultiplyStrings {

    // initial approach: try thinking about how we do multiplication by hand
    // and simulate that process
    // Basically, iterate from the back of the string for each layer of loop
    // the respective position in the result array is equal to i + j + 1


    // time: O(m * n)
    // space: O(m + n)

    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int len = len1+len2;
        int[] res = new int[len];
        for (int i = len1 - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            int carry = 0;
            for (int j = len2 - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int sum = carry + res[i+j+1] + d1 * d2;
                res[i+j+1] = sum % 10;
                carry = sum / 10;
                // be sure to check if we have a carry to add when we
                // reach j = 0 in the inner loop
                if (j == 0 && carry != 0) {
                    res[i+j] += carry;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int itr = 0;
        // skip 0s
        while (itr < res.length && res[itr] == 0) {
            itr++;
        }
        while (itr < res.length) {
            sb.append(res[itr++]);
        }
        // check if the result is 0 which will make sb.length() == 0
        return sb.length() == 0 ? "0" : new String(sb);
    }
}
