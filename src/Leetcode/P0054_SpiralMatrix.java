package Leetcode;
import java.util.*;

public class P0054_SpiralMatrix {

    // approach 1: brute force

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length - 1, n = matrix[0].length - 1;
        int ls = 0, rs = 0, brs = n, bls = m;
        while (ls <= brs && rs <= bls) {
            int tempLs = ls;
            while (tempLs <= brs) {
                res.add(matrix[rs][tempLs]);
                tempLs++;
            }
            rs++;

            int tempRs = rs;
            while (tempRs <= bls) {
                res.add(matrix[tempRs][brs]);
                tempRs++;
            }
            brs--;

            if (rs <= bls) {
                int tempBrs = brs;
                while (ls <= tempBrs) {
                    res.add(matrix[bls][tempBrs]);
                    tempBrs--;
                }
                bls--;
            }

            if (ls <= brs) {
                int tempBls = bls;
                while (rs <= tempBls) {
                    res.add(matrix[tempBls][ls]);
                    tempBls--;
                }
                ls++;
            }

        }
        return res;
    }
}
