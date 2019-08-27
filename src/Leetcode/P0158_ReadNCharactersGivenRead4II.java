package Leetcode;

public class P0158_ReadNCharactersGivenRead4II {

    // approach 1:
    // use bufPointer and bufCounter to store information from previous read4
    // function call. So at each of current call, we will first check if we
    // have more to read from buffer before we call read4() to read more
    // characters from the file.
    // eg:
    // file: "abc"
    // n:      1
    // in this case, read4() will read all 3 characters into buffer, while we only
    // actually need 1 for this call, so bufCounter and bufPointer acts to record
    // these information so that when next read is called, we first read the rest
    // characters in the buffer which are from previous call.

    private int bufPointer = 0;
    private int bufCounter = 0;
    private char[] temp = new char[4];

    public int read(char[] buf, int n) {
        int p = 0;
        while (p < n) {
            // check if we have more to read from internal buffer
            if (bufCounter == 0) {
                bufCounter = read4(temp);
            }
            // end of file
            if (bufCounter == 0) break;
            // read from internal buffer until end of buffer
            // or end of required number of characters to read
            while (bufPointer < bufCounter && p < n) {
                buf[p++] = temp[bufPointer++];
            }
            // if all buffer's read, reset buffer pointer and buffer counter to 0
            if (bufPointer >= bufCounter) {
                bufCounter = 0;
                bufPointer = 0;
            }
        }
        return p;
    }
}
