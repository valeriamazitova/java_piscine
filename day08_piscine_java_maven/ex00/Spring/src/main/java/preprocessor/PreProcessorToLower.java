package preprocessor;

public class PreProcessorToLower implements PreProcessor {

    @Override
    public String process(String stringToProcess) {
        return stringToProcess.toLowerCase();
    }
}
