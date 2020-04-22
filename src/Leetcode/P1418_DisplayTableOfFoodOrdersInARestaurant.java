package Leetcode;

import java.util.*;

public class P1418_DisplayTableOfFoodOrdersInARestaurant {

    // approach 1: hashmap + set + list + heap

    public List<List<String>> displayTable(List<List<String>> orders) {
        // store unique dishes
        Set<String> menus = new HashSet<>();
        // store dishes in lexicographic order
        LinkedList<String> orderedMenu = new LinkedList<>();
        // map table no to all its dishes
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        // build up the map
        for (int i = 0; i < orders.size(); i++) {
            List<String> order = orders.get(i);
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            if (!menus.contains(food)) {
                menus.add(food);
                orderedMenu.add(food);
            }
            map.putIfAbsent(table, new HashMap<>());
            Map<String, Integer> curTableMap = map.get(table);
            curTableMap.put(food, curTableMap.getOrDefault(food, 0) + 1);
        }

        List<List<String>> res = new ArrayList<>();
        // construct the first row
        Collections.sort(orderedMenu);
        orderedMenu.addFirst("Table");
        res.add(orderedMenu);

        // use heap to sort tables in increasing order in terms of table number
        PriorityQueue<Map.Entry<Integer, Map<String, Integer>>> pq =
                new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        for (Map.Entry<Integer, Map<String, Integer>> entry : map.entrySet()) {
            pq.offer(entry);
        }

        while (!pq.isEmpty()) {
            Map.Entry<Integer, Map<String, Integer>> entry = pq.poll();
            Map<String, Integer> curTableFood = entry.getValue();
            int table = entry.getKey();
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(table));
            for (int i = 1; i < orderedMenu.size(); i++) {
                // if we have this dish for this table, add frequency to this column
                if (curTableFood.containsKey(orderedMenu.get(i))) {
                    list.add(String.valueOf(curTableFood.get(orderedMenu.get(i))));
                }
                // otherwise, add 0
                else {
                    list.add("0");
                }
            }
            // don't forget to add this row to result list
            res.add(list);
        }
        return res;
    }
}
