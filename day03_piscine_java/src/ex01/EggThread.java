package ex01;

public class EggThread implements Runnable{
    private final ThreadUtility threadUtility;
    private final int counter;
    public EggThread(ThreadUtility threadUtility, int counter) {
        this.threadUtility = threadUtility;
        this.counter = counter;
        Thread eggThread = new Thread(this, "Producer");
        eggThread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            threadUtility.put();
        }
    }
}
