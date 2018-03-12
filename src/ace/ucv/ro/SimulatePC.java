package ace.ucv.ro;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimulatePC {
    public volatile static LinkedList<Integer> queue = new LinkedList<>();
    public static Semaphore fullSemaphore = new Semaphore(0);
    public static Semaphore emptySemaphore = new Semaphore(5);
    public static Lock mutex = new ReentrantLock();

    public static void main(String[] args) {

        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.run();
        consumer.run();

        try {
            producer.join();
        } catch (InterruptedException ie) {
            ie.getMessage();
        }
        try {
            consumer.join();
        } catch (InterruptedException ie) {
            ie.getMessage();
        }
    }
}
