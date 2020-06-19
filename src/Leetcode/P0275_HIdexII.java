package Leetcode;

public class P0275_HIdexII {

    // approach 1: binary search
    // There are 2 cases: th anser is in the array or the answer is not in the array
    // https://www.youtube.com/watch?v=CjKJDloMnwE
String
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int start = 0;
        int end = citations.length - 1;
//        '<=' ensures that we've checked each element including array with length of 1
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // it means we have at least citations[mid] papers that have citations[mid] citations
            // which satisfies the condition exactly and since the array is already sorted and this
            // is the best h-index we could get. Why don't we search further to the right to find an
            // even better h-index? Well, think about if you move right, the index level can be higher
            // however the number of papers will decrease.
            if (citations[mid] == citations.length - mid) {
                return citations.length - mid;
            }
            // this means we haven't found an h-index, so we need to search to the left side
            else if (citations[mid] > citations.length - mid) {
                end = mid - 1;
            }
            // this case means that we need to search for a higher h-index which is on the right side
            else {
                start = mid + 1;
            }
        }
        return citations.length - start;
    }
}
