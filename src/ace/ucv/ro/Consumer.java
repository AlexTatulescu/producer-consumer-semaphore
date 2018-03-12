package ace.ucv.ro;

public class Consumer extends Thread {
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            SimulatePC.fullSemaphore.release();
            SimulatePC.mutex.lock();
            Integer item = SimulatePC.queue.remove(0);
            consume(item);
            SimulatePC.mutex.unlock();
            try {
                SimulatePC.emptySemaphore.acquire();
            } catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
    }


    private void consume(Integer item) {
        System.out.println("Consumer consumed element " + item);
    }
}
