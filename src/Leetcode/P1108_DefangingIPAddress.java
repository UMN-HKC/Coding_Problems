package Leetcode;

public class P1108_DefangingIPAddress {

    // brute force

    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        String[] strs = address.split("\\.");
        for (String str : strs) {
            sb.append(str).append("[.]");
        }
        sb.setLength(sb.length() - 3);
        return new String(sb);
    }
}
