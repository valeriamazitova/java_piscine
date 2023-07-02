package ex01;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Program {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        if (number < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        } else {
            int numOfIterations = 1;
            boolean isPrime = true;
//            for (int i = 2; i <= sqrt(number); i++) {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
                numOfIterations++;
            }
            System.out.printf("%b %d", isPrime, numOfIterations);
        }
    }
}