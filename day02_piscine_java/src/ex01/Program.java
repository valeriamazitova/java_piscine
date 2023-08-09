package ex01;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Program {

    public static void main(String[] args) {
        if (args.length >= 2) {

            Dictionary fileDictionary = new Dictionary();
            fileDictionary.openFileAndFillDictionary(args[0]);
            fileDictionary.openFileAndFillDictionary(args[1]);

            VectorOfOccurrences vectorOfOccurrences1 = new VectorOfOccurrences();
            VectorOfOccurrences vectorOfOccurrences2 = new VectorOfOccurrences();


            vectorOfOccurrences1.openFileAndFillVector(args[0]);
            vectorOfOccurrences2.openFileAndFillVector(args[1]);

            writeToResultFile(Arrays.toString(Dictionary.getDictionary().toArray()));

            Similarity similarity = new Similarity(vectorOfOccurrences1, vectorOfOccurrences2);
            System.out.print("Similarity = " + similarity.getSimilarityCalcResult());


        } else {
            System.out.println("No arguments provided.");
        }
    }

    public static void writeToResultFile(String fileFormat) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("dictionary.txt", true);

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
