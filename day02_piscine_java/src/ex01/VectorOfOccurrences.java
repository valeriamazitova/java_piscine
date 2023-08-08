package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VectorOfOccurrences {

    private final int[] vectorOfOccurrences;

    static Set<String> dictionary;

    public VectorOfOccurrences() {

        VectorOfOccurrences.dictionary = Dictionary.getDictionary();

        vectorOfOccurrences = new int[VectorOfOccurrences.dictionary.size()];


    }

    public int[] getVectorOfOccurrences() {
        return vectorOfOccurrences;
    }

    public void openFileAndFillVector(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                fillVectorWithValues(line.split(" "));
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillVectorWithValues(String[] words) {

        List<String> dictList = new ArrayList<>(dictionary);

        for (String word : words) {
            int index = dictList.indexOf(word);
            if (index >= 0) {
                vectorOfOccurrences[index]++;
            }
        }
    }
}
