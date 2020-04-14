package Leetcode;

import java.util.PriorityQueue;

public class P1046_LastStoneWeight {

    // approach 1: heap

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() > 1) {
            int y = pq.poll();
            int x = pq.poll();
            if (y != x) {
                pq.offer(y - x);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }


    // approach 2: bucket sort

    public int lastStoneWeight_2(int[] stones) {
        int maxWeight = 0;
        for (int stone : stones) {
            maxWeight = Math.max(maxWeight, stone);
        }
        int[] bucket = new int[maxWeight + 1];
        for (int stone : stones) {
            bucket[stone]++;
        }
        int heaviestIndex = maxWeight;
        int secondHeaviestIndex = heaviestIndex - 1;
        while (heaviestIndex >= 1) {
            // skip no weight
            while (heaviestIndex >= 1 && bucket[heaviestIndex] == 0) {
                heaviestIndex--;
            }
            // if heaviestIndex exist
            if (heaviestIndex >= 1) {
                secondHeaviestIndex = bucket[heaviestIndex] > 1 ? heaviestIndex : heaviestIndex - 1;
                while (secondHeaviestIndex >= 1 && bucket[secondHeaviestIndex] == 0) {
                    secondHeaviestIndex--;
                }
                // if two heaviest stones found, smash them
                if (secondHeaviestIndex >= 1) {
                    bucket[heaviestIndex]--;
                    bucket[secondHeaviestIndex]--;
                    if (heaviestIndex != secondHeaviestIndex) {
                        bucket[heaviestIndex - secondHeaviestIndex]++;
                    }
                }
                else {
                    return heaviestIndex;
                }
            }
        }
        return 0;
    }
}
