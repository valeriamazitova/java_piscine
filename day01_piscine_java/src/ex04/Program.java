package ex04;

public class Program {
    public static void main(String[] args) throws Exception {
        TransactionsService transactionsService = new TransactionsService();

        // Adding users
        User user1 = new User("John", 1000);
        User user2 = new User("Alice", 500);
        transactionsService.addUser(user1);
        transactionsService.addUser(user2);

        // Retrieving user balance
        System.out.println("User 1 balance: " + user1.getBalance());
        System.out.println("User 2 balance: " + user2.getBalance());

        // Performing a transfer
        try {
            transactionsService.performTransfer(1, 2, 200);
            System.out.println("Transfer successful");
        } catch (RuntimeException e) {
            System.out.println("Transfer failed");
        }

        System.out.println("User 1 updated balance: " + user1.getBalance());
        System.out.println("User 2 updated balance: " + user2.getBalance());

        System.out.println("-------------\n---here try-catch block throws exception---");
        try {
            transactionsService.performTransfer(2, 1, 1000);
            System.out.println("Transfer successful");
        } catch (RuntimeException e) {
            System.out.println("Transfer failed");
        }
    }
}
