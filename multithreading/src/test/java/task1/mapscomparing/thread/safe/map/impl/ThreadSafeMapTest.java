package task1.mapscomparing.thread.safe.map.impl;

import org.junit.jupiter.api.Test;
import task1.maps.comparing.thread.safe.map.impl.ThreadSafeMap;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSafeMapTest {
    private static final Random random = new Random();
    private static final int THREAD_COUNT = 3;
    private static final int NUMBER_OF_OPERATIONS = 1000;
    private static final int KEYS_BOUNDARY = 5;

    @Test
    public void getTest() throws InterruptedException {
        List<ThreadSafeMap.MyEntry<Integer, Integer>> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            entries.add(new ThreadSafeMap.MyEntry<>(random.nextInt(KEYS_BOUNDARY), random.nextInt()));
        }
        ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>(entries);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(getReadRunnable(map));
            thread.start();
            thread.join();
        }
    }

    @Test
    public void putTest() throws InterruptedException {
        ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(getWriteRunnable(map));
            thread.start();
            thread.join();
        }
    }

    @Test
    public void removeTest() throws InterruptedException {
        List<ThreadSafeMap.MyEntry<Integer, Integer>> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            entries.add(new ThreadSafeMap.MyEntry<>(random.nextInt(KEYS_BOUNDARY), random.nextInt()));
        }
        ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>(entries);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(getRemoveRunnable(map));
            thread.start();
            thread.join();
        }
    }

    @Test
    public void putGetRemoveTest() throws InterruptedException {
        List<ThreadSafeMap.MyEntry<Integer, Integer>> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            entries.add(new ThreadSafeMap.MyEntry<>(random.nextInt(KEYS_BOUNDARY), random.nextInt()));
        }
        ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>(entries);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread writerThread = new Thread(getWriteRunnable(map));
            writerThread.start();
            writerThread.join();
            Thread readerThread = new Thread(getReadRunnable(map));
            readerThread.start();
            readerThread.join();
            Thread removeThread = new Thread(getRemoveRunnable(map));
            removeThread.start();
            removeThread.join();
        }
    }

    @Test
    public void getKeysGetValuesTest() throws InterruptedException {
        List<ThreadSafeMap.MyEntry<Integer, Integer>> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            entries.add(new ThreadSafeMap.MyEntry<>(random.nextInt(KEYS_BOUNDARY), random.nextInt()));
        }
        ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>(entries);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread getKeys = new Thread(getKeysRunnable(map));
            getKeys.start();
            getKeys.join();
            Thread getValues = new Thread(getValuesRunnable(map));
            getValues.start();
            getValues.join();
            Thread writerThread = new Thread(getWriteRunnable(map));
            writerThread.start();
            writerThread.join();
            Thread readerThread = new Thread(getReadRunnable(map));
            readerThread.start();
            readerThread.join();
            Thread removeThread = new Thread(getRemoveRunnable(map));
            removeThread.start();
            removeThread.join();
        }
    }

    @Test
    public void iteratorTest() throws InterruptedException {
        List<ThreadSafeMap.MyEntry<Integer, Integer>> entries = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            entries.add(new ThreadSafeMap.MyEntry<>(random.nextInt(KEYS_BOUNDARY), random.nextInt()));
        }
        ThreadSafeMap<Integer, Integer> map = new ThreadSafeMap<>(entries);
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread iteratorThread = new Thread(getIteratorRunnable(map));
            iteratorThread.start();
            iteratorThread.join();
            Thread writerThread = new Thread(getWriteRunnable(map));
            writerThread.start();
            writerThread.join();
            Thread readerThread = new Thread(getReadRunnable(map));
            readerThread.start();
            readerThread.join();
            Thread removeThread = new Thread(getRemoveRunnable(map));
            removeThread.start();
            removeThread.join();
        }
    }

    private static Runnable getReadRunnable(ThreadSafeMap<Integer, Integer> map) {
        return () -> {
            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                int key = ThreadLocalRandom.current().nextInt(KEYS_BOUNDARY);
                Integer value = map.get(key);
                System.out.println("For key = " + key + " got value = " + value);
            }
        };
    }

    private static Runnable getWriteRunnable(ThreadSafeMap<Integer, Integer> map) {
        return () -> {
            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                Integer key = ThreadLocalRandom.current().nextInt(KEYS_BOUNDARY);
                Integer value = ThreadLocalRandom.current().nextInt();
                map.put(key, value);
                System.out.println("Put key = " + key + " with value = " + value);
            }
        };
    }

    private static Runnable getRemoveRunnable(ThreadSafeMap<Integer, Integer> map) {
        return () -> {
            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                Integer key = ThreadLocalRandom.current().nextInt(KEYS_BOUNDARY);
                map.remove(key);
                System.out.println("Entry with key = " + key + " was removed");
            }
        };
    }

    private static Runnable getKeysRunnable(ThreadSafeMap<Integer, Integer> map) {
        return () -> {
            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                Set<Integer> keys = map.getKeys();
                System.out.println(keys.toString());
            }
        };
    }

    private static Runnable getValuesRunnable(ThreadSafeMap<Integer, Integer> map) {
        return () -> {
            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                List<Integer> values = map.getValues();
                System.out.println(values.toString());
            }
        };
    }

    private static Runnable getIteratorRunnable(ThreadSafeMap<Integer, Integer> map) {
        return () -> {
            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                Iterator<ThreadSafeMap.MyEntry<Integer, Integer>> iterator = map.iterator();
                if (iterator.hasNext()) {
                    System.out.println("Iterator.next = " + iterator.next());
                    iterator.remove();
                    System.out.println("Iterator was remove");
                }
            }
        };
    }
}
