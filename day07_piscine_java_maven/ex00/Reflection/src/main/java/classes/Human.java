package classes;

public class Human {

    private String firstName;
    private String lastName;
    private Integer age;

    public Human() {
        this.firstName = "Default first name";
        this.lastName = "Default last name";
        this.age = 0;
    }

    public Human(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int grow(int value) {
        this.age += value;
        return age;
    }


    @Override
    public String toString() {
        return "Human{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
