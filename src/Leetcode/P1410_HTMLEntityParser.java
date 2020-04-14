package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1410_HTMLEntityParser {

    // approach 1:

    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "\'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (text.charAt(i) != '&') {
                sb.append(text.charAt(i));
                i++;
            }
            else {
                int index = text.indexOf(";", i);
                if (index == -1) {
                    sb.append(text.substring(i));
                    break;
                }
                String str = text.substring(i, index+1);
                if (map.containsKey(str)) {
                    sb.append(map.get(str));
                }
                else {
                    sb.append(str);
                }
                i = index + 1;
            }
        }
        return sb.toString();
    }
}
