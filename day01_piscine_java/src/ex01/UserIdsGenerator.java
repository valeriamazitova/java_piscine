package ex01;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int id;

    private UserIdsGenerator() {
        // Приватный конструктор
        id = 0;
    }

    public static synchronized UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public synchronized int generateId() {
        id++;
        return id;
    }
}

