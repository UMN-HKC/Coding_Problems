package Leetcode;

import java.util.*

public class P0359_Logger {

    // approach 1: map
    // basically, this problem is a trade-off between time or space
    // map solution is fast in time but may end up storing too many
    // outdated messages

    // time: O(1)
    // space: O(n)

    class Logger {
        private Map<String, Integer> map;
        public Logger() {
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {
            if (!map.containsKey(message)) {
                map.put(message, timestamp);
                return true;
            }
            else {
                if (Math.abs(map.get(message) - timestamp) < 10) {
                    return false;
                }
                else {
                    map.put(message, timestamp);
                    return true;
                }
            }
        }
    }

    // approach 2: queue (more space efficient)
    // only keep a list of messages that are within the window size which is 10 in this case

    // time: O(n)
    // space: O(1)

    class Message {
        int time;
        String message;
        public Message(int t, String m) {
            time= t;
            message = m;
        }
    }

    Queue<Message> q;
    public static int TIME_WINDOW = 10;
    public Logger() {
        q = new LinkedList<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!q.isEmpty() && timestamp - q.peek().time >= TIME_WINDOW) {
            q.poll();
        }
        Iterator<Message> itr = q.iterator();
        while (itr.hasNext()) {
            Message m = itr.next();
            if (m.message.equals(message)) {
                return false;
            }
        }
        q.offer(new Message(timestamp, message));
        return true;
    }
}
