package Leetcode;

public class P0393_UTF {

    // cannot work it out initially
    // the idea is borrowed from discussion board
    // basically, we check one byte at a time, and determine this character's number of bytes
    // and then traverse the array to check its following n-1 bytes
    // and the above checking is done iteratively until we've explored the whole array

    // note that to determine the number of bytes, for example 110XXXXX(greater than 192),
    // we will need to & the data with 11100000(the MS 2 bits are to check for two 1 bits, and the
    // 3rd MSB is used to check 0 following according to UTF rule)

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        int i = 0;
        while (i < data.length) {
            int numberOfBytes;
            if ((data[i] & 128) == 0) {
                numberOfBytes = 1;
            }
            else if ((data[i] & 224) == 192) {
                numberOfBytes = 2;
            }
            else if ((data[i] & 240) == 224) {
                numberOfBytes = 3;
            }
            else if ((data[i] & 248) == 240) {
                numberOfBytes = 4;
            }
            else {
                return false;
            }
            for (int j = 1; j < numberOfBytes; j++) {
                if (i + j >= data.length) {
                    return false;
                }
                if ((data[i+j] & 192) != 128) {
                    return false;
                }
            }
            i += numberOfBytes;
        }
        return true;
    }
}
