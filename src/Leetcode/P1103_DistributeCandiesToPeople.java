package Leetcode;

public class P1103_DistributeCandiesToPeople {

    // initial approach: brute force

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int give = 1;
        int atPos = 0;
        while (candies != 0) {
            if (candies >= give) {
                res[atPos++] += give;
                candies -= give;
                give++;
                atPos %= num_people;
                if (candies == 0) {
                    return res;
                }
            }
            else if (candies < give) {
                res[atPos] += candies;
                return res;
            }
        }
        return res;
    }

    // more concise
    public int[] distributeCandies_concise(int candies, int num_people) {
        int p = 0, give = 1;
        int[] res = new int[num_people];
        while (candies != 0) {
            res[p % num_people] += Math.min(candies, give);
            candies -= Math.min(candies, give);
            give++;
            p++;
        }
        return res;
    }
}
