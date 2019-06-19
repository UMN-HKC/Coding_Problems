package Leetcode;
import java.util.*;

public class P0271_EncodeAndDecodeStrings {

    // Idea borrowed from discussion board:
    // the difficult part of this problem is to know how to avoid comparing characters
    // instead, if we know the size of current word, we simply cover them and escape to the
    // start of the next word. In this way, we don't have to deal with specific characters

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String s : strs) {
            sb.append(s.length()).append("/").append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        while(i < s.length()) {
            int slash = s.indexOf("/", i);
            int size = Integer.valueOf(s.substring(i, slash));
            i = slash + size + 1;
            ret.add(s.substring(slash + 1, i));
        }
        return ret;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));