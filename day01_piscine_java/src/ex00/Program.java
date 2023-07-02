package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        User u1 = new User();
        u1.setId(1);
        u1.setName("Vasily");
        u1.setBalance(500);
        System.out.println(u1);

        User u2 = new User();
        u2.setId(2);
        u2.setName("Alexey");
        u2.setBalance(1500);
        System.out.println(u2);

//        ----------------------------------

        UUID uuid = UUID.randomUUID();
        Transaction t1 = new Transaction(uuid, u1, u2, Transaction.CategoryOfTransaction.OUTCOME, -500);
//        t1.setUuid(uuid);
//        t1.setReceiver(u1);
//        t1.setSender(u2);
//        t1.setCategoryOfTransaction(Transaction.CategoryOfTransaction.OUTCOME);
//        t1.setAmount(-500);
        System.out.println(t1);
    }
}