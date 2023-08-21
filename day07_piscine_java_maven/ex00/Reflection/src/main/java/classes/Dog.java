package classes;

public class Dog {

    private String breed;
    private Integer age;
    private String color;

    public Dog() {
        breed = "Default breed";
        color = "Default color";
        age = 0;
    }

    public Dog(String breed, Integer age, String color) {
        this.breed = breed;
        this.age = age;
        this.color = color;
    }

    public int grow(int value) {
        this.age += value;
        return age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
