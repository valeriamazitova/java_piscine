package ex01;

import static java.lang.Math.sqrt;

public class Similarity {
    private final double similarityCalcResult;

    private final int[] firstVector;

    private final int[] secondVector;


    public Similarity(VectorOfOccurrences firstVector, VectorOfOccurrences secondVector) {
        this.firstVector = firstVector.getVectorOfOccurrences();
        this.secondVector = secondVector.getVectorOfOccurrences();

        similarityCalcResult = performCalculations();
    }

    private double performCalculations() {
        return multiplyVectors() / (lengthOfVector(firstVector) * lengthOfVector(secondVector));
    }

    private double multiplyVectors() {

        double multiplicationResult = 0;
        for (int i = 0, j = 0; i < firstVector.length && j < secondVector.length; i++, j++) {
            multiplicationResult += firstVector[i] * secondVector[j];
        }
        return multiplicationResult;
    }

    private double lengthOfVector(int[] vector) {
        double resultLengthOfVector = 0;
        for (int i: vector) {
            resultLengthOfVector += i*i;
        }
        return sqrt(resultLengthOfVector);
    }

    public double getSimilarityCalcResult() {
        return similarityCalcResult;
    }
}
