package ex03;

import java.util.Objects;
import java.util.Scanner;

public class Program {
    public static long inputCheck() {
        Scanner in = new Scanner(System.in);
        String word = in.next();
        int number = in.nextInt();
        int week_count = 1;
        int min_score, tmp_score;
        long  resulting_value = 0;
        while (number < 19) {

            min_score = 999;
            tmp_score = 0;
            if (word.equals("Week") && number == week_count) {
                for (int i = 0; i < 5; i++) {
                    tmp_score = in.nextInt();
                    if (tmp_score < min_score) {
                        min_score = tmp_score;
                    }
                }

                resulting_value = resulting_value * 10 + min_score;

                week_count++;
                word = in.next();
                if (word.equals("42")) break;
                number = in.nextInt();
            } else {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
        }
        return resulting_value;
    }
    public static void output(long resulting_value) {
        String numberString = Long.toString(resulting_value);
        for (int i = 0; i < numberString.length(); i++) {
            int digit = Character.getNumericValue(numberString.charAt(i));
            System.out.print("Week " + (i+1) + " ");
            for (int j = 0; j < digit; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    public static void main(String[] args){

        long resulting_value = inputCheck();
        output(resulting_value);
    }

}