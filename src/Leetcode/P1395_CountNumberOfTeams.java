package Leetcode;

public class P1395_CountNumberOfTeams {

    // approach 1:
    /*
    * The basic idea is that we will use two arrays called smaller and greater to represent
    * smaller[i] elements are smaller than rating[i] on its left, and greater[i] elements
    * are greater than rating[i] on its right
    *
    * */

    // time: O(n^2)
    // space: O(n)

    public int numTeams_1(int[] rating) {
        int n = rating.length;
        int[] smaller = new int[n];
        int[] greater = new int[n];
        int tot = 0;
        for (int i = 1; i < n; i++) {
            int cur = rating[i];
            for (int j = 0; j < i; j++) {
                if (cur > rating[j]) {
                    if (smaller[j] >= 1) {
                        tot += smaller[j];
                    }
                    smaller[i]++;
                }
                else if (cur < rating[j]){
                    if (greater[j] >= 1) {
                        tot += greater[j];
                    }
                    greater[i]++;
                }
            }
        }
        return tot;
    }

    // approach 2:
    /*
    * For each solder, count how many solders on the left and right have less and greater ratings.
    * This solder can form less[left] * greater[right] + greater[left] * less[right] teams.
    * */

    // time: O(n^2)
    // space: O(1)

    public int numTeams(int[] rating) {
        int n = rating.length;
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            int leftSmaller = 0, rightGreater = 0;
            int leftGreater = 0, rightSmaller = 0;
            for (int j = 0; j < n; j++) {
                if (rating[j] < rating[i]) {
                    if (i > j) {
                        leftSmaller++;
                    }
                    else {
                        rightSmaller++;
                    }
                }
                if (rating[j] > rating[i]) {
                    if (i > j) {
                        leftGreater++;
                    }
                    else {
                        rightGreater++;
                    }
                }
            }
            res += leftSmaller * rightGreater + leftGreater * rightSmaller;
        }
        return res;
    }
}
