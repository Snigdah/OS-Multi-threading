import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int maxSize;
    private final Queue<Integer> queue;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new LinkedList<>();
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notifyAll();
        return value;
    }
}
