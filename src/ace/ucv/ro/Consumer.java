package ace.ucv.ro;

public class Consumer extends Thread {
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                SimulatePC.fullSemaphore.acquire();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
                continue;
            }
            SimulatePC.mutex.lock();
            Integer item = SimulatePC.queue.removeFirst();
            consume(item);
            SimulatePC.mutex.unlock();
            SimulatePC.emptySemaphore.release();
        }
    }


    private void consume(Integer item) {
        System.out.println("Consumer consumed element " + item);
    }
}
