package ex01;

public class HenThread implements Runnable{

    private final ThreadUtility threadUtility;
    private final int counter;

    public HenThread(ThreadUtility threadUtility, int counter) {
        this.threadUtility = threadUtility;
        this.counter = counter;
        Thread henThread = new Thread(this, "Consumer");
        henThread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < counter; i++) {
            threadUtility.get();
        }

    }
}
