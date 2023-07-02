package ex02;

public class Program {

    public static void main(String[] args) throws Exception {
        User u1 = new User("Vasily", 500);
        User u2 = new User("Alexander", 700);
        User u3 = new User("Yuri", 1000);
        User u4 = new User("Sergey", 1400);
        User u5 = new User("Anna", 800);
        User u6 = new User("Kate", 850);
        User u7 = new User("Jennifer", 5000);
        User u8 = new User("Inna", 50);
        User u9 = new User("Pamela", 10);
        User u10 = new User("Oleg", 900);
        User u11 = new User("Tatiana", 750);

        UsersArrayList usersArrayList = new UsersArrayList();
        usersArrayList.addUser(u1);
        usersArrayList.addUser(u2);
        usersArrayList.addUser(u3);
        usersArrayList.addUser(u4);
        usersArrayList.addUser(u5);
        usersArrayList.addUser(u6);
        usersArrayList.addUser(u7);
        System.out.println("Size = " + usersArrayList.getSize());
        usersArrayList.addUser(u8);
        usersArrayList.addUser(u9);
        usersArrayList.addUser(u10);
        usersArrayList.addUser(u11);


        System.out.println(usersArrayList.getUserByIndex(5));
        System.out.println(usersArrayList.getUserById(1));
        System.out.println("AmountOfUsers = " + usersArrayList.getUserAmount());
        System.out.println("Size = " + usersArrayList.getSize());

//        exceptions
//        System.out.println(usersArrayList.getUserByIndex(11));
//        System.out.println(usersArrayList.getUserById(15));
    }
}
