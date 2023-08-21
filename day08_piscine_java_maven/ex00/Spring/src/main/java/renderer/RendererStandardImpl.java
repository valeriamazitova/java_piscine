package renderer;

import preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void print(String textToPrint) {
        System.out.println(preProcessor.process(textToPrint));
    }
}
