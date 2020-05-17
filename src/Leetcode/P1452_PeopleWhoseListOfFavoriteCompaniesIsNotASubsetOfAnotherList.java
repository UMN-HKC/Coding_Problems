package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1452_PeopleWhoseListOfFavoriteCompaniesIsNotASubsetOfAnotherList {

    // approach 1: brute force
    // For each list of words, check if it is a subset with all other list of words

    // time: O(n^3)

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> res = new ArrayList<>();
        List<Set<String>> lists = new ArrayList<>();
        for (int i = 0; i < favoriteCompanies.size(); i++) {
            lists.add(new HashSet(favoriteCompanies.get(i)));
        }
        for (int i = 0; i < lists.size(); i++) {
            boolean isSuperset = false;
            for (int j = 0; j < lists.size() && !isSuperset; j++) {
                if (i == j || lists.get(j).size() < lists.get(i).size()) continue;
                Set<String> set = lists.get(j);
                for (String s : lists.get(i)) {
                    if (!set.contains(s)) {
                        isSuperset = false;
                        break;
                    }
                    else {
                        isSuperset = true;
                    }
                }
                if (isSuperset) {
                    break;
                }
            }
            if (!isSuperset) {
                res.add(i);
            }
        }
        return res;
    }
}
