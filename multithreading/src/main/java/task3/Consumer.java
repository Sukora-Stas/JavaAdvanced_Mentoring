package task3;

import java.util.Queue;

public class Consumer {

    private final Queue<String> queue;

    public Consumer(Queue<String> queue) {
        this.queue = queue;
    }

    String consume() throws InterruptedException {
        synchronized (queue) {
            if (queue.isEmpty()) {
                System.out.println("Queue is empty");
                queue.wait();
            }
            String value = queue.poll();
            queue.notify();
            return value;
        }
    }
}
