package ex00;

public class MyThread extends Thread {

    private final Integer counter;

    private final String message;

//    public void setMessage(String message) {
//        this.message = message;
//    }

//    public void setCounter(Integer counter) {
//        this.counter = counter;
//    }

    public MyThread(String message, Integer counter) {
        this.message = message;
        this.counter = counter;
    }

    public void run() {
        for (int i = 0; i < counter; i++) {
            System.out.println(message);
        }
    }
}
