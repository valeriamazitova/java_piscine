package ex02;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        checkProgramArgumentsCorrectness(args);

        String folderPath = args[0].substring("--current-folder=".length());

        System.out.println(folderPath);
        FileUtility fileUtility = new FileUtility(Paths.get(folderPath));

        try {
            userInputScan(fileUtility);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void userInputScan(FileUtility fileUtility) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("-> ");
        String input = scanner.nextLine();


        while (!input.equals("exit")) {

            String[] commandArgs = input.split("\\s+");

            fileUtility.runCommand(commandArgs);

            System.out.print("-> ");
            input = scanner.nextLine();
        }

    }

    public static void checkProgramArgumentsCorrectness(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
            System.err.println("Required program arguments not found: --current-folder=<folder_path>");
            System.exit(-1);
        }
    }
}
