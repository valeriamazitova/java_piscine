package ex03;

import java.util.LinkedList;
import java.util.UUID;

public class TransactionLinkedList implements TransactionList {

    LinkedList<Transaction> transactionLinkedList;

    public TransactionLinkedList() {
        transactionLinkedList = new LinkedList<>();
    }

    @Override
    public void addTransaction(Transaction t) {
        transactionLinkedList.add(t);
    }

    @Override
    public void deleteTransactionById(UUID uuid) {
        for (int i = 0; i < transactionLinkedList.size(); i++) {
            if (transactionLinkedList.get(i).getUuid() == uuid) {
                transactionLinkedList.remove(i);
                return;
            }
        }
        throw new RuntimeException("TransactionNotFoundException");
    }

    @Override
    public Object[] toArray() {
        return transactionLinkedList.toArray();
    }

    @Override
    public String toString() {
        return "TransactionLinkedList{" +
                "transactionLinkedList=" + transactionLinkedList +
                '}';
    }
}
