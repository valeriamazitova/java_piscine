package app;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.util.*;

import annotations.HtmlForm;
import annotations.HtmlInput;
import com.google.auto.service.AutoService;

@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(HtmlForm.class.getName(), HtmlInput.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(HtmlForm.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
                List<String> inputFields = new ArrayList<>();

                for (Element fieldElement : element.getEnclosedElements()) {
                    if (fieldElement.getKind() == ElementKind.FIELD) {
                        HtmlInput htmlInput = fieldElement.getAnnotation(HtmlInput.class);
                        if (htmlInput != null) {
                            inputFields.add(
                                    String.format("<input type=\"%s\" name=\"%s\" placeholder=\"%s\">",
                                            htmlInput.type(), htmlInput.name(), htmlInput.placeholder())
                            );
                        }
                    }
                }

                String formContent = String.format(
                        "<form action=\"%s\" method=\"%s\">\n%s\n<input type=\"submit\" value=\"Send\">\n</form>",
                        htmlForm.action(), htmlForm.method(), String.join("\n", inputFields)
                );

                writeToFile(htmlForm.fileName(), formContent);
            }
        }

        return true;
    }

    private void writeToFile(String fileName, String content) {
        try {
            Filer filer = processingEnv.getFiler();
            FileObject fileObject = filer.createResource(
                    StandardLocation.CLASS_OUTPUT, "", fileName);
            try (Writer writer = fileObject.openWriter()) {
                writer.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


