package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import preprocessor.PreProcessor;
import preprocessor.PreProcessorToUpperImpl;
import printer.Printer;
import printer.PrinterWithPrefixImpl;
import renderer.Renderer;
import renderer.RendererErrImpl;

public class Main {
    public static void main(String[] args) {
//        PreProcessor preProcessor = new PreProcessorToUpperImpl();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//        printer.setPrefix("Prefix");
//        printer.print("Hello!");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        PrinterWithPrefixImpl printer = context.getBean("printer", PrinterWithPrefixImpl.class);
        printer.setPrefix("Prefix");
        printer.print("Hello!");

    }
}
