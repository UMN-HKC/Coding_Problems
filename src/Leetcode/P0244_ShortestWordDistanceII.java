package Leetcode;

import java.util.*;

public class P0244_ShortestWordDistanceII {


    /**
     * Your WordDistance object will be instantiated and called as such:
     * WordDistance obj = new WordDistance(words);
     * int param_1 = obj.shortest(word1,word2);
     */


    // the initial solution causes TLE
    class WordDistance_initial {
        Map<Set<String>, Integer> map;
        String[] words;
        public WordDistance_initial(String[] words) {
            map = new HashMap<>();
            this.words = words;
        }

        public int shortest(String word1, String word2) {
            Set<String> set = new HashSet<>();
            set.add(word1);
            set.add(word2);
            if (map.containsKey(set)) {
                return map.get(set);
            }
            else {
                int res = helper(words, word1, word2);
                Set<String> newSet = new HashSet<>();
                newSet.add(word1);
                newSet.add(word2);
                map.put(newSet, res);
                return res;
            }
        }
        public int helper(String[] words, String word1, String word2) {
            int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1))
                    p1 = i;

                if (words[i].equals(word2))
                    p2 = i;

                if (p1 != -1 && p2 != -1)
                    min = Math.min(min, Math.abs(p1 - p2));
            }

            return min;
        }
    }

    // this is the accepted solution from the discussion board which is O(n) for initialization and
    // O(n) for each call

    public class WordDistance_improved {

        private Map<String, List<Integer>> map;

        public WordDistance_improved(String[] words) {
            map = new HashMap<String, List<Integer>>();
            for(int i = 0; i < words.length; i++) {
                String w = words[i];
                if(map.containsKey(w)) {
                    map.get(w).add(i);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    map.put(w, list);
                }
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int ret = Integer.MAX_VALUE;
            for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
                int index1 = list1.get(i), index2 = list2.get(j);
                if(index1 < index2) {
                    ret = Math.min(ret, index2 - index1);
                    i++;
                } else {
                    ret = Math.min(ret, index1 - index2);
                    j++;
                }
            }
            return ret;
        }
    }

}
