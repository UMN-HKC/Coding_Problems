package Leetcode;

public class P0273_IntegerToEnglishWords {

    // the key idea is to:
    // - first, list out all english words for needed numbers
    // - second, divide number by 1000 in each iteration and get (num % 1000)'s respective english representation
    // Some things to pay extra attention:
    // - try to be consistent in terms of the location where we add empty space, in here we always add to the end
    // - always add an empty space to separate potential words, and do trimming in the end

    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",   "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String res = "";
        int thousandCnt = 0;
        while (num != 0) {
            // this is to ensure that cases lik "120000120" only gets correct THOUSANDS
            if (num % 1000 != 0) {
                res = numLessThanThousand(num % 1000) + THOUSANDS[thousandCnt] + " " + res;
            }
            num /= 1000;
            // while we still increment thousandCnt each step
            thousandCnt++;
        }
        // trim any trailing empty space
        return res.trim();
    }
    public String numLessThanThousand(int num) {
        String res = "";
        if (num == 0) return "";
        if (num >= 100) {
            res = LESS_THAN_20[num / 100] + " Hundred " +  numLessThanThousand(num % 100);
        }
        else if (num >= 20) {
            res = TENS[num / 10] + " " + numLessThanThousand(num % 10);
        }
        else {
            res = LESS_THAN_20[num] + " ";
        }
        return res;
    }
}
