package Leetcode;

public class MyIntegerStack {

        public static void main(String[] args) {
            MyIntegerStack stack = new MyIntegerStack();
            stack.push(3);
            stack.push(3);
            stack.push(3);
            System.out.println(stack.peek());
            System.out.println(stack.pop());

        }

        private int[] data;
        private int size;
        private int cnt;
        public MyIntegerStack() {
            data = new int[10];
            cnt = 0;
            size = 10;
        }
        public void push(int num) {
            if (cnt == size) {
                doubleSize();
            }
            cnt++;
            data[cnt] = num;
        }

        public int peek() {
            return data[cnt];
        }

        public int pop() {
            int res = data[cnt];
            cnt--;
            return res;
        }

        public boolean empty() {
            return size == 0;
        }

        public void doubleSize() {
            int[] doubledArray = new int[size * 2];
            for (int i = 0; i < size; i++) {
                doubledArray[i] = data[i];
            }
            size *= 2;
        }
}
