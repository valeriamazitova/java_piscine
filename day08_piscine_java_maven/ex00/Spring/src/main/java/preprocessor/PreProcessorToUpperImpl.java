package preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String process(String stringToProcess) {
        return stringToProcess.toUpperCase();
    }
}
