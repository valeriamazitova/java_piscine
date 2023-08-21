package app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Classes:\n  - Human\n  - Dog\n---------------------");
        System.out.print("Enter class name:\n-> ");
        String className = scanner.nextLine();

        Class<?> clazz = Class.forName("classes." + className);
        System.out.println("---------------------");
        System.out.println("fields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("    " + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("    " + method.getReturnType().getSimpleName() + " " + method.getName()
                    + "(" + Arrays.toString(method.getParameterTypes()) + ")");
        }

        System.out.println("---------------------\nLet's create an object.");
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        Object object = constructor.newInstance();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.print(field.getName() + ":\n -> ");
            String value = scanner.nextLine();
            if (field.getType() == String.class) {
                field.set(object, value);
            } else if (field.getType() == Integer.class) {
                field.set(object, Integer.parseInt(value));
            }
        }

        System.out.println("Object created: " + object);

        System.out.println("---------------------\nEnter name of the method for call:");
        String methodName = scanner.nextLine();
        Method method = clazz.getDeclaredMethod(methodName, int.class);
        if (method.getReturnType() != void.class) {
            System.out.println("Enter int value:");
            int intValue = Integer.parseInt(scanner.nextLine());
            Object result = method.invoke(object, intValue);
            System.out.println("Method returned:\n" + result);
        } else {
            System.out.println("Enter int value:");
            int intValue = Integer.parseInt(scanner.nextLine());
            method.invoke(object, intValue);
            System.out.println("Method executed.");
        }
    }
}
