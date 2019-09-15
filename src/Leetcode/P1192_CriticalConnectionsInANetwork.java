package Leetcode;

public class P1192_CriticalConnectionsInANetwork {

    // approach 1: Union find (TLE)
    // Tarjan's algorithm is the way to go, visit it later

    // The idea is kind of brute force but it's my initial approach to this
    // problem. Basically, for each connection in our connections list, we
    // check whether leaving out of this connection for the UF class, we will
    // have uf.size > 1 which means this connection is a critical connection

    class UF {
        int[] id, weight;
        int size;
        public UF(int n) {
            id = new int[n];
            weight = new int[n];
            size = n;
            for (int i = 0; i < n; i++) {
                id[i] = i;
                weight[i] = 1;
            }
        }
        public int find(int p) {
            while (id[p] != id[id[p]]) {
                id[p] = id[id[p]];
                p = id[p];
            }
            return id[p];
        }
        public void union(int p, int q) {
            int pp = find(p);
            int pq = find(q);
            if (weight[pp] < weight[pq]) {
                id[p] = pq;
                weight[pq] += weight[pp];
            }
            else {
                id[q] = pp;
                weight[pp] += weight[pq];
            }
            size--;
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < connections.size(); i++) {
            if (!isConnected(connections, n, i)) {
                int from = connections.get(i).get(0);
                int to = connections.get(i).get(1);
                res.add(new ArrayList(Arrays.asList(from, to)));
            }
        }
        return res;

    }
    public boolean isConnected(List<List<Integer>> connections, int n, int cut) {
        UF uf = new UF(n);
        for (int i = 0; i < connections.size(); i++) {
            if (i == cut) continue;
            List<Integer> connection = connections.get(i);
            int from = connection.get(0);
            int to = connection.get(1);
            int pFrom = uf.find(from);
            int pTo = uf.find(to);
            if (pFrom != pTo) {
                uf.union(pFrom, pTo);
            }
        }
        return uf.size == 1;
    }
}
