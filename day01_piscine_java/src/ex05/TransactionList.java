package ex05;

import java.util.UUID;

public interface TransactionList {

    void addTransaction(Transaction t);

    void deleteTransactionById(UUID uuid);

    Object[] toArray();
}
