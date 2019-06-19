package Leetcode;

public class P0013_RomanToInteger {

    // idea borrowed from discussion board
    // the key is to know the roman symbol combination rules: 3000 is expressed as MMM not IIIM
    // and another point is that to detect I, X and C as subtraction, we iterate from right to left,
    // add whatever to the result first, but when encountering I, X and C, we check if the previous
    // result value indicates subtraction

    public int romanToInt(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch(c) {
                case 'I':
                    res += res >= 5 ? -1 : 1;
                    break;
                case 'X':
                    res += res >= 50 ? -10 : 10;
                    break;
                case 'C':
                    res += res >= 500 ? -100 : 100;
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'D':
                    res += 500;
                    break;
                default:
                    res += 1000;
                    break;
            }
        }
        return res;
    }
}
