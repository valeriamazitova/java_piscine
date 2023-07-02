package ex05;

import java.util.LinkedList;
import java.util.UUID;


public class TransactionsService {

    private UsersArrayList usersArrayList;

    public TransactionsService() {
        usersArrayList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersArrayList.addUser(user);
    }

    public int getUserBalance(int userId) throws Exception {
        User user = usersArrayList.getUserById(userId);
        return user.getBalance();
    }

    public void performTransfer(int senderId, int receiverId, int amount) throws Exception {
        User sender = usersArrayList.getUserById(senderId);
        User receiver = usersArrayList.getUserById(receiverId);

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance for the transaction");
        }

        UUID uuid = UUID.randomUUID();
        Transaction incomeTransaction = new Transaction(uuid, receiver, sender, 
                Transaction.CategoryOfTransaction.INCOME, amount);
        Transaction outcomeTransaction = new Transaction(uuid, sender, receiver,
                Transaction.CategoryOfTransaction.OUTCOME, amount * (-1));

        outcomeTransaction.setPaired(true);
        sender.addTransaction(outcomeTransaction);

        incomeTransaction.setPaired(true);
        receiver.addTransaction(incomeTransaction);

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
    }

    public LinkedList<Transaction> getUserTransfers(int userId) throws Exception {
        User user = usersArrayList.getUserById(userId);
        return user.getTransaction().transactionLinkedList;
    }

    public void removeTransaction(int userId, UUID transactionId) throws Exception {
        User user = usersArrayList.getUserById(userId);
        user.removeTransaction(transactionId);
    }

    public LinkedList<Transaction> getUnpairedTransactions() {
        LinkedList<Transaction> unpairedTransactions = new LinkedList<>();

        for (User user : usersArrayList.userArrayList) {
            for (Transaction transaction : user.getTransaction().transactionLinkedList) {
                if (!transaction.isPaired()) {
                    unpairedTransactions.add(transaction);
                }

            }
        }

        return unpairedTransactions;
    }


    public User getUser(int userId) throws Exception {
        return usersArrayList.getUserById(userId);
    }
}
