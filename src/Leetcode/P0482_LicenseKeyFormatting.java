package Leetcode;

public class P0482_LicenseKeyFormatting {

    // approach 1:

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) != '-') {
                if (cnt != 0 && cnt % K == 0) {
                    sb.append("-");
                }
                sb.append(S.charAt(i));
                cnt++;
            }
        }
        return sb.reverse().toString().toUpperCase();
    }
}
