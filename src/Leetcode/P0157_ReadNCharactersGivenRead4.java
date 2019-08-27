package Leetcode;

public class P0157_ReadNCharactersGivenRead4 {

    // approach 1:
    // buf is the destination that we want to put characters into
    // temp is our internal buffer that we use to store characters
    // read from read4() function call. And then, we will transfer
    // characters in temp buffer to destination buffer.

    public int read(char[] buf, int n) {
        int p = 0;
        char[] temp = new char[4];
        while (p < n) {
            int cnt = read4(temp);
            int i = 0;
            // transfer characters to destination buffer
            while (i < cnt && p < n) {
                buf[p++] = temp[i++];
            }
            // when cnt < 4, indicating eof
            if (cnt < 4) break;
        }
        return p >= n ? n : p;
    }
}
