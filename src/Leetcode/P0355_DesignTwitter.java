package Leetcode;

import java.util.*;

public class P0355_DesignTwitter {


    // approach 1: heap
    // OOD: create User and Tweet classes. Each user will have id, its followed
    // other users and its tweet list(where tweet is node with next pointer)
    // Each tweet is basically a node with id, time, and pointer to next tweet

    // The key for getNewsFeed() is to pull this user's followed other users and put
    // heads of their tweets into max heap. Then, it's basically merge k sorted lists
    // problem. Note that, we need to make sure we do not offer null tweet into
    // our priority queue

    public static int timeStamp = 0;
    /** Initialize your data structure here. */
    class User {
        int id;
        Set<Integer> followed;
        Tweet tweetHead;
        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            followed.add(id);
        }
    }
    class Tweet {
        int time;
        int id;
        Tweet next;
        public Tweet(int id) {
            time = timeStamp++;
            this.id = id;
        }
    }
    Map<Integer, User> userMap;
    public Twitter() {
        userMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User usr = userMap.get(userId);
        Tweet t = new Tweet(tweetId);
        t.next = usr.tweetHead;
        usr.tweetHead = t;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;
        User curUsr = userMap.get(userId);
        Set<Integer> followed = curUsr.followed;
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> b.time - a.time);
        for (Integer id : followed) {
            User u = userMap.get(id);
            if (u != null && u.tweetHead != null) {
                pq.offer(u.tweetHead);
            }
        }
        int n = 10;
        for (int i = 0; i < n && pq.size() > 0; i++) {
            Tweet recent = pq.poll();
            res.add(recent.id);
            recent = recent.next;
            if (recent != null) {
                pq.offer(recent);
            }
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (followerId == followeeId) {
            return;
        }
        userMap.get(followerId).followed.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (followerId == followeeId) {
            return;
        }
        userMap.get(followerId).followed.remove(followeeId);
    }
}
