package Leetcode;

public class P0165_CompareVersionNumber {

    // initial thought: brute force
    // 实现题

    public int compareVersion(String version1, String version2) {
        String[] vs1 = version1.split("\\.");
        String[] vs2 = version2.split("\\.");
        int itr1 = 0, itr2 = 0;
        while (itr1 < vs1.length || itr2 < vs2.length) {
            if (itr1 >= vs1.length) {
                if (Integer.valueOf(vs2[itr2]) != 0) {
                    return -1;
                }
                itr2++;
            }
            else if (itr2 >= vs2.length) {
                if (Integer.valueOf(vs1[itr1]) != 0) {
                    return 1;
                }
                itr1++;
            }
            else if (Integer.valueOf(vs1[itr1]) > Integer.valueOf(vs2[itr2])) {
                return 1;
            }
            else if (Integer.valueOf(vs1[itr1]) < Integer.valueOf(vs2[itr2])) {
                return -1;
            }
            else {
                itr1++;
                itr2++;
            }
        }
        return 0;
    }
}
