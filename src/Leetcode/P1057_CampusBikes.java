package Leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class P1057_CampusBikes {

    // approach 1: PQ + greedy
    // The basic idea is to match each pair of bike and worker and put them into
    // a priority queue in an order that's required by the problem. Then, pop
    // pairs from pq until all workers have been paired with a bike

    // time: O((m*n)*log(m*n))

    class Pair {
        int bikeIdx;
        int workerIdx;
        int dis;
        public Pair(int bikeIdx, int workerIdx, int dis) {
            this.bikeIdx = bikeIdx;
            this.workerIdx = workerIdx;
            this.dis = dis;
        }
    }
    class myComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            if (a.dis == b.dis) {
                if (a.workerIdx == b.workerIdx) {
                    return a.bikeIdx- b.bikeIdx;
                }
                else {
                    return a.workerIdx - b.workerIdx;
                }
            }
            else {
                return a.dis - b.dis;
            }
        }
    }
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0) return new int[0];
        int workerLen = workers.length;
        int bikeLen = bikes.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new myComparator());
        boolean[] usedBikes = new boolean[bikeLen];
        boolean[] satisfiedWorkers = new boolean[workerLen];
        int[] res = new int[workerLen];
        for (int i = 0; i < workerLen; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < bikeLen; j++) {
                int[] bike = bikes[j];
                int dis = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                Pair pair = new Pair(j, i, dis);
                pq.offer(pair);
            }
        }
        int cnt = 0;
        while (!pq.isEmpty() && cnt < workerLen) {
            Pair p = pq.poll();
            if (satisfiedWorkers[p.workerIdx] || usedBikes[p.bikeIdx]) continue;
            res[p.workerIdx] = p.bikeIdx;
            usedBikes[p.bikeIdx] = true;
            satisfiedWorkers[p.workerIdx] = true;
            cnt++;
        }
        return res;
    }

    // approach 2: bucket sort

    // Because the distance is not very large, we can use the maximum distance which is 2000
    // as our bucket size and put all bike index and worker index pair into their respective
    // distance bucket. Note that it is important we traverse workers and bikes array from
    // lower index to higher index so that the list put into bucket is ordered in terms of
    // worker index and bike index

    // time: O(m * n)

    public int[] assignBikes_2(int[][] workers, int[][] bikes) {
        if (workers == null || workers.length == 0) return new int[0];
        int workerLen = workers.length;
        int bikeLen = bikes.length;
        int[] res = new int[workerLen];
        List<int[]>[] bucket = new ArrayList[2000];
        for (int i = 0; i < workerLen; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < bikeLen; j++) {
                int[] bike = bikes[j];
                int dis = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                int[] pair = new int[]{i, j};
                if (bucket[dis] == null) {
                    bucket[dis] = new ArrayList<>();
                }
                bucket[dis].add(pair);
            }
        }
        int cnt = 0;
        boolean[] satisfiedWorkers = new boolean[workerLen];
        boolean[] usedBikes = new boolean[bikeLen];
        for (int i = 0; i < 2000 && cnt < workerLen; i++) {
            if (bucket[i] != null) {
                List<int[]> list = bucket[i];
                int j = 0;
                while (cnt < workerLen && j < list.size()) {
                    int[] pair = list.get(j);
                    if (!satisfiedWorkers[pair[0]] && !usedBikes[pair[1]]) {
                        res[pair[0]] = pair[1];
                        usedBikes[pair[1]] = true;
                        satisfiedWorkers[pair[0]] = true;
                        cnt++;
                    }
                    j++;
                }
            }
        }
        return res;
    }
}
