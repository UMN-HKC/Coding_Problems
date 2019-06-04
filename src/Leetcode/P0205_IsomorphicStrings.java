package Leetcode;

public class P0205_IsomorphicStrings {

    // take me quite some time and couldn't figure out initially
    // idea borrowed from discussion board
    // Basically, we need to check if the last seen indices of the currently visiting character in
    // both strings are equal. This ensures that their pattern is same.

    // time: O(n)
    // space: O(1) constant space

    public boolean isIsomorphic(String s, String t) {
        int[] indices = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (indices[s.charAt(i)] != indices[t.charAt(i) + 128]) {
                return false;
            }
            indices[s.charAt(i)] = indices[t.charAt(i) + 128] = i + 1;
        }
        return true;
    }
}
