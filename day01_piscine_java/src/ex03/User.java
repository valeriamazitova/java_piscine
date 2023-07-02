package ex03;

import ex02.UserIdsGenerator;

public class User {
    private Integer id;
    String name;
    int balance;
    TransactionLinkedList transaction;

    public User(String name, int balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance > 0) {
            this.balance = balance;
        } else {
            System.err.println("User's balance cannot be less than zero");
        }
        transaction = new TransactionLinkedList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        if (balance > 0) {
            this.balance = balance;
        } else {
            System.err.println("User's balance cannot be less than zero");
        }
    }

    public TransactionLinkedList getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionLinkedList transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
