package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1396_DesignUndergroundSystem {

    // approach 1:
    /*
    * routes: checkin_place -> {(checkout_place_1, [total_checkin_time, total_checkout_time, count]), ...}
    * checkinLocationMap: id -> checkin_place
    * checkinTimeMap: id -> checkin_time
    * */

    Map<String, Map<String, int[]>> routes;
    Map<Integer, String> checkinLocationMap;
    Map<Integer, Integer> checkinTimeMap;

    public UndergroundSystem() {
        routes = new HashMap<>();
        checkinLocationMap = new HashMap<>();
        checkinTimeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        if (!routes.containsKey(stationName)) {
            routes.put(stationName, new HashMap<>());
        }
        // record checkin time for this id
        checkinTimeMap.put(id, t);
        // put checkin location for this id into checkinMap
        checkinLocationMap.put(id, stationName);
    }

    public void checkOut(int id, String stationName, int t) {
        // update routes to add total checkin
        // and total checkout time and counter
        String checkinLocation = checkinLocationMap.get(id);
        int checkinTime = checkinTimeMap.get(id);
        if (!routes.containsKey(checkinLocation)) {
            routes.put(checkinLocation, new HashMap<>());
            routes.get(checkinLocation).put(stationName, new int[]{checkinTime, t, 1});
        }
        else {
            if (!routes.get(checkinLocation).containsKey(stationName)) {
                routes.get(checkinLocation).put(stationName, new int[]{checkinTime, t, 1});
            }
            else {
                int[] record = routes.get(checkinLocation).get(stationName);
                record[0] += checkinTime;
                record[1] += t;
                record[2]++;
            }
        }
        // remove info from two checkin maps
        checkinTimeMap.remove(id);
        checkinLocationMap.remove(stationName);
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] record = routes.get(startStation).get(endStation);
        return (double)(record[1] - record[0]) / record[2];
    }

    // approach 2: using Pair in java 8
    // cleaner

    HashMap<String, Pair<Integer, Integer>> checkoutMap = new HashMap<>(); // Route - {TotalTime, Count}
    HashMap<Integer, Pair<String, Integer>> checkInMap = new HashMap<>();  // Uid - {StationName, Time}

    public UndergroundSystem() {}

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> checkIn = checkInMap.get(id);
        String route = checkIn.getKey() + "_" + stationName;
        int totalTime = t - checkIn.getValue();
        Pair<Integer, Integer> checkout = checkoutMap.getOrDefault(route, new Pair<>(0, 0));
        checkoutMap.put(route, new Pair<>(checkout.getKey() + totalTime, checkout.getValue() + 1));
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "_" + endStation;
        Pair<Integer, Integer> checkout = checkoutMap.get(route);
        return (double) checkout.getKey() / checkout.getValue();
    }



    /**
     * Your UndergroundSystem object will be instantiated and called as such:
     * UndergroundSystem obj = new UndergroundSystem();
     * obj.checkIn(id,stationName,t);
     * obj.checkOut(id,stationName,t);
     * double param_3 = obj.getAverageTime(startStation,endStation);
     */
}
