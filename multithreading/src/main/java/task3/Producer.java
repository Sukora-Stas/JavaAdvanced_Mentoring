package task3;

import java.util.Queue;

public class Producer {
    private final int capacity;
    private final Queue<String> queue;

    public Producer(Queue<String> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    void produce(String value) throws InterruptedException {
        synchronized (queue) {
            if (queue.size() == capacity) {
                System.out.println("Queue is full");
                queue.wait();
            }
            queue.add(value);
            queue.notify();
        }
    }
}
