package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        SignatureMap signatureMap = new SignatureMap();
        signatureMap.fillSignatureMap(
                "/Users/carrjohn/IdeaProjects/day02_piscine_java/src/ex00/signatures.txt");

//        String filePath = "src/ex00/test-files/cube.bmp";

        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        while (!filePath.equals("42")) {

            try {
                byte[] buffer = readHEXFromFile(filePath);

                if (signatureMap.contains(buffer)) {
                    System.out.println("PROCESSED");
                    writeToResultFile(signatureMap.getSearchResult());
                } else {
                    System.out.println("UNDEFINED");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            filePath = scanner.nextLine();
        }

    }

    public static byte[] readHEXFromFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);

        byte[] buffer = new byte[8];
        fileInputStream.read(buffer);

        fileInputStream.close();

        return buffer;
    }

    public static void writeToResultFile(String fileFormat) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true);

            byte[] bytes = fileFormat.getBytes();
            fileOutputStream.write(bytes);

            byte[] endOfString = "\n".getBytes();
            fileOutputStream.write(endOfString);

            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
