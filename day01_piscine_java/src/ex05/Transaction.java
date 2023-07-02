package ex05;

import java.util.UUID;

public class Transaction {
    public enum CategoryOfTransaction {
        OUTCOME, INCOME
    }

    UUID uuid;
    User receiver;
    User sender;
    Transaction.CategoryOfTransaction categoryOfTransaction; // outcome or income
    int amount;

//    additional
    private boolean paired;

    public Transaction(UUID uuid, User receiver, User sender,
                       Transaction.CategoryOfTransaction categoryOfTransaction, int amount) {
        this.uuid = uuid;
        this.receiver = receiver;
        this.sender = sender;
        this.categoryOfTransaction = categoryOfTransaction;
        setAmount(amount);
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setCategoryOfTransaction(Transaction.CategoryOfTransaction category) {
        this.categoryOfTransaction = category;
    }

    public void setAmount(int amount) {
        if ((categoryOfTransaction == Transaction.CategoryOfTransaction.OUTCOME && amount < 0) ||
                (categoryOfTransaction == Transaction.CategoryOfTransaction.INCOME && amount > 0)) {
            this.amount = amount;
        } else {
            System.err.println("Sum of transaction should be according to its type");
        }
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public Transaction.CategoryOfTransaction getCategoryOfTransaction() {
        return categoryOfTransaction;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
//        return "Transaction{" +
//                "uuid=" + uuid +
//                ", receiver=" + receiver +
//                ", sender=" + sender +
//                ", categoryOfTransaction=" + categoryOfTransaction +
//                ", amount=" + amount +
//                "}\n";
        return "To " + sender.getName() + "(id = " + sender.getId() + ") " + amount +
                " with id = " + uuid;
    }

    public boolean isPaired() {
        return paired;
    }

    public void setPaired(boolean paired) {
        this.paired = paired;
    }
}
