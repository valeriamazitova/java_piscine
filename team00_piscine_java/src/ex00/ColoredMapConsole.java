package ex00;

//public class Program {
//
//
//}

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColoredBmpPrinter {

    public static void main(String[] args) {
        printColoredBmp("path/to/your/image.bmp");
    }

    public static void printColoredBmp(String imagePath) {
        try {
            BufferedImage image = Imaging.getBufferedImage(new File(imagePath));
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int rgb = image.getRGB(x, y);
                    // Extract RGB components and determine the corresponding console color
                    // Print colored character/block based on the color
                }
                System.out.println(); // Move to the next line
            }
        } catch (ImageReadException | IOException e) {
            e.printStackTrace();
        }
    }
}

