package Leetcode;

public class P0067_AddBinary {

    // initial approach

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) return b;
        if (b == null || b.length() == 0) return a;

        int itrA = a.length() - 1, itrB = b.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while (itrA >= 0 || itrB >= 0 || carry != 0) {
            int sum = 0;
            if (itrA >= 0 || itrB >= 0) {
                if (itrA >= 0) {
                    sum += a.charAt(itrA) - '0';
                    itrA--;
                }
                if (itrB >= 0) {
                    sum += b.charAt(itrB) - '0';
                    itrB--;
                }
            }
            sum += carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
}
