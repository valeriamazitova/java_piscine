package ex05;

import ex01.UserIdsGenerator;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static Menu instance;
    private static TransactionsService transactionsService;

    private Menu() {
        transactionsService = new TransactionsService();
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }
    public static void printMessage() {
        System.out.println("""
                1. Add a user
                2. View user balances
                3. Perform a transfer
                4. View all transactions for a specific user
                5. DEV - remove a transfer by ID
                6. DEV - check transfer validity
                7. Finish execution
                """);
    }

    public static void run(int userChoice, String profile) throws Exception {

        boolean isDevProfile = profile.equals("dev");
        Scanner scanner = new Scanner(System.in);

        switch (userChoice) {
            case 1:
                System.out.println("Enter a user name and a balance");
                String name = scanner.nextLine();
                int balance = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                User user = new User(name, balance);
                transactionsService.addUser(user);
                System.out.println("User with id = " + user.getId() + " is added");
                break;
            case 2:
                System.out.println("Enter a user ID");
                int userId = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                User u = transactionsService.getUser(userId);
                int userBalance = u.getBalance();
                System.out.println(u.getName() + " - " + userBalance);
                break;
            case 3:
                System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                int senderId = scanner.nextInt();
                int recipientId = scanner.nextInt();
                int transferAmount = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                try {
                    transactionsService.performTransfer(senderId, recipientId, transferAmount);
                    System.out.println("The transfer is completed");
                } catch (RuntimeException e) {
                    System.out.println("Transfer failed: " + e.getMessage());
                }
                break;
            case 4:
                System.out.println("Enter a user ID");
                int viewUserId = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                System.out.println("Transactions:");
                transactionsService.getUserTransfers(viewUserId).forEach(System.out::println);
                break;
            case 5:
                if (isDevProfile) {
                    System.out.println("Enter a user ID and a transfer ID");
                    int devUserId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    String transferId = scanner.nextLine();
                    transactionsService.removeTransaction(devUserId, UUID.fromString(transferId));
                    System.out.println("Transfer removed");
                } else {
                    System.out.println("Invalid option");
                }
                break;
            case 6:
                if (isDevProfile) {
                    System.out.println("Check results:");
                    transactionsService.getUnpairedTransactions().forEach(System.out::println);
                } else {
                    System.out.println("Invalid option");
                }
                break;
            case 7:
                System.out.println("Finish execution");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
        System.out.println("---------------------------------------------------------");
    }


}
