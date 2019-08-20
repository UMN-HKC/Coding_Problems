package Leetcode;
import java.util.ArrayList;
import java.util.List;



public class P0068_TextJustification {

    // approach 1: just implement it but with helper function to make code cleaner

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            List<String> curLine = new ArrayList<>();
            int num = 0, curLen = 0;
            // start building current string line
            while (i < words.length && (curLen + words[i].length() + num <= maxWidth)) {
                curLen += words[i].length();
                curLine.add(words[i]);
                num++;
                i++;
            }
            // left justify (including line with only 1 word or the last line)
            if (num == 1 || i == words.length) {
                res.add(leftJustifyBuild(curLine, maxWidth));
            }
            // regular build with more space on the left side
            else {
                res.add(regularBuild(maxWidth - curLen, num, curLine));
            }
        }
        return res;
    }
    public String leftJustifyBuild(List<String> curLine, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int rest = maxWidth;
        for (int j = 0; j < curLine.size(); j++) {
            String str = curLine.get(j);
            rest -= str.length();
            sb.append(str);
            if (rest != 0) {
                sb.append(" ");
                rest--;
            }
        }
        // append rest of spaces
        while (rest != 0) {
            sb.append(" ");
            rest--;
        }
        return new String(sb);
    }
    public String regularBuild(int space, int num, List<String> curLine) {
        StringBuilder sb = new StringBuilder();
        // extra spaces to
        int extra = space % (num - 1);
        int each = space / (num - 1);
        for (int j = 0; j < curLine.size(); j++) {
            String str = curLine.get(j);
            sb.append(str);
            if (j == curLine.size() - 1) continue;
            // first place even spaces evenly
            for (int k = 1; k <= each; k++) {
                sb.append(" ");
            }
            // place 1 space from extra space one at a time
            if (extra > 0) {
                sb.append(" ");
                extra--;
            }
        }
        return new String(sb);
    }
}
