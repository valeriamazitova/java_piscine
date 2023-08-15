package ex01;

public class ThreadUtility {

    boolean producerPrintedValue = false; // producer in this case is EggThread

    public synchronized void put() {
        while (producerPrintedValue) {
            try {
                wait();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        producerPrintedValue = true;
        notify();
    }

    public synchronized void get() {
        while (!producerPrintedValue) {
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        producerPrintedValue = false;
        notify();
    }
}
