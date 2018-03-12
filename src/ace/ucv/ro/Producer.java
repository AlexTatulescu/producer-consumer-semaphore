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
            SimulatePC.emptySemaphore.release();
            SimulatePC.mutex.lock();
            Integer item = produceItem();
            SimulatePC.queue.add(item);
            SimulatePC.mutex.unlock();
            try {
                SimulatePC.fullSemaphore.acquire();
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
