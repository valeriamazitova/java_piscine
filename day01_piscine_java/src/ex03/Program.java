package ex03;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User u1 = new User("Vasily", 500);
        User u2 = new User("Alexander", 700);
        User u3 = new User("Yuri", 1000);
        User u4 = new User("Sergey", 1400);
        User u5 = new User("Anna", 800);

        Transaction t1 = new Transaction(UUID.randomUUID(), u1, u2,
                Transaction.CategoryOfTransaction.OUTCOME, -50);
        Transaction t2 = new Transaction(UUID.randomUUID(), u1, u2,
                Transaction.CategoryOfTransaction.OUTCOME, -50);
        Transaction t3 = new Transaction(UUID.randomUUID(), u1, u3,
                Transaction.CategoryOfTransaction.OUTCOME, -50);
        Transaction t4 = new Transaction(UUID.randomUUID(), u1, u4,
                Transaction.CategoryOfTransaction.OUTCOME, -50);
        Transaction t5 = new Transaction(UUID.randomUUID(), u5, u1,
                Transaction.CategoryOfTransaction.OUTCOME, -50);

        TransactionLinkedList transactionLinkedList = new TransactionLinkedList();
        transactionLinkedList.addTransaction(t1);
        transactionLinkedList.addTransaction(t2);
        transactionLinkedList.addTransaction(t3);
        transactionLinkedList.addTransaction(t4);
        transactionLinkedList.addTransaction(t5);

        u1.setTransaction(transactionLinkedList);

        System.out.println(u1);
        System.out.println(u1.getTransaction());

//        exception throwing
//        transactionLinkedList.deleteTransactionById(UUID.randomUUID());
    }
}
