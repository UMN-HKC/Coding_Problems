package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class P0535_EncodeAndDecodeTinyURL {

    // approach 1: counter
    // Downsides:
    // If I'm asked to encode the same long URL several times, it will get several entries. That wastes codes and memory.
    // People can find out how many URLs have already been encoded. Not sure I want them to know.
    // People might try to get special numbers by spamming me with repeated requests shortly before their desired number comes up.
    // Only using digits means the codes can grow unnecessarily large. Only offers a million codes with length 6 (or smaller). Using six digits or lower or upper case letters would offer (10+26*2)6 = 56,800,235,584 codes with length 6.

    Map<Integer, String> map = new HashMap<>();
    int i=0;
    public String encode(String longUrl) {
        map.put(i,longUrl);
        return "http://tinyurl.com/"+i++;
    }
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }

    // approach 2:
    // The basic idea is to randomly generate a short sequence of characters and map
    // the long url to this short url. If this generated short url is previously generated
    // keep generating until a unique one appears.

    Map<String, String> shortToLong = new HashMap<>();
    Map<String, String> longToShort = new HashMap<>();
    Random rd = new Random();
    public static String baseHost = "http://tinyurl.com/";
    public static String charSet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longToShort.containsKey(longUrl)) return longToShort.get(longUrl);
        String shortUrl = "";
        do {
            for (int i = 0; i < 6; i++) {
                int idx = rd.nextInt(charSet.length());
                shortUrl += charSet.charAt(idx);
            }
        } while (shortToLong.containsKey(shortUrl));
        shortUrl = baseHost + shortUrl;
        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }
}
