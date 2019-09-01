package Leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class P0353_DesignSnakeGame {


    // approach 1: deque + set

    // use deque to store snake and set to record occupied path
    // note that, only when encountering food will we add new head to
    // our deque. But for each move, we need to update tail and head
    // position as well as updating occupied path for tail and head

    int w;
    int h;
    int foodCnt;
    int[][] food;
    Deque<int[]> snake;
    Set<String> snakePath;
    public SnakeGame(int width, int height, int[][] food) {
        w = width;
        h = height;
        foodCnt = 0;
        this.food = food;
        snake = new LinkedList<>();
        snakePath = new HashSet<>();
        snake.add(new int[]{0,0,1});
        snakePath.add(new String("0:0"));
    }

    public int move(String direction) {
        int[] head = snake.peekLast();
        int r = head[0] + (direction.equals("U") ? -1 : (direction.equals("D") ? 1 : 0));
        int c = head[1] + (direction.equals("L") ? -1 : (direction.equals("R") ? 1 : 0));

        // check if within board or collide with itself
        if (r < 0 || c < 0 || r >= h || c >= w) return -1;
        if (snakePath.contains(r + ":" + c) && !(r == snake.peekFirst()[0] && c == snake.peekFirst()[1])) return -1;

        // encounter food, we need to add this position as new head and body remains same
        if (foodCnt < food.length && food[foodCnt][0] == r && food[foodCnt][1] == c) {
            foodCnt++;
            int[] newHead = new int[]{r, c, head[2] + 1};
            snake.addLast(newHead);
            snakePath.add(r + ":" + c);
        }
        // update head and tail
        else {
            int[] tail = snake.removeFirst();
            snakePath.remove(tail[0] + ":" + tail[1]);
            int[] newHead = new int[]{r, c, head[2]};
            snake.addLast(newHead);
            String np = r + ":" + c;
            snakePath.add(np);
        }
        return snake.peekLast()[2] - 1;
    }
}
