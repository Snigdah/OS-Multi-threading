public class Main {
    private static final int MAX_SIZE = 5;

    public static void main(String[] args) {
        Buffer buffer = new Buffer(MAX_SIZE);

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}