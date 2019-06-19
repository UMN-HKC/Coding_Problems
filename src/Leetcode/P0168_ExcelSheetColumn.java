package Leetcode;

public class P0168_ExcelSheetColumn {

    // idea borrowed from discussion board, was stuck at how to deal with 26
    // although easy question but still kind of bad at dealing with this situation

    public String convertToTitle(int n) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            // decrement n first to make '%' work on 26 and then '/' by 26 work correctly
            n--;
            sb.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        return sb.toString();
    }
}
