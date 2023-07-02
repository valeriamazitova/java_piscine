package ex05;

import java.util.ArrayList;

public class UsersArrayList implements UsersList {
    ArrayList<User> userArrayList;
    private int counterOfAddedElements;
    private int size;

    public UsersArrayList() {
        size = 10;
        userArrayList = new ArrayList<>(10);
        counterOfAddedElements = 0;
    }


    @Override
    public void addUser(User user) {
        if (counterOfAddedElements >= size) {
            userArrayList.ensureCapacity((int) (size * 1.5));
            size = (int) (size * 1.5);
        }
        userArrayList.add(user);
        counterOfAddedElements++;
    }

    public int getSize() {
        return size;
    }

    @Override
    public User getUserById(int id) throws Exception {
        for (int i = 0; i < counterOfAddedElements; i++) {
            if (userArrayList.get(i).getId() == id) {
                return userArrayList.get(i);
            }
        }
        throw new Exception("UserNotFoundException");
    }

    @Override
    public User getUserByIndex(int index) throws Exception {
        if (index >= counterOfAddedElements) {
            throw new Exception("UserNotFoundException");
        } else {
            return userArrayList.get(index);
        }
    }

    @Override
    public int getUserAmount() {
        return counterOfAddedElements;
    }
}
