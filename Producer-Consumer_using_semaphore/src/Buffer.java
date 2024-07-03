import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer {
    private final int maxSize;
    private final Queue<Integer> queue;
    private final Semaphore emptySlots;
    private final Semaphore fullSlots;
    private final Semaphore mutex;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
        this.emptySlots = new Semaphore(maxSize);
        this.fullSlots = new Semaphore(0);
        this.mutex = new Semaphore(1);
    }

    public void produce(int value) throws InterruptedException {
        emptySlots.acquire();
        mutex.acquire();
        queue.add(value);
        System.out.println("Produced " + value);
        mutex.release();
        fullSlots.release();
    }

    public int consume() throws InterruptedException {
        fullSlots.acquire();
        mutex.acquire();
        int value = queue.poll();
        System.out.println("Consumed " + value);
        mutex.release();
        emptySlots.release();
        return value;
    }
}
