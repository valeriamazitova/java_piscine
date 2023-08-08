package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

    private static Set<String> dictionary;

//    private int[] vectorOfOccurrences;

    public Dictionary() {

        dictionary = new HashSet<>();
    }

    public static Set<String> getDictionary() {
        return dictionary;
    }
//
//    public int[] getVectorOfOccurrences() {
//        return vectorOfOccurrences;
//    }

    public void openFileAndFillDictionary(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                fillTheDictionary(line);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillTheDictionary(String fileContent) {
        String[] words = fileContent.split(" ");

        for (String word : words) {
            dictionary.add(word);
        }
//        dictionary.addAll(Set.of(words));
//        vectorOfOccurrences = createVector(words);

    }


}
