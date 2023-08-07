package ex00;

public class User {
    private Integer id;
    private String name;
    private int balance;

    public User(Integer id, String name, int balance) {
        this.id = id;
        this.name = name;
        setBalance(balance);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
