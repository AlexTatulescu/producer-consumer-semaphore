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
                continue;
            }

            SimulatePC.mutex.lock();
            SimulatePC.queue.add(item);
            SimulatePC.mutex.unlock();
            SimulatePC.fullSemaphore.release();
        }
    }

    private int produceItem() {
        Integer item = random.nextInt();
        System.out.println("Producer created element " + item);
        return item;
    }
}
