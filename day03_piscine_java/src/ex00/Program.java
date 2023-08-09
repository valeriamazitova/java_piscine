package ex00;

public class Program {
    public static void main(String[] args) {

        int count = getCountFromArgs(args);


        MyThread eggThread = new MyThread("Egg", count);

        eggThread.start();

        MyThread henThread = new MyThread("Hen", count);

        henThread.start();

//        printFromMainThread("Human", count);

    }

    private static void printFromMainThread(String message, int counter) {
        for (int i = 0; i < counter; i++) {
            System.out.println(message);
        }
    }

    private static int getCountFromArgs(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Required program arguments not found: --count=<int>");
            System.exit(-1);
        }

        String countFlag = "--count=";
        for (String arg : args) {
            if (arg.startsWith(countFlag)) {
                String countValue = arg.substring(countFlag.length());
                try {
                    return Integer.parseInt(countValue);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid count value: " + countValue);
                    System.exit(-1);
                }
            }
        }
        return -1;
    }
}
