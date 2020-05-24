package Leetcode;

public class P1456_MaximumNumberOfVowelsInASubstringOfGivenLength {

    // approach 1: sliding window

    // time: O(n)

    public int maxVowels(String s, int k) {

        String vowels = "aeiou";
        int max = 0;
        int vowelCount = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            if (vowels.indexOf(s.charAt(j)) != -1) {
                vowelCount++;
            }
            j++;
            if (j - i == k) {
                max = Math.max(vowelCount, max);
            }
            if (j >= k) {
                if (vowels.indexOf(s.charAt(i)) != -1) {
                    vowelCount--;
                }
                i++;

            }
        }
        return max;
    }
}
