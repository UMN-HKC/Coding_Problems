package Leetcode;

public class P0028_strStr {
    public int strStr_initial(String haystack, String needle) {
        if (needle == null || needle == "") {
            return 0;
        }
        int itr = 0;
        while (itr <= haystack.length() - needle.length()) {
            int start = itr;
            int needleItr = 0;
            while (itr <= haystack.length() - needle.length() && needleItr < needle.length()
                    && haystack.charAt(start) == needle.charAt(needleItr)) {
                start++;
                needleItr++;
            }
            if (needleItr == needle.length()) {
                return itr;
            }
            itr++;
        }
        return -1;
    }

    public int strStr_concise(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    public int strStr_kmp(String haystack, String needle) {
        if(haystack == null || needle == null || needle.length() > haystack.length()) return -1;

        int[] parr = kmpPreprocess(needle);
        int i = 0, j = 0;
        while(i < haystack.length() && j < needle.length()) {
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
            } else if(j > 0) {
                j = parr[j - 1];
            } else {
                i++;
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    private int[] kmpPreprocess(String pattern) {
        int i = 1, j = 0;
        int[] res = new int[pattern.length()];
        while(i < pattern.length()) {
            if(pattern.charAt(i) == pattern.charAt(j)) {
                res[i] = j+1;
                i++; j++;
            } else if (j > 0) {
                j = res[j-1];
            } else {
                res[i] = 0;
                i++;
            }
        }
        return res;
    }
}

