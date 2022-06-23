package task4;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingObjectPool {
    private Queue<Object> objectQueue;
    private int size;

    //Creates filled pool of passed size
    public BlockingObjectPool(int size) {
        this.size = size;
        objectQueue = new ArrayBlockingQueue<>(size, true);
    }

    //Gets object from pool or blocks if pool is empty
    public Object get() {
        if (objectQueue.isEmpty()) {
            System.out.println("Queue is empty");
        }
        return objectQueue.poll();
    }

    //Puts object to pool or blocks if pool is full
    public void take(Object object) {
        objectQueue.add(object);
    }
}
