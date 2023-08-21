package printer;

import renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    private final Renderer renderer;
    private LocalDateTime localDateTime;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        localDateTime = LocalDateTime.now();
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void print(String message) {
        renderer.print(message + localDateTime);
    }
}
