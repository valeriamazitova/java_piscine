package ex02;

import java.util.Scanner;

public class Program {

    public static int sum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }

    public static boolean isPrime(int n) {
        boolean isPrime = true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        int counter = 0;
        while (number != 42) {
            if (isPrime(sum(number))) {
                counter++;
            }
            number = in.nextInt();
        }
        System.out.println("Count of coffee-request - " + counter);
    }
}