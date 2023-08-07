package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        User u1 = new User(1, "Vasily", 500);

        System.out.println(u1);

        User u2 = new User(2, "Alexey", 1500);

        System.out.println(u2);

//        ----------------------------------

        UUID uuid = UUID.randomUUID();
        Transaction t1 = new Transaction(uuid, u1, u2, Transaction.CategoryOfTransaction.OUTCOME, -500);

        System.out.println(t1);
    }
}