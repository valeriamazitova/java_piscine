package ex00;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColoredBmpPrinter {

    public static void main(String[] args) {
        printColoredBmp("src/main/java/ex00/it.bmp");
    }

    public static void printColoredBmp(String imagePath) {
        try {
            BufferedImage image = Imaging.getBufferedImage(new File(imagePath));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);

                    // Check if the pixel is black (using a threshold value)
                    boolean isBlack = ((rgb & 0xFF) < 128); // Assuming grayscale

                    if (isBlack) {
                        System.out.print("\u001B[31m█\u001B[0m"); // Print red block for black
                    } else {
                        System.out.print("\u001B[32m█\u001B[0m"); // Print green block for white
                    }
                }
                System.out.println(); // Move to the next line
            }
        } catch (ImageReadException | IOException e) {
            e.printStackTrace();
        }
    }
}

