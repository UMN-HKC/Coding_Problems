package Leetcode;

import java.util.*;

public class P0399_EvaluateDivision {

    // approach 1: graph
    // The basic idea is to realize and abstract this problem as a directed graph with weight

    public double[] calcEquation_dfs(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<String>> verticeToNeighbors = new HashMap<>();
        Map<String, List<Double>> verticeToValues = new HashMap<>();

        // build graph
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String start = equation.get(0);
            String end = equation.get(1);

            if (!verticeToNeighbors.containsKey(start)) {
                verticeToNeighbors.put(start, new ArrayList<>());
                verticeToValues.put(start, new ArrayList<>());
            }
            if (!verticeToNeighbors.containsKey(end)) {
                verticeToNeighbors.put(end, new ArrayList<>());
                verticeToValues.put(end, new ArrayList<>());
            }
            verticeToValues.get(start).add(values[i]);
            verticeToValues.get(end).add(1.0 / values[i]);
            verticeToNeighbors.get(start).add(end);
            verticeToNeighbors.get(end).add(start);
        }

        // dfs
        double[] result = new double[queries.size()];
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < queries.size(); i++) {
            result[i] = dfs(queries.get(i), verticeToNeighbors, verticeToValues, visited, 1.0);
            if (result[i] == 0.0) {
                result[i] = -1.0;
            }
        }

        return result;
    }
    public double dfs(List<String> query, Map<String, List<String>> verticeToNeighbors, Map<String, List<Double>> verticeToValues, Set<String> visited, double res) {
        if (query == null || query.size() < 2 || !verticeToNeighbors.containsKey(query.get(0))
                || !verticeToNeighbors.containsKey(query.get(1))) {
            return 0.0;
        }
        String start = query.get(0);
        String end = query.get(1);
        if (start.equals(end)) return res;

        visited.add(start);
        double temp = 0.0;
        for (int i = 0; i < verticeToNeighbors.get(start).size(); i++) {
            String neighbor = verticeToNeighbors.get(start).get(i);
            if (!visited.contains(neighbor)) {
                List<String> newQuery = new ArrayList<>();
                newQuery.add(neighbor);
                newQuery.add(end);
                temp = dfs(newQuery, verticeToNeighbors, verticeToValues, visited, res * (verticeToValues.get(start).get(i)));
                if (temp != 0.0) {
                    break;
                }
            }
        }
        visited.remove(start);
        return temp;
    }
}
