package Leetcode;

public class P1093_StatisticsFromALargeSample {

    // initially stuck at getting median
    // idea borrowed from discussion board

    // approach 1: two passes to find median

    public double[] sampleStats_two_pass(int[] count) {

        double[] res = new double[5];
        // use for get min
        boolean first = false;
        // use for get mode
        int mostFreq = count[0];
        double mode = 0;
        // use for get max
        int prev = 0;
        // use for mean and median
        double cnt = 0;
        double sum = 0;

        for (int i = 0; i < count.length; i++) {
            // find min
            if (!first && count[i] != 0) {
                res[0] = (double)i;
                first = true;
            }
            // get mode;
            if (count[i] != 0 && count[i] > mostFreq) {
                mode = i;
                mostFreq = count[i];
            }
            // find max
            if (count[i] != 0 && i > prev) {
                res[1] = i;
            }
            // accumulate number of numbers;
            if (count[i] != 0) {
                cnt += count[i];
                sum += count[i] * i;
            }
        }
        // get mode
        res[4] = mode;
        // get mean
        res[2] = sum / cnt;
        // get median
        boolean odd = cnt % 2 == 1;
        double tempSum = 0;
        double median1 = 0;
        double median2 = 0;
        for (int i = 0; i < count.length; i++) {
            tempSum += count[i];
            if (count[i] > 0 && tempSum >= cnt / 2) {
                median1 = i;
                break;
            }
        }
        tempSum = 0;
        for (int i = 0; i < count.length; i++) {
            tempSum += count[i];
            if (count[i] > 0 && tempSum >= (cnt+1) / 2) {
                median2 = i;
                break;
            }
        }
        res[3] = odd ? median2 : (median1 + median2) / 2;
        return res;
    }

    // approach 2: 1 pass to find median

    public double[] sampleStats_one_pass(int[] count) {

        double[] res = new double[5];
        // use for get min
        boolean first = false;
        // use for get mode
        int mostFreq = count[0];
        double mode = 0;
        // use for get max
        int prev = 0;
        // use for mean and median
        double cnt = 0;
        double sum = 0;

        for (int i = 0; i < count.length; i++) {
            // find min
            if (!first && count[i] != 0) {
                res[0] = (double)i;
                first = true;
            }
            // get mode;
            if (count[i] != 0 && count[i] > mostFreq) {
                mode = i;
                mostFreq = count[i];
            }
            // find max
            if (count[i] != 0 && i > prev) {
                res[1] = i;
            }
            // accumulate number of numbers;
            if (count[i] != 0) {
                cnt += count[i];
                sum += count[i] * i;
            }
        }
        // get mode
        res[4] = mode;
        // get mean
        res[2] = sum / cnt;
        // get median
        boolean odd = cnt % 2 == 1;
        double tempSum = 0;
        double median = -1;
        for (int i = 0; i < count.length; i++) {
            tempSum += count[i];
            if (odd) {
                if (tempSum > cnt / 2) {
                    median = i;
                    break;
                }
            }
            else {
                // have found the first middle element
                // count[i] > 0 here is to avoid counting 'zero' freq elements
                // it will fail for Eg: [0,1,3,0,4,0,....0,0]. Because we have a zero
                // at index '3', 'tempSum' will remain same for number '3' as well and
                // median will become '3' now, though its frequency is zero.
                if (count[i] > 0 && tempSum == cnt / 2) {
                    median = i;
                }

                if (tempSum > cnt / 2) {
                    // if after adding count[i], we have already passed the first middle number and covered the
                    // second middle number, and we still haven't set median previously, then two median numbers
                    // are both i.
                    if (median == -1) {
                        median = i;
                    }
                    // otherwise, we add the second middle number and divide to get median
                    else {
                        median = (median + i) / 2;
                    }
                    // remember to break the loop since at thius point we have found median
                    break;
                }
            }
        }
        res[3] = median;
        return res;
    }
}
