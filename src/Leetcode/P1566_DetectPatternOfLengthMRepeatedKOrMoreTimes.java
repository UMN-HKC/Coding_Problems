package Leetcode;

public class P1566_DetectPatternOfLengthMRepeatedKOrMoreTimes {

    // approach 1: brute force

    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        for (int i = 0; i <= n - k * m; i++) {
            // get pattern
            StringBuilder pattern = new StringBuilder();
            for (int j = i; j < i + m; j++) {
                pattern.append(arr[j]);
                if (j != i + m - 1) {
                    pattern.append(",");
                }
            }
            String patternStr = pattern.toString();
            // check consecutive strings
            int cnt = 1;
            for (int j = i + m; j < i + m * k; j += m) {
                StringBuilder sb = new StringBuilder();
                for (int l = j; l < j + m; l++) {
                    sb.append(arr[l]);
                    if (l != j + m - 1) {
                        sb.append(",");
                    }
                }
                if (!sb.toString().equals(patternStr)) {
                    break;
                }
                cnt++;
            }
            if (cnt == k) {
                return true;
            }
        }
        return false;
    }

    // approach 2: O(n) time O(1) space

    public boolean containsPattern_2(int[] arr, int m, int k)
    {
        if (arr.length < m * k)
        {
            return false;
        }
        int count = 0;
        for (int i = 0; i < arr.length - m; i++)
        {
            count = arr[i] == arr[i + m] ? count + 1 : 0;
            if (count == m * (k - 1))
            {
                return true;
            }
        }
        return false;
    }
}
