package ace.ucv.ro;

import java.util.Random;

public class Producer extends Thread {

    private Random random;

    Producer() {
        this.random = new Random();
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            Integer item = produceItem();
            try {
                SimulatePC.emptySemaphore.acquire();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
            SimulatePC.mutex.lock();
            SimulatePC.queue.add(item);
            SimulatePC.mutex.unlock();
            SimulatePC.fullSemaphore.release();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
    }

    private int produceItem() {
        Integer item = random.nextInt();
        System.out.println("Producer created element " + item);
        return item;
    }
}
