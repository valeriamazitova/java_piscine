package ex01;

import static ex00.Program.getCountFromArgs;

public class Program {
    public static void main(String[] args) {
        int count = getCountFromArgs(args);

        ThreadUtility threadUtility = new ThreadUtility();

        new EggThread(threadUtility, count);
        new HenThread(threadUtility, count);

    }
}
