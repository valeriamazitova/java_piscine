package ex05;

import java.util.Scanner;

public class Program {
    public static int scanInput() {
        boolean wrongInputFlag = true;

//        сканировать число от 1 до 7
        Scanner in = new Scanner(System.in);
        int choice = 0;
        while (wrongInputFlag) {
            Menu.printMessage();
            try {
                choice = in.nextInt();
                if (choice < 1 || choice > 7) {
                    System.err.println("WrongInput");
                    continue;
                }
                wrongInputFlag = false;
            } catch (Exception e) {
                System.err.println("WrongInput");
                System.exit(-1);
            }
        }
        return choice;
    }
    public static void main(String[] args) throws Exception {

        String profile = "production"; // Default to production mode

        // Check if dev profile is specified
        if (args.length > 0 && args[0].equals("--profile=dev")) {
            profile = "dev";
        }

//        System.out.println(profile);
        Menu menu = Menu.getInstance();
        int userChoice = 0;
//        System.out.println(userChoice);


        while (userChoice != 7) {
            userChoice = scanInput();
            Menu.run(userChoice, profile);
        }
    }
}
